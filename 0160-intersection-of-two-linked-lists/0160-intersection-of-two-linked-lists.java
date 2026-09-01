/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while(a != null && b != null) {
            a = a.next;
            b = b.next;
        }
        if(a == null) {
            // Either Length of B > or = to Length of A
            int bExtraLen = 0;
            while(b != null) {
                bExtraLen++;
                b = b.next;
            }
            while(bExtraLen-- > 0) {
                headB = headB.next;
            }
        }
        else {
            // Either Length of A > or = to Length of B
            int aExtraLen = 0;
            while(a != null) {
                aExtraLen++;
                a = a.next;
            }
            while(aExtraLen-- > 0) {
                headA = headA.next;
            }
        }
        // Now the heads are on that position where both Lists can be compared easily as of same length
        while(headA != null && headB != null) {
            if(headA == headB) {
                return headA;
            }
            else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }
}