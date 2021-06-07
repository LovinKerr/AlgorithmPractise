class _1048_LongestStringChain {
    //HashMap 存size to list of string
    //dfs
    //走hashmap, check if valid, O(n * m) 存map,  m is max length of string, n is max 16
    //Space = O(N)? hashmap?
    
    //最长路bfs？
    public int longestStrChain(String[] words) {
        Map<Integer, List<String>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for(String i : words) {
            int size = i.length();
            min = Math.min(size, min);
            if(!map.containsKey(size)) {
                map.put(size, new ArrayList<String>); //* map中放new list
            }
            map.get(size).add(i);
        }
        int max = 0;
        for(String i : words) {
            max = Math.max(max, helper(map, i));
        }
            
        return max;
        
        
    }
    //curr max size
    private int helper(Map<Integer, List<String>> map, String curr) {
        
    }
    
    //check if a is b's predecessor
    private boolean isPre(String a, String b) {
        
    }
}