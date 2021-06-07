class _792_NumOfMatchingSubsequences {
    public int numMatchingSubseq(String S, String[] words) {
        //Time =  O(n * m * L) : n: length of words, m : S length, L: average word length
        //Space = map size and longest deque size

    
        //26个字母分别作为key加入map
        //words中，第一个字母对应加到map key中
        
        //只有当前就加进去
        //减去当前剩下的放到key, 再验证
        Map<Character, Deque<String>> map = new HashMap<>();
        for(char start = 'a'; start <= 'z'; start++) {
            map.putIfAbsent(start, new LinkedList<>());
        }
        
        for(String word: words) {
            char first = word.charAt(0);
            map.get(first).add(word);
        }
        
        //bfs
        int counter = 0;
        for(int i = 0; i < S.length(); i++) { //也可toCharArray
            char c = S.charAt(i);
            Deque<String> deque = map.get(c); //c对应的整个linkedlist都可以给deque, 新写法
            int size = deque.size();
            for(int j = 0; j < size; j++) {
                String curr = deque.removeFirst(); //removeFirst和getFirst区别
                if(curr.length() == 1) {
                    counter++;
                } else {
                    String next = curr.substring(1); //Substring写法
                    map.get(next.charAt(0)).add(next);
                }
            }
            
        }
        return counter;
    }   
}