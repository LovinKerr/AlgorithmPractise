class _445_AddTwoNumbersII {
    //2个linkedlist相加，从linkedlist尾部开始走
    //1.reverse list
    //2.加起来，再reverse list
    
    //Time = O(n + m), reverse 3遍list
    //Space = O(1)
    
    //?是否可以强化 => O(Math.max(m,n) + 1)
    //Space = O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        reverseList(l1);
        reverseList(l2);
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        ListNode total = null;
        int carry = 0;
        while(pointer1 != null && pointer2 != null) {
            int sum = pointer1.val + pointer2.val + carry;
            carry = sum / 10;
            int value = sum % 10;
            total = new ListNode(value);
            
            total = total.next;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        
        while(pointer1 != null) {
            int sum = pointer1.val + carry;
            carry = sum / 10;
            int value = sum % 10;
            total = new ListNode(value);
            total = total.next;
            pointer1 = pointer1.next;
        }
        while(pointer2 != null) {
            int sum = pointer2.val + carry;
            carry = sum / 10;
            int value = sum % 10;
            total = new ListNode(value);
            total = total.next;
            pointer2 = pointer2.next;
        }
        return reverseList(total);
    }
    // private ListNode reverse(ListNode a) {
    //     if(a == null || a.next == null){
    //         return a;
    //     }
    //     ListNode curr = a;
    //     ListNode prev = null;
    //     ListNode next = a.next;
    //     while(curr != null) {
    //         curr.next = prev;
    //         prev = curr;
    //         curr = next;
    //         if(curr != null) {
    //             next = curr.next;
    //         }
    //     }
    //     return prev;
    // }
    private ListNode reverseList(ListNode head) {
        if(head == null) {
            return null;
        }
        
        ListNode curr = head;
        ListNode prev = null; //dummy
        ListNode next = head.next;
        
        while(curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if(curr != null) {
               next = curr.next;
            }

        }
        return prev;
    }
}