class _229_MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        //1.HashMap freq, > 1/3 length add to res
        //time= O(n), space = O(n) size of hashMap
        // corner case:
        // [3,2] -> [3,2]
        // [2,2] -> [2]
        // [2] -> [2]
        //[0,0,0] -> 在hashmap中操作注意不能重复加，用hashset查重
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        HashSet<Integer> set = new HashSet<>();
        if(len < 3) {
            for(int i : nums) {
                set.add(i);
            }
            List<Integer> list = new ArrayList<Integer>(set);
            return list;
        }
        double bar = (double) len/3;
        HashMap<Integer, Integer> eleToFreq = new HashMap<>();
        
        for(int i = 0; i < len; i++) {
            int curr = nums[i];
            if(!eleToFreq.containsKey(curr)) {
                eleToFreq.put(curr, 1);
            } else {
                int currFreq = eleToFreq.get(curr) + 1;
                eleToFreq.put(curr, currFreq);
                if(!set.contains(curr) && currFreq > bar) {
                    res.add(curr);
                    set.add(curr);
                }
            }
        }
        return res;
    }
}