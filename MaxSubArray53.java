class MaxSubArray53 {
//Greedy
    public int maxSubArray(int[] nums) {

        int max = nums[0];
        int sum = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            max = Math.max(sum, max);
        }
        return max;
    }


    public int maxSubArray(int[] nums) {

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int counter = 0;
        int maxNeg = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if(curr < 0) {
                maxNeg = Math.max(maxNeg, curr);
                counter++;
            }
            if(sum + curr > 0) {
                sum += curr;
            } else {
                sum = 0;
            }
            max = Math.max(sum, max);
        }
        if(counter == nums.length) {
            return maxNeg;
        } 
        return max;
    }
}