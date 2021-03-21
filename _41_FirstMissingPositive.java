class _41_FirstMissingPositive {

//Time = O(n)
//Space = O(n)
//nums放到hashset中，查找，有就往下，没有就return
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int negCounter = 0;
        int max = 0;
        for(int i : nums) {
            if(i > 0) {
                set.add(i);
                max = Math.max(max,i);
            }else {
                negCounter++;
            }
        }
        int posLen = nums.length - negCounter;
        for(int i = 1; i <= posLen; i++) {
            if(!set.contains(i)){
                return i;
            }
        }
        return max+1;
    }

//TEL error
//1.time = o(nlogN) -> sort, loop nums, if any, return, if not, the smallest one after nums
//space = O(1)
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int count = 1;
        for(int i = 0; i < len; i++) {
            int curr = nums[i];
            while(curr < 1) {
                continue;
            }
            if(curr == count) {
                count++;
            } else {
                return count;
            }
        }
        return nums[len - 1] + 1;
    }
}