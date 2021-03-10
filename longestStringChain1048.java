class LongestStringChain1048 {
//optimized solution
    

//朴素dp solution

    //Time = O(n^2) => sort: O(nlogn), for loop O(n^2), check valid: length of short word, constant
    //Space = O(n) length of the words
    
    //word1 + char = word2 (word1中字母顺序不变)
    //2个solution： dp is validate
    //最优： 枚举26个字母分别放在每个位置
    //短一位才有可能是长的predecessor
    public int longestStrChain(String[] words) {
    //判断 isPre
    //初始状态
        
        Arrays.sort(words, (a,b) -> a.length() - b.length()); // array sort comparator
        int[] dp = new int[words.length];
        // dp记录当前位置有多长的chain
        int max = 0; //最后结果是看最大，不是dp数组最后一位
        dp[0] = 1; //初始状态
        
        for(int i = 1; i < words.length; i++) { //从1开始走的原因？
            int val = 0; //如果在curr pos之前找不到任何pre
            for(int k = 0; k < i; k++) {
                // 转移方程，如果在前面任意找到一个pre,记录，走完0-k，记录最大的
                if(isPre(words[k], words[i])) {
                    val = Math.max(dp[k], val);
                    // val = dp[k];
                }
            }
            //加上自己
            dp[i] = val + 1;
            max = dp[i] > max ? dp[i] : max;
        }
        return max;
    }
    
    private boolean isPre(String shortWrd, String longWrd){
        if(longWrd.length() - shortWrd.length() != 1) {
            return false;
        }
        int i = 0; 
        int j = 0;
        while(i < shortWrd.length() && j < longWrd.length()) {
            if(shortWrd.charAt(i) == longWrd.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        //pre（短的）走完了就符合条件
        return i == shortWrd.length();
    }
}