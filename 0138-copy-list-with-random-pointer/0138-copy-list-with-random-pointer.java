/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        
        // 1 - Add Clone Nodes
        Node temp = head;
        while(temp != null) {
            Node cloneNode = new Node(temp.val);
            cloneNode.next = temp.next;
            temp.next = cloneNode;
            temp = cloneNode.next;
        }

        // 2 - Copy Random Pointers
        temp = head;
        while(temp != null) {
            Node oldNode = temp;
            Node newNode = temp.next;

            if(oldNode.random != null) {
                // Observation
                newNode.random = oldNode.random.next;
            }
            // Move temp
            temp = newNode.next;
        }

        // 3 - Detach Both List
        temp = head;
        Node ansListHead = head.next;

        while(temp != null) {
            Node oldNode = temp;
            Node cloneNode = temp.next;

            oldNode.next = cloneNode.next;
            if(cloneNode.next != null) {
                cloneNode.next = cloneNode.next.next;
            }

            // Move temp
            temp = temp.next;
        }

        return ansListHead;
    }
}