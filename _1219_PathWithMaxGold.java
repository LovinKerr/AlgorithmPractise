class _1219_PathWithMaxGold {

//Solution 2.
    // 走每一个非0点，update此点开始下最大的gold，再update所有点中最大gold
    // 不用boolean[][] 记录visited，只直接改动当前grid为0， 然后backtrack时改回来
    //Time = 
    //Space = O(logN) stack深度
    int[][] direction = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    //从任何位置开始和结束
    //每个非0点开始走，走到上下左右都不能走结束，update 最大gold
    public int getMaximumGold(int[][] grid) {
        //recursion 走grid中所有非0点
        //记录max
        //Time = O(n^2) ? O(n) 最大25个cell
        int rowL = grid.length;
        int colL = grid[0].length;
        int max = 0;
        for(int i = 0; i < rowL; i++) {
            for(int j = 0; j < colL; j++) {
                int curr = grid[i][j];
                if(curr > 0) {
                    int gold = helper(grid, i, j);
                    max = Math.max(max, gold);
                }
            }
        }
        return max; 
    }
    //找到从这个点出发，max gold
    private int helper(int[][] grid, int i, int j) {

        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int curr = grid[i][j];
        grid[i][j] = 0;
        int nextMax = 0;

        for(int[] dir : direction) {
            int nextRow = i + dir[0];
            int nextCol = j + dir[1];
            nextMax = Math.max(nextMax, helper(grid, nextRow, nextCol)); //注意这里上下左右只能走一步最大的，每步都走，但只保留最大的
        }
        grid[i][j] = curr;
        return curr + nextMax;
    }

//Solution 1.
    // 走每一个非0点，update此点开始下最大的gold，再update所有点中最大gold
    // 用boolean[][] 记录visited
    /*
    注意：backtrack回来的时候要把当前点标记为每走过
    */
    //time = 
    //Space = O(m*n)
    int[][] direction = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    //从任何位置开始和结束
    //每个非0点开始走，走到上下左右都不能走结束，update 最大gold
    public int getMaximumGold(int[][] grid) {
        //recursion 走grid中所有非0点
        //记录max
        //Time = O(n^2) ? O(n) 最大25个cell
        int rowL = grid.length;
        int colL = grid[0].length;
        int max = 0;
        for(int i = 0; i < rowL; i++) {
            for(int j = 0; j < colL; j++) {
                int curr = grid[i][j];
                if(curr > 0) {
                    boolean[][] visited = new boolean[rowL][colL];
                    int gold = helper(grid, i, j, visited);
                    max = Math.max(max, gold);
                }
            }
        }
        return max; 
        
    }
    //找到从这个点出发，max gold
    private int helper(int[][] grid, int i, int j, boolean[][] visited) {

        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int nextMax = 0;
        int curr = grid[i][j];
        
        for(int[] dir : direction) {
            int nextRow = i + dir[0];
            int nextCol = j + dir[1];
            // if( nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length || !visited[nextRow][nextCol]) {
            nextMax = Math.max(nextMax, helper(grid, nextRow, nextCol, visited)); //注意这里上下左右只能走一步最大的，每步都走，但只保留最大的
            // }
        }
        visited[i][j] = false; //backtrack
        return curr + nextMax;
    }
}