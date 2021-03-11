class mergeInteval56 {
    public int[][] merge(int[][] intervals) {
        //sort intervals to get the in order which could track next
        ArrayList<int[]> res = new ArrayList<>();
        if(intervals == null || intervals.length == 0) {
            return intervals;
        }
        
        Arrays.sort(intervals, (int[] a, int[] b) ->a[0] - b[0]);
        
        //res中最后一个和后面的比较，有overlap merge， res中存的都没有overlap
        int[] lastArray = new int[2];
        lastArray = intervals[0];
        res.add(lastArray);
        
        for(int i = 1; i < intervals.length; i++) {
            lastArray = res.get(res.size() - 1);
            
            if(lastArray[1] >= intervals[i][0]) {
                //overlap, so merge
                lastArray[1] = Math.max(intervals[i][1], lastArray[1]);
            } else {
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
//Time = O(nLogn)
//Space = O(n)  array