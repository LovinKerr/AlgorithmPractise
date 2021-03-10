class PathWithMinEffort1631 {

//Solution1. Dijkstra

    //此题source point为start
    //错点：test case[[1,10,6]] ！！算法的精髓忘记了
    int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        //adjacency matrix
        int[][] dist = new int[row][col]; //长度不是index，粗心噢

        for(int[] ele : dist) {
            Arrays.fill(ele, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        
        //visited判断是boolean,不要顺手写错成int
        boolean[][] visited = new boolean[row][col]; //default为false
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]); //{diff, row, col}
        
        int[] first = new int[]{0,0,0};
        pq.offer(first);
        
        //直接把heights当作graph
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int nodeRow = node[1];
            int nodeCol = node[2];
            // int nodeVal = node[0]; //node val不再pq里
            int nodeVal = heights[nodeRow][nodeCol];
            if(visited[nodeRow][nodeCol] == false) {
                visited[nodeRow][nodeCol] = true;
                if(nodeRow == row - 1 && nodeCol == col - 1) {
                    return node[0];
                }
            //走四面
                for(int i = 0; i < direction.length; i++) {
                    int[] dir = direction[i];
                    int r = nodeRow + dir[0];
                    int c = nodeCol + dir[1];
                    
                    if(r >=0 && r < row&& c >=0 && c < col) {
                        //1.update dist
                        int next = heights[r][c];
                        // int diff = Math.abs(next - nodeVal);
                        // int diff = nodeVal - next;
                        int diff = Math.abs(next - nodeVal); //diff只算绝对值

    //思考点：因为是找maxDiff,所以现在diff和当前比，记录大的
                        
                        int maxDiff = Math.max(diff, dist[nodeRow][nodeCol]);
                        dist[r][c] = dist[r][c] > diff ? diff : dist[r][c];
                       //2. add to pq
                        int[] tempNode = new int[]{dist[r][c], r, c};
                        pq.offer(tempNode);
                    }
                }
            }
        }
        //最后不是找其中最小的，而是target cell的值
        //dist记录的是每个点到start的最短diff，题中求的是target点到start的最短path diff, 所以为此cell值
        return dist[row - 1][col - 1];
    }


//Solution2. DFS + binary search using Hashmap

    int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
        // int maxDiff = 0;
        public int minimumEffortPath(int[][] heights) {
            //1.build graph
            HashMap<Integer, HashMap<Integer,Integer>> graph = buildGraph(heights); //Time = O(mn), 走4边4mn; space = graph空间 O（mn） 4mn
            
            int endId = getId(heights.length - 1, heights[0].length - 1, heights[0].length);
            //2.binary search
            int res = binarySearch(0, 1000000, graph, endId);//Time = O(mn*log1000000) 大的常数值说一下 => O(mn) Space = O(mn) recursion stack深度，最深mn
            //3.(validate) dfs
            return res;
        }
        //check if input num is valid, contains at least on path that all diff <= input
        //走dfs要判断visited
        private boolean dfs(int ifValidNum, HashMap<Integer, HashMap<Integer,Integer>> graph, int startId, int tgtId, HashSet<Integer> visitedId) { //scan图，time=O(mn) worst
            if(startId == tgtId){
                return true;
            } 
            //check curr diff <= input
            if(graph.containsKey(startId)) {
                visitedId.add(startId);
                for(Map.Entry<Integer, Integer> map : graph.get(startId).entrySet()) {
                    int nextId = map.getKey();
                    int diff = map.getValue();
                    if(!visitedId.contains(nextId)) {
                        if(diff <= ifValidNum) {
                            //往下走有一条路能连通
                            if(dfs(ifValidNum, graph, nextId, tgtId, visitedId)) {
                                return true;
                            }
                        } //接下来走的点如果全部是不valid, return false， 否则，return true(能走通) 
                    }
                }
            }
            return false;
        }
        private int binarySearch(int low, int high, HashMap<Integer, HashMap<Integer,Integer>> graph, int endId){
            while(low < high) {// 固定写low < high, 不用low <= high
               int mid = low + (high - low)/ 2;
               //判断每条边的diff都<= 此数，valid
               if(dfs(mid, graph, 0, endId, new HashSet<>())) {
                   high = mid; //判断mid是否能包含进high里，这里mid是可行解
               } else {
                   low = mid + 1; //这里代表mid不是可行解
               }
            }
            return low; //这时候low等于high,所以跳出来； 2.return值是low还是high看需要的最终值越小越好还是相反
        }
        
        private HashMap<Integer, HashMap<Integer,Integer>> buildGraph(int[][] heights){
            HashMap<Integer,HashMap<Integer,Integer>> graph = new HashMap<>(); //currId to [[nextId,diff]]
            int rowL = heights.length;
            int colL = heights[0].length;
            for(int i = 0; i < rowL; i++) {
                for(int j = 0; j < colL; j++) {
                    int currId = getId(i,j,colL);
                    int currVal = heights[i][j];
                    graph.putIfAbsent(currId, new HashMap<>());
                    for(int k = 0; k < direction.length; k++) {
                        int[] dir = direction[k];
                        int r = i + dir[0];
                        int c = j + dir[1];
                        if(r >= 0 && r < rowL && c >= 0 && c < colL) {
                            int nextId = getId(r,c,colL);
                            int nextVal = heights[r][c];
                            graph.get(currId).put(nextId, Math.abs(nextVal - currVal));
                        }
                    }
                }
            }
            return graph;
        }
        
        private int getId(int i , int j, int colL) {
            return i*colL + j;
        }
    }