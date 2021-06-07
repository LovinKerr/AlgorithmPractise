class _1466_reorderRouteToMakeAllPathsLeadToZero {
    //time = O(n) 每个点都走过constant times; visit each node once.
    //Space = O(n) store n nodes in the adjacency list, with n - 1 edges in total.
    public int minReorder(int n, int[][] connections) {
        //convert to list
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for(int[] e : connections){
            int head = e[0];
            int tail = e[1];
            list.get(head).add(tail);
            list.get(tail).add(-head);
        }
        boolean[] visited = new boolean[n];
        
        return helper(list,visited,0);
        
    }
    //dfs to walk whole list and count pos in node, 注意visited
    private int helper(List<List<Integer>> list, boolean[] visited, int head){
        int change = 0;
        visited[head] = true;
        for(int tail : list.get(head)) {
            if(!visited[Math.abs(tail)]) { //如果没有走过当前点
                change += helper(list,visited,Math.abs(tail)) + (tail > 0 ? 1 : 0);
            }
        }
        return change;
    }
}