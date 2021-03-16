class timeNeededToInformAllEmployees1376 {

//dfs
    //time = O(n) 每个点操作O(1), 每个点都走到
    //Space= O(n) map size
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        //manager[headId] = -1
        //manager[headId] = -1
        //index in manager: 员工id, manager[i] : i的领导id
        //index in inforTime: 员工id, inforTime[i] : i给自己的下属传达时间
        //dfs记录每条root to leaf长度，找出最长的
        HashMap<Integer, List<Integer>> map = new HashMap<>();         //scan manager, manager -> direct记录

        for(int workerId = 0; workerId < manager.length; workerId++) {
            int managerId = manager[workerId];
            if(!map.containsKey(managerId)) {
                map.put(managerId, new ArrayList<>());
            }
            map.get(managerId).add(workerId);
        }
        return dfs(map, informTime, headID);
    }
    
    private int dfs(HashMap<Integer, List<Integer>> map, int[] informTime, int currId){
        int max = 0;
        if(!map.containsKey(currId)) {
            return max;
        }
        
        List<Integer> list = map.get(currId);
        for(int i = 0; i < list.size(); i++) {
            //比较当前和以前的点哪个大
            max = Math.max(max, dfs(map, informTime, list.get(i)));
        }
        return max + informTime[currId]; //加上当前自己传达时间
    }
//bfs
    


/* 错误思路写法:
    不能用bfs分层走，这层最大的相当于下两层， 
    test case: 
    11
    4
    [5,9,6,10,-1,8,9,1,9,3,4]
    [0,213,0,253,686,170,975,0,261,309,337]
    */
    public int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
        //manager[headId] = -1
        //index in manager: 员工id, manager[i] : i的领导id
        //index in inforTime: 员工id, inforTime[i] : i给自己的下属传达时间
        //tree, bfs, 记录每层最长时间
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //scan manager, manager -> direct记录
        for(int workerId = 0; workerId < manager.length; workerId++) {
            int managerId = manager[workerId];
            if(!map.containsKey(managerId)) {
                map.put(managerId, new ArrayList<>());
            }
            map.get(managerId).add(workerId);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(headID);
        
        int res = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int maxTime = 0;
            while(size != 0) {
                //当前node操作
                int curr = queue.poll();
                size--;
                int time = informTime[curr];
                maxTime = time > maxTime ? time : maxTime;
                //把当前node's neighbor加到queue
                if(map.containsKey(curr)) {
                    for(int nextId : map.get(curr)){
                        queue.offer(nextId);
                    }
                }
            }
            res += maxTime;
        }
        return res;
    }
}