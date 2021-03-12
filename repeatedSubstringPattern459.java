class repeatedSubstringPattern459 {
    //1. RegEx正则表达式
    //2. Find Divisors + Rabin-Karp
    //3. Knuth-Morris-Pratt Algorithm (KMP)

//Solution: 优化
    //Time = O(n) 最大？,虽然有两个for loop, 但k只会执行valid的时候？ 还是O（n)
    //Space = O(1) or O(n) Substring
    public boolean repeatedSubstringPattern(String s) {
    
        int len = s.length();
        if( len == 1) {
            return false;
        }

        //至少是一半，所以从一半的substring个数查找起来，只要存在一个，长度能被len整除，且按倍数拼接的str和input完全相同，则成立
        for(int i = len /2 ; i >= 1 ; i--) {
            if(len % i == 0) {
                int times = len / i;
                String part = s.substring(0, i);
                int k;
                for(k = 1; k < times; k++) {
                    String temp = s.substring(k*i, k*i + i); //判断中间的每一个part是否相等，可以不用append
                    if(!part.equals(temp)) { //中间这个part
                        break;//走出for loop
                    }
                }
                if(k == times) {
                    return true;
                }
            }
        }
        return false;
    }

//Solution. 我能想到明白的一种
//Time = O(n^2) 最大？,虽然有两个for loop, 但k只会执行valid的时候？ 还是O（n)
//Space = O(1) or O(n) Substring
    public boolean repeatedSubstringPattern(String s) {
//化验证为查找
        int len = s.length();
        if( len == 1) {
            return false;
        }
        //至少是一半，所以从一半的substring个数查找起来，只要存在一个，长度能被len整除，且按倍数拼接的str和input完全相同，则成立
        for(int i = len /2 ; i >= 1 ; i--) {
            if(len % i == 0) {
                int times = len / i;
                StringBuilder sb = new StringBuilder();
                String part = s.substring(0, i);
                for(int k = 0; k < times; k++) {
                    sb.append(part);
                }
                if(sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}

//思考许久的Solution Sliding Window
    /*Time = O(m*n) where m and n are length of 2 strings, best O(m +n)
    A completely naive implementation might take (n+1-k)*kcomparisons to decide that a given string of length n does not contain a particular substring of length k. That's O(nk) for the worst case.
    Space = string的长度
    */
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        //位移一位还是成立的话
        return str.substring(1, str.length() - 1).contains(s);
    }

//不能count个数，错误
    public boolean repeatedSubstringPattern(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(!map.containsKey(curr)) {
                map.put(curr,1);
            } else {
                map.put(curr, map.get(curr) + 1);
            }
        }
        
        int check = 0;
        for(Map.Entry<Character,Integer> ele : map.entrySet()) {
            int freq = ele.getValue();
            if(check == 0) {
                check = freq;
                continue;
            }
            if(check % freq == 0 || freq % check == 0) {
                continue;
            } else{
                return false;
            }
        }
        return true;
    }
}