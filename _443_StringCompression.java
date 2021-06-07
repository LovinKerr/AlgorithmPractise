class _443_StringCompression {

    public int compress(char[] chars) {
        //time= O(n)
        //Space = O(1) length of s
        int len = chars.length;
        int res = 0;
        for(int i = 0; i < len; i++) {
            int counter = 1; //如何求一个数字的位数
            char curr = chars[i];
            while(i+1 < len && chars[i] == chars[i+1]) {
                counter++;
                i++;
            }
            // res++;
            chars[res++] = curr;
            
            // if(curr!= chars[i]) {
            //     res += (int)Math.log10(counter) + 1;
            // }
            if(counter != 1) {
                for(char c : Integer.toString(counter).toCharArray()) {
                    chars[res++] = c;
                }
            }
        }
        return res;
    }

//错误，答案要求修改input，题目中没说清楚，但面试要问清楚return 什么
    // Note that group lengths that are 10 or longer will be split into multiple characters in chars.
    public int compress(char[] chars) {
        //time= O(n)
        //Space = O(n) length of s
        int len = chars.length;
        int res = 0;
        for(int i = 1; i < len; i++) {
            int counter = 0; //如何求一个数字的位数
            char curr = chars[i];
            while(i+1 < len && chars[i] == chars[i+1]) {
                counter++;
                i++;
            }
            res++;
            
            if(curr!= chars[i]) {
                res += (int)Math.log10(counter) + 1;
            }
        }
        return res;
    }
}