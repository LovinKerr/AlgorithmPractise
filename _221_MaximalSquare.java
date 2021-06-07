class _221_MaximalSquare {
//Solution 2.
    //time = O(m*n) 每个点都走到
    //space = O(n) n is col length of matrix, 

    //dp[i][j] 代表以此cell为右下的长方形最长边长
    
    //方法：服用两行int[][] dp = new int[2][col + 1]; 根绝dp的值只决定于前一行和前一个
    //注意，服用要把不满足条件的地方改为0，以免出错
    //注意：输入参数是个char array非int array
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        int max = 0;
        
        int[][] dp = new int[2][col + 1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == '1') {
                    dp[(i+1) % 2][j+1] = Math.min (dp[i%2][j], Math.min(dp[i%2][j+1], dp[(i+1) % 2][j])) + 1;
                    max = Math.max(dp[(i+1) % 2][j+1], max);
                }else {
                    dp[(i+1) % 2][j+1] = 0;
                }
            }
        }
        return max * max;

    }

//Solution 1.
//dp[i][j] 代表以此cell为右下的长方形最长边长
    
    //time = O(m*n) 每个点都走到
    //space = O(mn) for dp matrix
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        int max = 0;
        
        int[][] dp = new int[row + 1][col + 1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == '1') {
                    dp[i+1][j+1] = Math.min (dp[i][j], Math.min(dp[i][j+1], dp[i+1][j])) + 1;
                    max = Math.max(dp[i + 1][j + 1], max);
                }
            }
        }
        return max * max;
        
    }
}