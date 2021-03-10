class NetworkDelayTime743 {
    public int networkDelayTime(int[][] times, int n, int k) {

//direct graph, int[0,1]-> 0: dest label, 1: time
        HashMap<Integer,HashMap<Integer,Integer>> graph = buildGraph(times); //direct graph, int[0,1]-> 0: dest label, 1: time

//已知到src最短路径,dist也可以用HashMap表示，这里用array因为1到n，注意index 为0的是dummy，无用，不能为MAX_VALUE,        
        int[] dist = new int[n + 1]; 
        HashSet<Integer> visited = new HashSet<>();
//pq帮助拿到下一个最小的dist
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> a[1] - b[1]); //minHeap, label to dist

//初始化dist, 用node取代i，更明确        
        for(int node = 1; node < dist.length; node++){ //初始化dist, 用node取代i，更明确
            if(node == k) {
                dist[node] = 0;
            } else if(graph.containsKey(node) && graph.get(k).containsKey(node)) { //注意get 之前都判断是否存在
                dist[node] = graph.get(k).get(node);
                int[] temp = {node, dist[node]};
                pq.offer(temp);
            } else {
                dist[node] = Integer.MAX_VALUE;
            }
        }
        visited.add(k);

//dijkstra实现：走到当前node，更新neighbor node，dist和pq
        while(!pq.isEmpty()) {
            int[] curr = pq.poll(); 
            int label = curr[0];
            int currTime = curr[1];
            if(!visited.contains(label)){
                visited.add(label);
                if(graph.containsKey(label)) {
                    for(Map.Entry<Integer,Integer> ele : graph.get(label).entrySet()) {
                        int nextLabel = ele.getKey();
                        int nextTime = ele.getValue();
                        int updateTime = currTime + nextTime;
                        if(dist[nextLabel] > updateTime) {
                            dist[nextLabel] = updateTime;
                            int[] update = {nextLabel, updateTime};
                            pq.offer(update);
                        }
                    }
                }
            }
        }
 //最后根据题意求dist中最长的是，注意corner case,走不到的情况       
        int res = 0;
        for(int i : dist) {
            res = i > res ? i : res;
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    //direct graph, int[0,1]-> 0: dest label, 1: time
    private HashMap<Integer,HashMap<Integer,Integer>> buildGraph(int[][] times){
        HashMap<Integer,HashMap<Integer,Integer>> graph = new HashMap<>();
        for(int[] ele : times) {
            int src = ele[0];
            int dest = ele[1];
            int time = ele[2];
            if(graph.containsKey(src)) {
                graph.get(src).put(dest,time);
            } else {
                HashMap<Integer,Integer> temp = new HashMap<>();
                temp.put(dest,time);
                graph.put(src,temp);
            }
        }
        return graph;
    }
    
}