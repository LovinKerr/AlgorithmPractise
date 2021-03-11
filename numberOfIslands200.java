class numberOfIslands200 {
//dfs solution
    //time = O(mn * k) 每个点操作recursion 一次操作4，recursion 次数k
    //space = recursion stack 占用的空间 ？ O(log mn)?
    
    int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public int numIslands(char[][] grid) {
        //走过mark成2
        int m = grid.length;
        int n = grid[0].length;
        int counter = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') { //find an island
                    counter++;
                    helper(grid, i, j); //find the connected area and mark it as another
                }
            }
        }
        return counter;
    }
    
    private void helper(char[][] grid, int row, int col) {
        if(row >= grid.length || col >= grid[0].length || row < 0 || col < 0) {
            return;
        }
        if(grid[row][col] == '1') {

            //handle curr
            grid[row][col] = '2';

            //find four edge
            for(int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];
                int r = row + dir[0];
                int c = col + dir[1];
                helper(grid, r, c);
            }
        }
    }

//Union Find Solution
    // 此道题类似于把大象装冰箱, 一共分三步:

    // 初始化
    // 检查双下左右四个方向
    // 合并岛屿减count

    // Time = O（mn + 4k) k为操作的次数, 小于bfs/dfs的O(mN*k)
    // Space = O(mn) father的size，1维数组长度为mn   
        /*这道题, 用bfs或者dfs的时候, k个操作, 时间复杂度是O(kmn), 其中m是行数, n是列数.
    但是其实每次操作, 我们并不需要遍历整张图, 只需要在每个点附近的局部做处理就行, 这种bfs/dfs下的局部处理, 可以想到并查集. 需要做的的事是: 把其他的操作,转化成查找和合并这两件事情.*/
    int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    
    public int numIslands(char[][] grid) {
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        if(row == 0 || col == 0) {
            return 0;
        }
        
        //initial father and unionfind
        int total = 0;
        UnionFind uf = new UnionFind(row*col); //convert it into an 1d array
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {//把每一个land的地方记录
                    total++;
                }
            }
        }
        
        uf.setCount(total);
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    for(int d = 0; d < direction.length; d++) {
                        int[] dir = direction[d];
                        int r = i + dir[0];
                        int c = j + dir[1];
                        if(isValid(r,c,row,col) && grid[r][c] == '1') {
                            uf.connect(i * col + j, r * col + c);
                        }
                    }
                }
            }
        }
        return uf.query();
    }
    
        private boolean isValid(int r, int c, int row, int col) {
            if(r >= 0 && r < row && c >= 0 && c < col) {
                return true;
            }
            return false;
        }
        
        private class UnionFind{
            int[] father = null;
            int count;
        //initialize 1-> n in father,default: father of the node is self
            public UnionFind(int n) {
                father = new int[n];
                for(int i = 0; i < n; i++) {
                    father[i] = i;
                }
            }
            
            public void setCount(int total) {
                count = total;
            }
            
        //路径压缩型, find father
            private int find(int x) {
                //直到找到一个father为自己的node， 最大的大哥
                if(father[x] == x) {
                    return x;
                }
                return father[x] = find(father[x]); 
            }
        //合并
            public void connect(int a, int b) {
                int father_a = find(a);
                int father_b = find(b);
                
                //合并两个点，拥有共同大哥
                if(father_a != father_b) {
                    father[father_a] = father_b;
                    count--;
                }
            }
            public int query(){
                return count;
            }
        }
    }