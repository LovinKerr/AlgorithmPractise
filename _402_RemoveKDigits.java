class _402_RemoveKDigits {
	    public String removeKdigits(String num, int k) {
        // 为了提高效率，先将字符串转为数组
        char[] arr=num.toCharArray();
        Stack<Character> s = new Stack<>();
        // 循环每个字符
        for(int i=0;i<num.length();i++){
            // 如果栈元素个数大于0并且k大于0，并且当前元素大于栈顶元素
            while(s.size()>0&&k>0&&arr[i]<s.peek()){
                s.pop(); // 栈顶元素pop出栈
                k--; // k减一
            }
            // 如果栈内元素个数为0，并且当前数字是0，跳过（前导零）
            if(s.size()==0&&arr[i]=='0') continue;
            // 将当前数字入栈
            s.push(arr[i]);
        }
        // 如果k仍大于0
        while(k>0&&s.size()>0){
            // 从栈顶pop出k个最大数
            s.pop();
            k--;
        }
        // 如果栈内元素个数为空，返回0
        if(s.size()==0) return "0";
        // 将栈内元素组成字符串返回
        StringBuilder sb=new StringBuilder();
        while(s.size()>0) sb.append(s.pop());
        sb.reverse();
        return sb.toString();
    }
	//错误写法： NumberFormatException
    //每种可能性比较一遍
    //Time = O(m)
    //Space = O(n)
    public String removeKdigits(String num, int k) {
        int len = num.length();
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        for(int i = 1; i < len; i++) {
            //corner case: 0 couldn't sit in the first place
            if (stack.size() <= 0 && num.charAt(i) == 0) {
                continue;
            }
            
            //高位数大
            while(!stack.isEmpty() && num.charAt(i) < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            //高位数小
            if(stack.isEmpty() || (!stack.isEmpty() && num.charAt(i) >= stack.peek())) {
                stack.push(num.charAt(i));
                continue;
            }
            if(k == 0) {
                stack.push(num.charAt(i));
            }
        }
        //make sure stack's size is len - k 
        while(k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        String res = "";
        //convert
        if(stack.size() == 0) {
            return "0";
        } else {
            while(!stack.isEmpty()) {
                // if(stack.size() == 1 && stack.peek() == '0' && res != "") { //第一位，即stack底层
                //     char c = stack.pop();
                // }  
                // else if(stack.size() == 1 && stack.peek() == '0' && res == "") {
                //     return "0";
                // }else {
                    char c = stack.pop();
                    res = c + res;
                // }
            }
            
        }
        return Integer.parseInt(res) + "";
    } 
}

             public String removeKdigits(String num, int k) {
        int len = num.length();
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        for(int i = 1; i < len; i++) {
            //高位数大
            while(!stack.isEmpty() && num.charAt(i) < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            //corner case: 0 couldn't sit in the first place
            if (stack.size() == 0 && num.charAt(i) == '0') {
                continue;
            }
            //高位数小
            stack.push(num.charAt(i));
            //could combine
            // if(stack.isEmpty() || (!stack.isEmpty() && num.charAt(i) >= stack.peek())) {
            //     stack.push(num.charAt(i));
            //     continue;
            // }
            // if(k == 0) {
            //     stack.push(num.charAt(i));
            // }
        }
        //make sure stack's size is len - k 
        while(k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        //convert
        if(stack.size() == 0) {
            return "0";
        } 
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    } 

/*Test

"1432219"
3
"10200"
1
"10"
2
"10"
1
"5337"
2
"43214321"
4
*/