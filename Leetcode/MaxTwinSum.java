import java.util.Scanner;

/* Letcode 2130: "Maximum Twin Sum of a Linked List"
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Input: Length = 4, List = 5 4 2 1
Output: 6
Explanation:
- Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
- There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6.

Input: Length = 4, List = 4 2 2 3
Output: 7
Explanation:
- The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7. */

class MaxTwinSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // length of list
        
        ListNode head = new MaxTwinSum.ListNode(sc.nextInt());
        ListNode curr = head;
        
        for (int i = 1; i < n; ++i) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        sc.close();

        System.out.print(pairSum(head));
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static int pairSum(ListNode head) {
        int n = len(head); // find length of the list
        ListNode mid = getMiddle(head, n); // get the middle node
        ListNode end = reverse(mid); // reverse the right half of the list and return the head

        // The list we have now will always have the right half length >= left half
        // start checking head and end
        int max = 0; // Given: ListNode.val > 0
        while (head != null && end != null) {
            int sum = head.val + end.val;
            max = (sum > max) ? sum : max;
            head = head.next;
            end = end.next;
        }

        return max;
    }

    private static int len(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    private static ListNode getMiddle(ListNode head, int len) {
        int idx = len / 2;
        while (idx != 0) {
            head = head.next;
            --idx;
        }
        return head;
    }

    private static ListNode reverse(ListNode head) {
        if (head == null)
            return head;

        ListNode current = head, prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
