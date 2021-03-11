class maxPointsYouCanObtainFromCards1423 {

//SlidingWindow Solution
    //Time = O(n)
    //space = O(1)
    public int maxScore(int[] cardPoints, int k) {
        //sliding window
        int len = cardPoints.length;
        int size = len - k;
        //keep a smallest window
        
        //base case
        int sum = 0;
        for(int i = 0; i < size; i++) {
            sum+= cardPoints[i];
        }
        
        int minSum = sum;
        int total = sum;
        for(int currIdx = size; currIdx < len; currIdx++) {
            int preIndex = currIdx - size;
            total += cardPoints[currIdx];
            sum += (cardPoints[currIdx] - cardPoints[preIndex]);
            minSum = sum < minSum ? sum : minSum;
        }
        
        return total - minSum;
    }
}