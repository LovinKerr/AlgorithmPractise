class _226_invertBinaryTree {
//BFS
    //Time = O(n) scan each node
    //Space = O(n) queue's size, contains all node in one layer
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            TreeNode temp = curr.left;//1.为什么这里可以不check null
            curr.left = curr.right;
            curr.right = temp;
            
            if(curr.left != null) {
                queue.offer(curr.left);
            }
            if(curr.right != null) {
                queue.offer(curr.right);
            }
        }
        return root;
    }

//DFS Bottom Up
    //Time = O(n) scan each node
    //Space = O(logN) stack of recursion
    public TreeNode invertTree(TreeNode root) {
        //Top Down
        //recursion 当前把自己左右换一下
        if(root == null) {
            return null;
        }
        //从之前拿参数
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        //当前层操作
        root.left = right;
        root.right = left;
        return root;
    }

//Top Down,从上到下，每次都交换下
    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
        
    }
    private void helper(TreeNode node) {
        if(node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        helper(node.left);
        helper(node.right);
    }
}