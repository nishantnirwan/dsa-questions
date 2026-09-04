/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null) {
            fast = fast.next;
            if(fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            if(slow == fast) {
            return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Boolean> visited = new HashMap<>();

        ListNode curr = head;

        while(curr != null) {
            if(visited.containsKey(curr)) {
                return curr;
            }
            visited.put(curr, true);
            curr = curr.next;
        }
        return null;
    }
}