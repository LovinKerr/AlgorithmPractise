class _298_BinaryTreeLongestConsecutiveSequence {
//Top down: 记录当前的个数，target（curr.val + 1), 是就+1， 不是就从头开始记录
    //Time = O(n) 每个节点都走了
    //Space = O(logn) to O(n) stack深度
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        //global record一个最长
        //count
        helper(root, 0, 0);
        return max;
    }
        //带着target寻找
    private void helper(TreeNode node, int count, int target) {
        if(node == null) {
            return;
        }
        
        if(node.val == target) {
            count++;
        } else {
            count = 1;
        }
        max = Math.max(count, max);
        
        helper(node.left, count, node.val + 1);
        helper(node.right, count, node.val + 1);
    }

//Bottom up
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root, 0);
        return max;
    }
    private int helper(TreeNode node, int count){
        if(node == null) {
            return 0;
        }
        int left = helper(node.left, count);
        int right = helper(node.right, count);
        
        //handle self
        if(node.left != null && node.right != null && node.val == node.left.val - 1 && node.val == node.right.val - 1) {
            count = Math.max(left, right) + 1;

        } else if(node.left != null && node.val == node.left.val - 1) {
            count = left + 1;
        } else if(node.right != null && node.val == node.right.val - 1) {
            count = right + 1;
        } else {
            count = 1;
        }
        max = Math.max(count, max);
        return count;
    }
}