class _1277_CountSquareSubmatricesWithAllOnes {

//Solution 2.
    //matrix[i][j] records以此cell为右下的最长正方形边长
    //同时表示以此cell为右下的正方形个数
    //按照这道题把matrix中所有数加起来
    
    //implement by change number in input,
    //当input 不是最小col，row时，计算他们的状态值
    //当是最小col和row，只需直接加上当前值（1）
    
    //Time = O(m*n)
    //Space = O(1)

    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i > 0 && j > 0 && matrix[i][j] > 0) {
                    matrix[i][j] = Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i][j-1])) + 1;
                }
                res += matrix[i][j];
            }
        }
        return res;
    }
}

//Solution 1.
    //dp[i][j] records以此cell为右下的最长正方形边长
    //同时表示以此cell为右下的正方形个数
    //按照这道题把dp中所有数加起来
    
    //Time = O(m*n)
    //Space = O(m*n)
    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        int[][] dp = new int[row + 1][col + 1];
        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 1) {
                    dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i+ 1][j], dp[i][j+1])) + 1;
                    res += dp[i+1][j+1];
                }
            }
        }
        return res;
    }
}