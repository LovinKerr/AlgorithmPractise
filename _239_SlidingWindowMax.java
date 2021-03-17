class _239_SlidingWindowMax {
//TreeSet    
    //Time = O(nLogN) 走n个点，每个点add一遍
    //Space = O(k) largest size of set
    //用treeMap 保存滑动窗口中的元素index； 技巧是key的comparator比较nums[o1],nums[o2]
    public int[] maxSlidingWindow(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>((o1,o2) -> 
                                             nums[o1] != nums[o2] 
                                             ? Integer.compare(nums[o1], nums[o2])  
                                             : Integer.compare(o1,o2));
        
        int len = nums.length;
        int[] res = new int[len - k + 1];
        //走nums,把index加紧set， 加到开始移动窗口后要remove前一位
        //记录最大，set比较的是值，传入的是index，因此拿到set last的index所对应值为最大
        for(int i = 0; i < len; i++) {
            set.add(i);
            if(i >= k) {
                set.remove(i - k); //移动走 index(i - k)
            }
            //加入res
            if(i - k + 1 >= 0) {
                res[i - k + 1] = nums[set.last()];
            }
        }
        return res;
    }
}