class _658_FindKClosestElement {
    //整理笔记
    //Binary Search O(log(n-k)), find left
    //Space = O(1)
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int len = arr.length;
        int left = 0;
        int right = len - k;
        while(left < right) {
            int mid = left + (right - left) /2;
            if(x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for(int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    //Time = O(n)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;
        while(right - left >= k) {
            if(Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;
            } else {
                right--;
            }
        }
        
        for(int i = left; i <= right; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}