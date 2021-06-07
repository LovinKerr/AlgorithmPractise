class _394_DecodeString {
    //走string s, 数字
    public String decodeString(String s) {
        String res = "";
        String temp = "";
        int times = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                times = times * 10 + c - '0';
            } else if(Character.isLetter(c)) {
                temp = temp + c;
            } else if(c == ']') {
                for(int j = 0; j < times; i++) {
                    res += temp;
                }
                times = 0;
                temp = "";
            }
        }
        return res;
    }
}