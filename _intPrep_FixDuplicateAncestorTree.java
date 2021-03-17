public class _intPrep_FixDuplicateAncestorTree {
    //time = o(n) max
    //space = o(n) max for set and recursion

    public TreeNode fixTree(TreeNode root) {
        HashSet<TreeNode> set = new HashSet<>();
        set.add(root);
        helper(root, set);
        return root;
    }
    private void helper(TreeNode node,HashSet<TreeNode> set) {
        if(node == null) {
            return;
        }
        if(!set.contains(node.left)) {
            set.add(node.left);
            helper(node.left, set);
        } else {
            node.left = null;
            return;
        }
        
        if(!set.contains(node.right)) {
            set.add(node.right);
            helper(node.right, set);
        } else {
            node.right = null;
            return;
        }
    }
}