class balancedBinaryTree110 {
    //time = O(NlgN)
    //space = recursion stack
    public boolean isBalanced(TreeNode root) {
        //寻找规律 bottom up
        if(root == null) {
            return true;
        }
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        if(Math.abs(left - right) > 1) {
            return false;
        }
        
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int dfs(TreeNode node){
        if(node == null) {
            return 0;
        }
        
        int left = dfs(node.left);
        int right = dfs(node.right);
        
        return Math.max(left, right) + 1;
    }
}