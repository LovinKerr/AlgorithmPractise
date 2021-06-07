class _62_UniquePaths {
//Solution 1.
    //dp: Time = O(m*n) 每个点走过一次
    // Space = O(m*n) dp array size
      public int uniquePaths(int m, int n) {
        //1.变量： 记录的状态
        int[][] dp = new int[m][n]; //记录起点到当前位置有几种解法
        //2.initialization
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //3.transformation formula
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j - 1]; //当前位置path取决于左边数量+上面数量，对应只能向右向下走
            }
        }
          //4.结果 求的是哪个状态
        return dp[m - 1][n - 1];
    } 

//错误
    int counter = 0;
    //固定start, end, 有多少种走法
    //recursion 全部走一遍
    //Time= O(n) ?多少遍n呢
    //Space= stack depth + o(m*n)
    
    int[][] direction = {{0,1}, {1, 0}};
    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        helper(0,0,visited, matrix);
        return counter;
    }
    //记录走了多少种解法
    private void helper(int i, int j, boolean[][] visited, int[][] matrix) {
        if(i == matrix.length - 1 && j == matrix[0].length - 1) {
            counter++;
            return; 
        }
        if(i >= matrix.length || j >= matrix[0].length || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for(int k = 0; k < direction.length; k++) {
            int[] dir = direction[k];
            int row = i + dir[0];
            int col = j +dir[1];
            helper(row, col, visited, matrix);
        }
    }
}