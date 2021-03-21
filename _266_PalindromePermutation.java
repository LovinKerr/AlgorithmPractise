class _266_PalindromePermutation {
    //Solution 2.hashset, 有就remove，没有就add,最后set什么都没有或size 1就对了
        //time = O(n)
        //space = O(1) 26个字母
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(set.contains(curr)) {
                set.remove(curr);
            } else {
                set.add(curr);
            }
        }
        return set.size() == 0 || set.size() ==1;
        
    }
// Solution 1.hashmap: 其中有一个
//time :O(n) space O(1) : 26个字母 constant
         
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> charToFreq  = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(!charToFreq.containsKey(curr)) {
                charToFreq.put(curr,1);
            } else {
                charToFreq.put(curr, charToFreq.get(curr) + 1);
            }
        }
        int count = 0;
        for(Map.Entry<Character,Integer> entry: charToFreq.entrySet()) {
            int val = entry.getValue();
            if(val % 2 != 0) {
                count++;
            }
        }
        return count <= 1;
        
    }
}