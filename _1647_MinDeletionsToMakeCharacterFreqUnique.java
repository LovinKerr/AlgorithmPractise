
class _1647_MinDeletionsToMakeCharacterFreqUnique {
    //?2.第二种时间复杂度更高？

    // count each character first. For each 26 characters, check if it's count is already used. If so, delete characters until you find unused count, or reach zero.
    //Time = O(n) 第一个for loop走完s的长度
    //Space = O(1)
    public int minDeletions(String s) {
        int[] freq = new int[26];
        int res = 0;
        HashSet<Integer> used = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }
        
        for(int i = 0; i < 26; i++) { //0到25是26个字母
            int count = freq[i];
            while(count > 0 && !used.add(count)) { //不等于0或used不能被加入说明已存在此count
                count--; //一直找到一个存在的
                res++;
            }
        }
        return res;
    }

//Solution for saving space at time 
//  Example:  freq = [50, 50, 49, 6, 5]
// So for the second 50, exp = 49; And for 49, exp = 48;
// And for 6, exp = 47, but 47 > 6 let's re-adjust exp = 6.

//如果freq == 0前走
//前一个freq和后一个一样的话，后一个-1
//greedy? 这题为什么是greedy
    //Time = O(nLogN) sort了？ 加上第一个for loop走完整个s
    //Sapce = O(1) 26 is constant
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
		Arrays.sort(freq);
        int exp = freq[25];
        int res = 0;
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;
            if (freq[i] > exp) { //如果freq比exp大，更新freq
                res += freq[i] - exp;
            } else {
                exp = freq[i];//freq比exp小，更新exp
            }
            if (exp > 0) exp--; // Lowest exp is zero, cannot be negative
        }
        return res;
    }
}