class triangle120 {
//Solution dfs + memo 去重
	 public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] visited = new int[n][triangle.get(n - 1).size()];
        for(int[] ele : visited) {
            Arrays.fill(ele, Integer.MAX_VALUE);
        }
        // write your code here
        return dfsHelper(0,0,triangle, visited);
    }

    //return min path from(x,y) to bottom
    private int dfsHelper(int x, int y, List<List<Integer>> triangle, int[][] visited ) {
        if(x == triangle.size()) {
            return 0;
        }
        int currVal = triangle.get(x).get(y);
        //每次选小的
        if(visited[x][y] == Integer.MAX_VALUE) {
            visited[x][y] = currVal + Math.min(dfsHelper(x + 1, y, triangle, visited), dfsHelper(x+1,y+1,triangle, visited));
        }
        return visited[x][y];
    }


//Solution 去掉重复计算
	//Time = O(2^n)
    public int minimumTotal2(List<List<Integer>> triangle) {
    
    // write your code here
    return dfsHelper(0,0,triangle);
}

//return min path from(x,y) to bottom
	private int dfsHelper(int x, int y, List<List<Integer>> triangle) {
	    if(x == triangle.size()) {
	        return 0;
	    }
	    
	    int currVal = triangle.get(x).get(y);
	    //每次选小的
	    return currVal + Math.min(dfsHelper(x + 1, y, triangle), dfsHelper(x+1,y+1,triangle));
	}

//simple dfs solution
	//Time = O(2^n)

    int min = Integer.MAX_VALUE;
    public int minimumTotal1(List<List<Integer>> triangle) {
        
        // write your code here
        dfs(0,0,0,triangle);
        return min;
    }

    private void dfs(int x, int y, int sum, List<List<Integer>> triangle) {
        if(x == triangle.size()) {
            //update: find whole path from top to bottom
            min = sum < min ? sum : min;
            return;
        }
        int currVal = triangle.get(x).get(y);
        dfs(x + 1, y, sum + currVal, triangle);
        dfs(x + 1, y + 1, sum + currVal, triangle);
    }
}