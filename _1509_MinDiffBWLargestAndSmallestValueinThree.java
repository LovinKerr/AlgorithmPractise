class _1509_MinDiffBWLargestAndSmallestValueinThree {
    public int minDifference(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if (len <= 4) {
            return 0;
        }
        //Sliding Window
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= len - 3; i++) {
            min = Math.min(min, nums[len - 4 + i] - nums[i]);
        }
        
        return min;
        //改前三位
//         int min = nums[0];
//         int max = nums[len - 1];
//         int small = nums[3];
//         int big = nums[len - 4];
        
//         int res = 0;
//         if(max - small > big - min) {
//             res = big - min;
//         } else {
//             res = max - small;
//         }
//         return res;
    }
}