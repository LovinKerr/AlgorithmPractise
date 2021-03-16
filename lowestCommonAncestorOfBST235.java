class lowestCommonAncestorOfBST235 {
//BFS    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //Time = O(n)
        //Space = O(1)
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;

        while(root != null) {
            int cVal = node.val;
            if(pVal > cVal && qVal > cVal) {
                node = node.right;
            } else if(pVal < cVal && qVal < cVal) {
                node = node.left;  
            } else {
                return node;
            }
        }
        return null;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//DFS: 
        //Time = O(n) worst case 走整树
        //Space = O(n) because the maximum amount of space utilized by the recursion stack would be NN since the height of a skewed 歪曲BST could be NN.
        if(root == null) {
            return null;
        }
        int pVal = p.val;
        int qVal = q.val;
        int cVal = root.val;
            //1.如果p,q同时大于root,走右边
            if(pVal > cVal && qVal > cVal) {
                return lowestCommonAncestor(root.right, p, q);
            } else if(pVal < cVal && qVal < cVal) { //2.如果p,q同时小于root,左边
                return lowestCommonAncestor(root.left, p, q);
            } else { //3.If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's subtrees. and hence we return this common node as the LCA
                return root;
            }

        /* 错误
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        
        if(left != null && right != null && left.val < root.val && right.val > root.val) {
            return root;
        } else if(p.val > root.val && q.val > root.val) {
            return right;
        } 
        return left;*/
    }
}