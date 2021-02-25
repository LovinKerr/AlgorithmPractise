class SnapshotArray1146 {

//List of Array Solution:
    int maxIndex = 0; //用来做啥
    int[] values; 
    List<int[]> snaps; //each time snap
    
    public SnapshotArray(int length) {
        values = new int[length];
        snaps = new ArrayList<>();
    }
    
    public void set(int index, int val) {
        if(index > maxIndex) {
            maxIndex = index;
        }
        values[index] = val; //改变value
    }
    
    public int snap() {
        int[] arr = new int[maxIndex + 1]; //干什么用
        for(int i = 0; i <= maxIndex; i++) { //记录改变的值？
            arr[i] = values[i];
        }
        snaps.add(arr);
        return snaps.size() - 1;
    }
    
    public int get(int index, int snap_id) {
        int[] arr = snaps.get(snap_id);
        if(index >= arr.length) return 0;
        return arr[index];
    }

//TreeMap Solution: ? where use binary search:https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
    //这里不用HashMap 而用treemap原因
    TreeMap<Integer,Integer>[] array;
    int snapId = 0;

    public SnapshotArray(int length) {
        //new这个array
        array = new TreeMap[length];
        for(int i = 0; i < length; i++) {
            array[i] = new TreeMap<Integer,Integer>();
            array[i].put(0,0); //if not change, then 0, avoid null pointer exception NPE
        }
    }
    
    public void set(int index, int val) {
        array[index].put(snapId, val);
    }
    
    public int snap() {
        snapId++;
        return snapId - 1;
    }
    
    public int get(int index, int snap_id) {
        //syntax:
        return array[index].floorEntry(snap_id).getValue();
    }

//HashMap Solution: Memory Limit Exceeded

    //snap_id is ?
    //Hashmap: snap_id : array?
    //array copy: https://www.geeksforgeeks.org/array-copy-in-java/
    int[] array;
    int counter = 0;
    HashMap<Integer, int[]> idToArray = new HashMap<>();

    public SnapshotArray(int length) {
        array = new int[length];
    }
    //set val to array's mapped index
    public void set(int index, int val) {
        array[index] = val;
    } 
    
    //return snap_id: the total number of times we called snap() minus 1.
    public int snap() {
        counter++;
        int[] temp = array.clone();
        idToArray.put(counter - 1, temp);
        return counter - 1;
    }
    
    //at the snap_id time we took the snapshot, get the value by index
    public int get(int index, int snap_id) {
        int[] snapArray = idToArray.get(snap_id);
        return snapArray[index];
    }
}