class _228_SummaryRanges {
//Solution 1.
    //Time = O(n)
    //Space = O(1)
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len == 0) {
            return res;
        }
        // if(len == 1){
        //     res.add(nums[0] + "");
        //     return res;
        // }
        
        for(int i = 0; i < len; i++) {
            int curr = nums[i];
            while(i + 1 < len && (nums[i+1] - nums[i]) == 1) { //i+1 valid，则把i走到i+1; 注意这里nums[i]会变动，不能用curr代替
                i++;
            }
            if(curr != nums[i]) {
                res.add(curr + "->" + nums[i]);
            } else {
                res.add(curr + "");
            }
        }
        return res;
    }

//错误各种漏考虑corner case
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        
        //用pre 不如用 next, 不然最后一个会漏
        
        String temp = "";
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            int next = nums[i+1];
            
            if(temp == "") {
                temp = String.valueOf(curr);
                if(curr + 1 != next) {
                    res.add(new String(temp));
                    temp = "";
                }
            } else {
                if(curr + 1 != next) {
                    temp = temp + "->" + String.valueOf(curr);
                    res.add(new String(temp));
                    temp = "";
                }
            }
        }
        //永远都走不了最后一个数
        if(temp != "") {
            res.add(temp);
        }
        return res;
    }
}