// "static void main" must be defined in a public class.

import java.util.*;

public class DFS_Template {
    public static void main(String[] args) {
        DFS_Template test = new DFS_Template();
        HashMap<Character, List<Character>> map1 = new HashMap<>();
        map1.put('a',new ArrayList<>(Arrays.asList('b','c','d','e')));
        map1.put('b',new ArrayList<>(Arrays.asList('c','e','f')));
        map1.put('g',new ArrayList<>(Arrays.asList('h','i')));
        //checked map1 is working
        HashSet<Character> visited = new HashSet<>();
        for(Map.Entry<Character,List<Character>> entry : map1.entrySet()) {
            Character key = entry.getKey();
            List<Character> values = entry.getValue();
            // for(Character i: values) {
            //     System.out.println("values: " + i);
            // }
            test.dfs(map1, visited, key);
        }
    }
    
    private void dfs(Map<Character, List<Character>> map,HashSet<Character> visited, Character curr) {
        if(curr == null || visited.contains(curr)) {
            return;
        }
        System.out.println("curr node is " + curr);
        visited.add(curr);
        // ?? map
        if(map.containsKey(curr)) {
            List<Character> list = map.get(curr);
            for(Character i : list) {
                dfs(map,visited, i);
            }
        }
    }
}