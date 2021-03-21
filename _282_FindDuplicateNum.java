class _282_FindDuplicateNum {
    //Time = O(n) max
    //Space = O(n)
    public int findDuplicate(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if(set.contains(curr)) {
                res = curr;
                break;
            } else {
                set.add(curr);
            }
        }
        return res;
    }
}