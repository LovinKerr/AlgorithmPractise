class _offer09_twoStackImplementQueue {
    //用两个stack使queue实现加在最后，删在前面
    // stack_in只负责进入
    // stack_out只负责取出
    // 只有stack_out为空时才把stack_in的所有元素倾倒进stack_out中，这样顺序就不会乱了
//Time = O(n) max n when stackOut is empty and stackIn is full
// 对于插入和删除操作，时间复杂度均为 O(1)O(1)。插入不多说，对于删除操作，虽然看起来是 O(n)O(n) 的时间复杂度，但是仔细考虑下每个元素只会「至多被插入和弹出 stack2 一次」，因此均摊下来每个元素被删除的时间复杂度仍为 O(1)。

//Space = O(n) max stack size N, we have 2 of them
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public CQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }
    
    public void appendTail(int value) {
        stackIn.push(value);
    }
    
    public int deleteHead() {
        if(!stackOut.isEmpty()) {
            return stackOut.pop();
        } else if(!stackIn.isEmpty()) {
            while(!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
            return stackOut.pop();
        }
        return -1;
    }
}
// https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/