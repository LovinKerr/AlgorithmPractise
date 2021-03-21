class _1448_countGoodNodesInBinaryTree {
    //Time = O(n) 每个node都走一遍
    //Space = O(logN), max O(n) recursion stack size
    int count = 0;
    public int goodNodes(TreeNode root) {
        // Top down记录path走到现在最大值
        helper(root, Integer.MIN_VALUE);
        return count;
    }
    private void helper(TreeNode node, int max) {
        if(node == null) {
            return;
        }
        if(node.val >=max) {
            count++;
            max = node.val;
        }
        helper(node.left, max);
        helper(node.right, max);
    }
}