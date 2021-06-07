public class BuildMap {
    Map<Integer,Integer> map;
    Set<Integer> set; //store all key which has not been set all
    int all = 0;
    public static void main(String[] args) {
        BuildMap test1 = new BuildMap();
        test1.set(1,78);
        test1.set(2,79);
        test1.set(3,80);
        System.out.println(test1.get(3));
        test1.setAll(99);
        test1.set(4,81);
        System.out.println(test1.get(3));
        System.out.println(test1.get(2));
        System.out.println(test1.get(4));
    }
    
    public BuildMap(){
        map = new HashMap<>();
        set = new HashSet<>();
    }
    
    public void set(int a, int b) {
        map.put(a,b);
        set.add(a);
    }
    
    public int get(int key){
        int res = 0;
        if(set.contains(key)) {
            res = map.get(key);
        } else {
            res = all;
        }
        return res;
    }
    
    public void setAll(int input) {
        if(set.size() > 0) {
            set.clear();
        }
        all = input;
    }
}