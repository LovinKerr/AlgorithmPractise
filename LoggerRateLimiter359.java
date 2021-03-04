class LoggerRateLimiter359 {
    //HashMap加进去就不能拿出来
//Queue+set
    private class Log{
        int timestamp;
        String msg；
        public Log() {
            timestamp = new int();
            msg = new String();
        }
    }
    public


//Hashmap solution
    //Time = O(1)
    //Space = O(n) The disadvantage to this solution is that the memory usage never stops growing.
    HashMap<String,Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(map.containsKey(message)) {
            //10s外重新print
            if(timestamp - map.get(message) >= 10) {
                map.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        } 
        map.put(message, timestamp);
        return true;
    }
}