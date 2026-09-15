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

        if(head == null) {
            return new int[] {-1,-1};
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int i = 1;
        List<Integer> criticalPoints = new ArrayList<>();

        while(curr != null && curr.next != null) {
            // Compare for Local Maxima
            if(curr.val > prev.val && curr.val > curr.next.val) {
                criticalPoints.add(i);
            }
            
            // Compare for Local Minima
            if(curr.val < prev.val && curr.val < curr.next.val) {
                criticalPoints.add(i);
            }

            curr = curr.next;
            prev = prev.next;
            i++;
        }

        if(criticalPoints.size() < 2) {
            return new int[] {-1, -1};
        }
        // Criticall Points are ready
        int minDist = Integer.MAX_VALUE;
        for(i = 1; i < criticalPoints.size(); i++) {
            minDist = Math.min(minDist, criticalPoints.get(i) - criticalPoints.get(i-1));
        }

        int maxDist = criticalPoints.get(criticalPoints.size() - 1) - criticalPoints.get(0);

        return new int[] {minDist, maxDist};
    }
}