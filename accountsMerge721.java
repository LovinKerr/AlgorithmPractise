class accountsMerge721 {

//Solution graph dfs: build graph的时候merge and connect component

    //Time: O(nlogn)? sort, for loop里面每次O(n), sort nlogN, recursion O(1)对每个点
    //Space = O(n)
    //Draw an edge between two emails if they occur in the same account. The problem comes down to finding connected components of this graph.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //2 HashMap 
        //1.email and email's neighbors: undirect graph, add in both: connect account here by same email address
        HashMap<String, HashSet<String>> graph= new HashMap<>(); //email and it's neighbors
        //2.email to name
        HashMap<String, String> name = new HashMap<>(); //store email to name
        
        //build graph 错误写法
        for(List<String> account : accounts) {
            String username = account.get(0);
            // 只能从1开始走，list可能一共只有2个元素
            for(int i = 1; i < account.size(); i++) {
                String curr = account.get(i);
                String pre = account.get(i - 1);
 
                if(!graph.containsKey(curr)) {
                    graph.put(curr, new HashSet<>());
                }
                name.putIfAbsent(curr,username);
                
                if(i == 1) {
                    continue; //skip to next
                }
                graph.get(curr).add(pre);
                graph.get(pre).add(curr);
            }
        }
        //remeber check visited
        HashSet<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        
        //walk every email
        for(String e : name.keySet()) {
            if(visited.add(e)) { //if hashset could add e retuan true, means it's not visited before
                
                LinkedList<String> list = new LinkedList<>();
                helper(e, graph, visited, list);//dfs to record every connected email
                
                Collections.sort(list);//sort, then add name to first position
                list.addFirst(name.get(e)); 
                res.add(list);
            }
        }
        return res;
    }
        //这个dfs还需要想，top down
        //当前层： 拿到一个email,把她和她的neigbor加到list中
    private void helper(String e, HashMap<String, HashSet<String>> email,HashSet<String> visited, LinkedList<String> list){
        list.add(e);
        for(String next : email.get(e)) {
            if(visited.add(next)) {
                helper(next, email, visited, list);
            }
        }
    }
}

/*
class Solution {
//没画出正确的graph，undirect graph, node的关系
//想用两个hashtable表示， 走emailToLabel如果 set size > 1,则操作merge， 但好像无法merge
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //2 graph
        HashMap<String, HashSet<Integer>> emailToLabel = new HashMap<>(); //? counter to name
        HashMap<Integer, HashSet<String>> accountGraph = new HashMap<>();
        
        //build graph
        for(int j = 0; j < accounts.size(); j++) {
            String name = "";
            for(int i = 0; i < account.size(); i++) {
                if(i == 0) {
                    // name = account.get(0);
                    accountGraph.put(j, new HashSet<>());
                } else {
                    String email = account.get(i);
                    accountGraph.get(j).add(email);
                    if(emailGraph.containsKey(email)) {
                        emailGraph.get(email).add(j);
                    } else {
                        Hashset<Integer> temp = new HashSet<>();
                        temp.add(j);
                        emailToLabel.put(email, temp);
                    }
                }
            }
        }
        
        for(Map.Entry<)
    }
}*/