class maxAvgSubtree1120 {
    //Time = O(n) 每个点都走到
    //Space = O（logN) for average, O(n) for worst case for skewed tree; generally recursion 深度
    //bottom up, but use variable as res, merged with top down
    double maxAvg = 0;
                
    public double maximumAverageSubtree(TreeNode root) {
        //从下到上记录sum, count, global记一个avg
        
        helper(root);
        return maxAvg;

    }
    private long[] helper(TreeNode node) {
        if(node == null) {
            long[] temp = new long[] {0,0};
            return temp;
        }
        
        long[] left = helper(node.left);
        long[] right = helper(node.right);
        
        long sum = left[0] + right[0] + node.val;
        long count = left[1] + right[1] + 1;
        long[] curr = new long[2];
        curr[0] = sum;
        curr[1] = count;
        
        //注意long 也要换算成double
        double avg = (double)sum/count;
        
        maxAvg = avg > maxAvg ? avg: maxAvg;
        
        return curr;
    }
}