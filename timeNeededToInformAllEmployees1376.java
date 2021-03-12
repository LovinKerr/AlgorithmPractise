class timeNeededToInformAllEmployees1376 {

/* 不能用bfs分层走，这层最大的相当于下两层， 错误思路写法
    test case: 
    11
    4
    [5,9,6,10,-1,8,9,1,9,3,4]
    [0,213,0,253,686,170,975,0,261,309,337]
    */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
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