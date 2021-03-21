class _1576_ReplaceAllQuestionMarkToValid {
    //Time = O(n) scan once
    //space = O(n) arr.size
    public String  modifyString(String s) {
        //1.convert to array which is convinient to modify
        char[] arr = s.toCharArray();
        //2.loop to get ?
        int len = s.length();
        for(int i = 0; i < len; i++) {
            if(arr[i] == '?') {
                for(int j = 0; j < 3; j++) {
                    if(i > 0 && (arr[i - 1] - 'a') == j) { //check pre
                        continue;
                    }
                    if(i + 1 < len && arr[i + 1] - 'a' == j) { //check next
                        continue;
                    }
                    arr[i] = (char)('a' + j); //modify
                    break;
                }
            }
        }
        //replace ?
        return String.valueOf(arr);
    }
}