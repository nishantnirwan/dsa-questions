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
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode forward = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forward;
        }

        return prev;
    }

    public ListNode doubleIt(ListNode head) {
        head = reverse(head);

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode temp = head;
        int carry = 0;

        while(temp != null) {
            int sum = temp.val * 2 + carry;
            int digit = sum % 10;
            curr.next = new ListNode(digit);
            curr = curr.next;

            carry = sum / 10;
            temp = temp.next;
        }

        if(temp == null && carry != 0) {
            curr.next = new ListNode(carry);
        }

        dummy = dummy.next;
        head = reverse(dummy);
        return head;
    }
}