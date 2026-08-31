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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prev = -1;
        int min = Integer.MAX_VALUE;
        int max = -1;
        int index = 1;

        ListNode a = head;
        ListNode b = head.next;

        while(b != null && b.next != null) {
            ListNode c = b.next;

            if((b.val > a.val && b.val > c.val) || (b.val < a.val && b.val < c.val)) {
                if(first == -1) {
                    first = index;
                }
                else {
                    min = Math.min(min, index - prev);
                    max = Math.max(max, index - first);
                }
                prev = index;
            }
            a = b;
            b = c;
            index++;
        }
        if(first == -1 || prev == first) {
            return new int[]{-1,-1};
        }
        return new int[]{min, max};
    }
}