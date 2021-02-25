class HitCounter362 {
//Follow Up: What if the number of hits per second could be very large? Does your design scale?

/*Scalable Solution: 
    2个array保存1.timestamp 2.hit count
    存到timestamp里的数对300取余得idx， 比较array1.中值的大小，不一样就说明在5min外，update
    
    time=O(1) , scan array 300 times, constant times
    space= O(1) size of two 300 size array
*/
    int[] stampsArr;
    int[] numOfHit;

    public HitCounter() {
        stampsArr = new int[300];
        numOfHit = new int[300];
    }
    
    public void hit(int timestamp) {
        int idx = timestamp % 300; 
        if(stampsArr[idx] != timestamp) {
            //out of 5 min
            numOfHit[idx] = 1;
            stampsArr[idx] = timestamp;
        } else {
            numOfHit[idx]++;
        }
    }
    
    public int getHits(int timestamp) {
        int res = 0;
        //找出所有在5分中内的位置，然后把hits中对应位置的点击数都加起来
        for(int i = 0; i < stampsArr.length; i++) {
            if(timestamp - stampsArr[i] < 300) {
                res += numOfHit[i];
            }
        }
        return res;
    }


    //granularity 间隔尺寸，粒度； monotonically 单调地
/*Queue Solution
    time = O(1) for hit and getHits method
    space = O(n) the size of queue
    */
    Queue<Integer> queue;
    public HitCounter() {
        queue = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(timestamp - queue.peek() >= 300) { //poll out all not valid timestamp
            queue.poll();
        }
        return queue.size();
    }
}
/*  scalable design问法

Ans: if huge amount of hits happened at the same timestamp, this solution will takes too much memory since each element in queue is a single hit.
    If everyone is calling hit without getHits you will eventually fill up the JVM

    Since this is a design question, we need to ask interviewer how this class is going to be used?
    A working code is not the answer to this question, but how you adjust your program to meet different use cases.

    Consider: There are 1000 frequent hit() followed by 1 getHits(). 
    If we only do removal in getHits() function, it will be very time consuming. For me, 
    I prefer to do removal in both hit() and getHits(), so that the program avoids system lag in this case.
    This is important when you design a time-critical system.

    One follow up is that, if the duration is much longer than 300 seconds, 
    say 3 days instead, does our method handle space growth efficiently? Is sub-linear growth possible?

    Like OP said above, it is open-ended and depends on each use case.
    */