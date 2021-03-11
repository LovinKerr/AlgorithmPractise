public class ConnectingGraph { 

    private int[] father = null;

    private int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }

    public ConnectingGraph(int n) {
        // initialize your data structure here.
        father = new int[n + 1];
        for (int i = 1; i <= n; ++i)
            father[i] = i;
    }

    public void connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        //如果a,b不是同一个爸爸，那就把b的爸爸给到a做爸爸？ 为什么不是相反
        if (root_a != root_b)
            father[root_a] = root_b;
    }
        
    public boolean  query(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        return root_a == root_b;
    }
}