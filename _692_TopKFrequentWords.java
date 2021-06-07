class _692_TopKFrequentWords {

//Solution 1.1 heap 里放入String
    /*
    注意priorityqueue中comparator的存放顺序和最终结果是反的
    */
    //to save time, use Priority Queue and reduce the time to O(nLogK)
    //Time = O (nlogN) --> O(nLogK)
    //Space = O(n)
    public List<String> topKFrequent(String[] words, int k) {
        
        HashMap<String, Integer> map = new HashMap<>();
        int len = words.length;
        for(int i = 0; i < len; i++) {
            String curr = words[i];
            if(!map.containsKey(curr)) {
                map.put(curr, 0);
            }
            map.put(curr, map.get(curr) + 1);
        }
        
        PriorityQueue<String> pq = new PriorityQueue<>( (a,b) -> map.get(a).equals(map.get(b)) ?
                                                       b.compareTo(a) :  //alphebeta order呈现最后结果，这里加到pq中，是反的， 所以love 与i 相比， love在heap top
                                                       map.get(a) - map.get(b));
        
        for(String word : map.keySet()) {
            pq.offer(word);
            if(pq.size() > k) {
                pq.poll();
            }
        }
                                                                           
        LinkedList<String> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.addFirst(pq.poll());
        }
                                                                           
        return res;
    }

//Solution 1.2 heap里放入hashmap entry
    //to save time, use Priority Queue and reduce the time to O(nLogK)
    /*
    注意priorityqueue中comparator的存放顺序和最终结果是反的
    */
    //Time = O (nlogN) --> O(nLogK)
    //Space = O(n)
    public List<String> topKFrequent(String[] words, int k) {
        
        HashMap<String, Integer> map = new HashMap<>();
        int len = words.length;
        for(int i = 0; i < len; i++) {
            String curr = words[i];
            if(!map.containsKey(curr)) {
                map.put(curr, 0);
            }
            map.put(curr, map.get(curr) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>( (a,b) -> a.getValue().equals(b.getValue()) ? //比较a,b的频率
                                                                          b.getKey().compareTo(a.getKey()) : //如果相同则比较a,b 两个string
                                                                          a.getValue() - b.getValue()); //如果不同，则表示minheap，小的在前面，大的在后面
        
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry); 
            if(pq.size() > k) {
                pq.poll();
            }
        }
                                                                           
        LinkedList<String> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.addFirst(pq.poll().getKey());
        }
                                                                           
        return res;
    }

//Solution2. 
    // k's most frequency
    //sort by freq, if same freq, sort by alphabetical order
    //Hashmap + sort
    //Time = O (nlogN)
    //Space = O(n)
    public List<String> topKFrequent(String[] words, int k) {
        
        HashMap<String, Integer> map = new HashMap<>();
        int len = words.length;
        for(int i = 0; i < len; i++) {
            String curr = words[i];
            if(!map.containsKey(curr)) {
                map.put(curr, 0);
            }
            map.put(curr, map.get(curr) + 1);
        }
        
        List<String> res = new LinkedList<>(map.keySet()); //把map中的key都加入res
        
        Collections.sort(res, (a, b) -> map.get(a).equals(map.get(b)) ? //如果频率相等
                        a.compareTo(b) : //a与b 字母排序
                        map.get(b) - map.get(a)); //否则从大到小
        
        return res.subList(0,k);
    }
}