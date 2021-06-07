class _418_SentenceScreenfitting {
//Solution2.
    
    public int wordsTyping(String[] sentence, int rows, int cols) {
            int[] rowIdx = new int[sentence.length]; //每层开头在sentence中的index
            int[] times = new int[sentence.length]; //变数
            for(int i=0;i<sentence.length;i++) {
                int curLen = 0;
                int curIdx = i;
                int time = 0;
                while(curLen + sentence[curIdx].length() <= cols) {
                    curLen += sentence[curIdx++].length()+1;//+1 is space
                    if(curIdx==sentence.length) { //二次循环
                        curIdx = 0;
                        time ++;
                    }
                }
                rowIdx[i] = curIdx; //当前string不能放到当前row,就走下一排
                times[i] = time; //第i层times
            }
            int ans = 0;
            int cur = 0;
            for(int i=0; i<rows; i++) {
                ans += times[cur];
                cur = rowIdx[cur];
            }
            return ans;
        }

//Solution 1.
    public int wordsTyping(String[] sentence, int rows, int cols) {
        /*use all words in sentence as one component, walk screen(rows*cols), and handle cases:
            1.whole words no space at the end of each row
            2.more than one space at the end of each row
            Time = O(rows*cols) ; scan sentence O(n) less than rows*cols 
            Space = O(1) or length of all
        */
        String all = "";
        for(String ele : sentence) {
            all += (ele + " ");
        }
        int counter = 0; //可放入char个数
        int len = all.length();
        for(int i = 0; i < rows; i++) {
            counter += cols;
            int mod = counter % len; 
            if(all.charAt(mod) == ' ') { //1.whole words no space at the end of each row
                counter++; 
            } else{
                while(counter > 0 && all.charAt((counter-1) % len) != ' ') { //2.more than one space at the end of each row
                    counter--;
                }
            }
        }
        return counter / len;
    }
}
}