/*invariant； parity奇偶性
The length of smaller half is kept to be n / 2 at all time and the length of the larger half is either n / 2 or n / 2 + 1 depend on n's parity.

This way we only need to peek the two heaps' top number to calculate median.*/

class _295_MedianFinder {
    //Time = O(logn) for(addNum()); O(1) for( findMedian())
    //Space = O(n) two heap together store all element in the stream
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;
    Boolean even = true;//

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());//maxHeap
        large = new PriorityQueue<>();
    }
    
    //进前是偶数，进来是奇数：因为此时large比small多一位1.small: k，k+ 1 => k+1; k+1 把small中最大的给large
    //2.large相反： 
    public void addNum(int num) {
        if(even) { //进来前是偶数，+1个是奇数
            small.offer(num);
            large.offer(small.poll()); 
        } else{
            large.offer(num);
            small.offer(large.poll());
        }
        even = !even;
    }
    //注意是peek,只find没拿出来
    public double findMedian() {
        double res = 0.0;
        if(even) {
            res = (double)(small.peek() + large.peek())/2;
        } else {
            res = large.peek();
        }
        return res;
    }
}