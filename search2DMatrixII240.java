class search2DMatrixII240 {
// Search Space Reduction
    //Time = O(n + m)
    //space = O(1)
    //分析为什么这么走？
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        int i = 0;
        int j = col - 1;
        while(i < row && j >= 0) {
            int compare = matrix[i][j];
            if(target > compare) {
                i++; //往下走
            } else if( target < compare) {
                j--;
            } else if(target == compare) {
                return true;
            }
        }
        return false;
    }
}