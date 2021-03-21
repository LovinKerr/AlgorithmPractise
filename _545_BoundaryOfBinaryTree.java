class _545_BoundaryOfBinaryTree {
    //时间 O（n)每个点走了两遍，boundary/leaf
    //space O(n) 最大list size
    
    // 逆时针的顺序来输出树的边界
    List<Integer> list = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        list.add(root.val);
        //逆时针找
        //只走左子树
        getLeftBound(root.left);
        getLeaves(root.left);
        //只走右子树
        getLeaves(root.right);
        getRightBound(root.right);
        return list;
    //3个
    }
    private void getLeftBound(TreeNode node){
        //node不存在或是leaf，不走
        if(node == null || (node.left == null && node.right == null)) {
            return;
        }
        //先走自己从上到下
        list.add(node.val);
        if(node.left == null) {
            getLeftBound(node.right);
        } else {
            getLeftBound(node.left);
        }
    }
    
    private void getRightBound(TreeNode node){
        if(node == null || (node.right == null && node.left == null)) {
            return;
        }
        //先走孩子从下到上（逆时针）
        if(node.right == null) {
            getRightBound(node.left);
        } else {
            getRightBound(node.right);
        }
        list.add(node.val);// add after child visit(reverse)
    }
    
    private void getLeaves(TreeNode node){
        // null corner case
        if(node == null) {
            return;
        }
        //是leaf
        if(node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        //不是leaf往下走
        getLeaves(node.left);
        getLeaves(node.right);
    }
}