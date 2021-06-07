class _64_MinPathSum {
    //Space = O(m*n)
    //Time = O(m*n) 每个点走过一遍
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        //1.变量 记录状态
        int[][] dp = new int[row][col]; //从起点到当前点min sum
        //2.初始化[0][i] [i][0只有一条路可走
        dp[0][0] = grid[0][0];
        for(int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        //3.transformation formula
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        //Result
        return dp[row - 1][col - 1];
    }
}