class _75_SortColors {
    //folow up: solve without sort
    
    //3个pointer，一个代表从0开始的0， 一个代表从尾到前的2， 一个代表当前
    //Time= O（n)
    //Space = O(1)
    public void sortColors(int[] nums) {
        if(nums.length == 1) {
            return;
        }
        int left = 0; 
        int right = nums.length - 1;
        int i = 0;
        while(i <= right) { //需要写= 比如里面没有2，只有0，1的情况
            int curr = nums[i];
            //每次操作确保i前面的数字已经sort好不变
            if(curr == 2) {
                
                swap(nums, i, right--);
                //注意这里i不能往前走，还没有判断swap之后的在i这里的值
            } else if(curr == 0) {
                swap(nums, i++, left++);

            } else {
                i++;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
     // 另一个解： dual-pivot partitioning sub-routine of quick sort algorithm

    //题意：sort一个只包含0，1，2的数组
// 错误写法，想法和上面solution 2类
    public void sortColors(int[] nums) {
    //swap? 2 放最后面，0 放最前面
    for(int i = 0; i < nums.length; i++) {
        int curr = nums[i];
        if(curr == 2) {
            nums = swapLast(nums, i);
        }
    }
}
    private int[] swapLast(int[] nums, int j) {
        int len = nums.length;
        int i = len - 1;
        while(nums[i] == 2) {
            i--;
        }
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
        return nums;
    }
    private int[] swapFirst(int[] nums, int j){
        int i = 0;
        while(nums[i] == 0) {
            i++;
        }
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
        return nums;
    }

//Soluiton 1.arrays.sort(O(nlogN))
    //Space O(1)
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}