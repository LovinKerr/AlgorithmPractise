class ContiguousArray525 {
    //易错点：map.put(0,-1) : dummy: for the next first 0, len
    

    //Time = O(n)
    //Space = max O(n): size of map
    
    //Solution1. without change nums in place
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); //num to first index
        int maxLen = 0;
        //dummy 0
        map.put(0, -1);
        int sum = 0; //cal preSum may not use an array example, here just use an update integer
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if(map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
    //Time = O(n)
    //Space = O(n)

    //Solution2. change nums inplace 
    public int findMaxLength(int[] nums) {
        //preSum
        //HashMap
        //convert nums into -1,1
        if(nums == null) {
            return 0;
        }
        
        int preSum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                nums[i] = -1;
            }
            nums[i] += preSum;
            preSum = nums[i];
        }
        
        HashMap<Integer, Integer> numToFirstIndex = new HashMap<>();
        //dummy sum 0
        numToFirstIndex.put(0,-1);
        int len = 0;         //the len between same num would be valid

        for(int i = 0; i < nums.length; i++) {
            if(numToFirstIndex.containsKey(nums[i])) {
                len = Math.max(len, i - numToFirstIndex.get(nums[i]));
            } else {
                numToFirstIndex.put(nums[i], i);
            }
        }
        return len;
    }


}