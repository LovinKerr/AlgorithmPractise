class _155_MinStack {
    //Time = O(1)
    //Space = O(n)
    Stack<Integer> stack;
    Stack<Integer> sorted;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        sorted = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        //为什么这里要是大于等于
        if(sorted.empty() || sorted.peek() >= x) {
            sorted.push(x);
        }
    }
    
    public void pop() {
        int temp = 0;
        if(!stack.isEmpty()){
            temp = stack.pop();
        }
        
        if(!sorted.empty() && sorted.peek() == temp) {
            sorted.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return sorted.peek();
    }
}