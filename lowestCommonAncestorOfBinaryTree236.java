class lowestCommonAncestorOfBinaryTree236 {
    
//Time = O(n) n is num of node
//Space = O(n) worst case
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ||root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right != null) {
            return root;
        } else if(left != null) {
            return left;
        }
        return right;
    }
}