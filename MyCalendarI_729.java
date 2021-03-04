class MyCalendarI_729 {
    //Time = O(NlogN) : n is # events booked, search :O(logN); insert(book method) :O(1)
    //Space = O(N) size of cal
    TreeMap<Integer, Integer> cal;

    public MyCalendar() {
        cal = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        //line 10 不能定义data type为int,(== null not valid)
        Integer prev = cal.floorKey(start); //比start小中最大的数
        Integer next = cal.ceilingKey(start);
        if((prev == null || cal.get(prev) <= start) && (next == null || next >= end)) {
            cal.put(start, end);
            return true;
        } 
        return false;
    }
}