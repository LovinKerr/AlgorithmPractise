class _347_TopKFrequentElements {

//Solution 2. Bucket sort
    //Time O（n)  map的size， bucket size， 有为null的cell， 有包含list的cell
    //Space O(n)
    //Hashmap求freq， bucket sort
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>(); //ele to freq
        int len = nums.length;
        
        for(int i = 0; i < len; i++) {
            int curr = nums[i];
            if(!map.containsKey(curr)) {
                map.put(curr, 0);
            }
            map.put(curr, map.get(curr) + 1);
        }
        List<Integer>[] bucket = new ArrayList[len + 1]; //+1是考虑nums只有一个数时corner case
        for(Map.Entry<Integer,Integer> ele : map.entrySet()) {
            int curr = ele.getKey();
            int freq = ele.getValue();
            if(bucket[freq] == null) {
                // ArrayList<Integer> list = new ArrayList<>();
                // bucket[freq] = list;
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(curr);
        }
        int[] res = new int[k];
        // List<Integer> res = new ArrayList<>();
        int idx = 0;
        for(int i = bucket.length - 1; i >= 0; i--) {
            if(bucket[i] != null) {
                for(int element : bucket[i]) {
                    if(idx >= k) { //注意这里 idx最多到k
                        break;
                    }
                    res[idx++] = element;
                }
            }

        }
        return res;

    }

//Solution 3. Hashmap求freq， pq存 前k位
    //Time = O(nLogK)
    //Space = O(n)
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>(); //ele to freq
        
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if(!map.containsKey(curr)) {
                map.put(curr, 0);
            }
            map.put(curr, map.get(curr) + 1);
        }
        
        //freq小的在上面
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) ->map.get(a) - map.get(b));
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry.getKey());
            if(pq.size() > k) {
                pq.poll();
            }
        }
        
        int[] res = new int[k];
        int idx = 0;
        while(!pq.isEmpty()) {
            res[idx++] = pq.poll();
        }
        return res;
    }
}