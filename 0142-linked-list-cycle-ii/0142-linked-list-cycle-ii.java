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