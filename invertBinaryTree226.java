class invertBinaryTree226 {
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
            TreeNode temp = curr.left;
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

//DFS
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
}