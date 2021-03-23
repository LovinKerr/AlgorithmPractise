class _1578_MinDeletionCostToAvoidRepeatingLetters {
    // 解题思路：在一组相同char里，删除全部保留一个（要想删得最少，保留最大的）-> 
    // cost = allCost - maxCost
    //Time = O(n) s.length
    //Space = O(1)
    
    public int minCost(String s, int[] cost) {
        int sum = 0;
        int max = 0;
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            //每当找到一个不等的，结算
            if( i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                res += (sum - max);
                sum = max = 0;
            }
            sum += cost[i];
            max = Math.max(max, cost[i]);
        }
        //最后一次没有if要记得加上
        res+=(sum - max);
        return res;
    }
}
//错误
    // 解题思路：在一组相同char里，删除全部保留一个，cost = allCost - maxCost
    public int minCost(String s, int[] cost) {
        int res = 0;
        int len = s.length();
        for(int i = 1; i < len; i++) {
            int sum = cost[i];
            int max = 0;
            while(i < len && s.charAt(i) == s.charAt(i - 1)){
                sum += cost[i];
                max = Math.max(max, Math.max(cost[i - 1], cost[i]));
                i++;
            }
            if(max != 0) {
                i--;
                res += (sum - max);
            }
        }
        return res;
    }
}