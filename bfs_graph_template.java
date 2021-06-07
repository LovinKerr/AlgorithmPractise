// "static void main" must be defined in a public class.
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        Main test = new Main();
        HashMap<Integer, List<Integer>> map1 = new HashMap<>();
        map1.put(1,new ArrayList<>(Arrays.asList(2,3,4,5)));
        map1.put(2,new ArrayList<>(Arrays.asList(3,5,8)));
        map1.put(7,new ArrayList<>(Arrays.asList(9,10)));
        //checked map1 is working
        for(Map.Entry<Integer,List<Integer>> entry : map1.entrySet()) {
            int key = entry.getKey();
            List<Integer> values = entry.getValue();
            System.out.println("map" + key);
            for(int i: values) {
                System.out.println("values: " + i);
            }
        }
        test.bfs(map1);
    }
    
    //visited?
    public void bfs(HashMap<Integer,List<Integer>> map) {
        // HashMap<Integer,Integer> dist = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();
        List<Integer> nodeHead = new ArrayList<>();
        
        for(Map.Entry<Integer,List<Integer>> entry : map.entrySet()) {
            nodeHead.add(entry.getKey());
        }
        
        for(int i : nodeHead){
            queue.offer(i);
            visited.add(i);
        }
        // dist.put(head,0);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            // if(!visited.contains(curr)){
            //     visited.add(curr);
            // }
            System.out.println("Curr node " + curr);
            if(map.containsKey(curr)) {
               List<Integer> list = map.get(curr); // map的key 只有三个
                if(list != null) {
                    // int nextDist = dist.get(curr) + 1;
                for(int i = 0; i < list.size(); i++) {
                    int next = list.get(i);
                    if(!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                        // dist.put(next, nextDist);
                    }
                } 
                }
            }
        }
    }
}