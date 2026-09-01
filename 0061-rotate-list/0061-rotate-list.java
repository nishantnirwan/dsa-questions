/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }
        int length = 1;
        ListNode temp = head;
        while(temp.next != null) {
            length++;
            temp = temp.next;
        }
        // Make it circular
        temp.next = head;

        // Update k
        k = k % length;

        // Link Break and Set forward variable
        temp = head;
        for(int i = 1; i <= length - k - 1; i++) {
            temp = temp.next;
        }
        ListNode forward = temp.next;

        // Link Break
        temp.next = null;
        return forward;
    }
}