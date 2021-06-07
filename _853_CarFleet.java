class _853_CarFleet {
    //算出多久可以到达target
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        double[][] posToTime = new double[len][2]; //record 0: distance from target, 1: time to arrive target
        
        for(int i = 0; i < len; i++) {
            double pos = (double)position[i];
            posToTime[i][0] = pos;
            posToTime[i][1] = (target - pos)/speed[i];
        }
        
        //sort by distance
        Arrays.sort(posToTime, (a,b) -> Double.compare(a[0], b[0]));
        
        int counter = 0;
        double slow = 0;
        
        //比较到达终点的时间，一样的counter不变，否则counter++， 因为一个car也算一个car fleet
        
        for(int i = len - 1; i >= 0; i--) {
            if(posToTime[i][1] > slow) {
                slow = posToTime[i][1];
                counter++;
            }
        }
        return counter;
    }
}