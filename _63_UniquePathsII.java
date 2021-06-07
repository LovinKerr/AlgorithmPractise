class _63_UniquePathsII {

//SOlution 1.
    //dp
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        if(obstacleGrid[0][0] == 1) {
            return 0;
        } else {
            dp[0][0] = 1;
        }
        //intialization
        //注意这里只要遇到障碍物直接break,后面都为0
        for(int i = 0; i < row; i++) {
            if(obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
                break;
            }
        }
        
        for(int j = 0; j < col; j++) {
            if(obstacleGrid[0][j] == 0 ) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
                break;
            }
        }
        
        //转移方程
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[row-1][col-1];
    }
}