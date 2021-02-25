class MeetingRooms2_253 {
    public int minMeetingRooms1(int[][] intervals) {

//Solution Heap: sort处理start，heap处理end
        //Time = O(nLogN) sort + scan interval once
        //Space = max O(n): max size of heap
        //start,end都要处理
        //画图，理解最后pq里留下的是每个房间最后的结束时间，如果2个时间段可以用同一房间，则结束时间被poll出来更新
        
        //test1:[[4,9],[4,17],[9,10]]
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0])); 
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a-b);
        pq.offer(intervals[0][1]);
        
        for(int i = 1; i < intervals.length; i++) {
            //compare end
            if(intervals[i][0] >= pq.peek()){//最小的end; 后一个start和前面offer进去最小的end做比较
                pq.poll(); //拿出来，和现在同一个room
            } 
            pq.offer(intervals[i][1]);
        }
        return pq.size(); 
    }

//Solution sort: 2d array convert to 2 array
    public int minMeetingRooms2(int[][] intervals) {

        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);
                
        //start,end 关系,和heap思路类似
        int rooms = 0;
        int indOfEnd = 0;
        for(int i = 0; i < n; i++) {
            if(start[i] >= end[indOfEnd]) {
                //merge available
                indOfEnd++;
            } else {
                rooms++;
            }
        }
        return rooms;
    }
}