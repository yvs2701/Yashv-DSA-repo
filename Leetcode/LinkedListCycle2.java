import java.util.Arrays;

/**
 * <h3><a href="https://leetcode.com/problems/linked-list-cycle-ii/">
 * LeetCode 142: Linked List Cycle II
 * </a></h3>
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
 * connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: head = [3,2,0,-4], pos = 1 <br>
 * Output: tail connects to node index 1 <br>
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * </p>
 * <p>
 * Example 2: <br>
 * Input: head = [1,2], pos = 0 <br>
 * Output: tail connects to node index 0 <br>
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * </p>
 * <p>
 * Example 3: <br>
 * Input: head = [1], pos = -1 <br>
 * Output: no cycle <br>
 * Explanation: There is no cycle in the linked list.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= number of nodes &lt;= 10^4</li>
 * <li>-10^5 &lt;= Node.val &lt;= 10^5</li>
 * <li>pos is -1 or a valid index in the linked-list.</li>
 * </ul>
 * </p>
 */
public class LinkedListCycle2 {
    /**
     * Assume a Linked List, and two pointers iterating the nodes in it.
     * A pointer covers two nodes at once (fast pointer), and the other covers each node one by one (slow pointer).
     * If the List had a cycle. Assume the pointer meet somewhere in the cycle.
     * Let x1 = distance from the head node to the node where cycle starts.
     * Let x2 = distance from the start of the cycle to the place where both the pointers meet.
     * Let x3 = distance from the meeting of pointers to the start of the cycle again.
     * Since fast pointer moves twice the distance than slow pointer we can represent this equation as:
     * 2 * slow_distance = fast_distance
     * 2 * (x1 + x2) = (x1 + x2 + x3 + x2)
     * 2 * x1 + 2 * x2 = x1 + x3 + 2 * x2
     * 2 * x1 = x1 + x3
     * x1 = x3
     * Which means distance from the head to cycle start
     * and meeting point to cycle start is the same
     * We just create a third pointer, and move it just like slow pointer (one step at a time).
     * The place where both meet is the place where the cycle started!
     */
    static ListNode detectCycle(ListNode head) {
        if (head == null) // no cycle found
            return null;
        ListNode fast = head, slow1 = head;
        do {
            slow1 = slow1.next;
            fast = fast.next;
            if (fast == null) { // no cycle found
                return null;
            }
            fast = fast.next;
        } while (fast != null && fast != slow1);

        if (fast == null) { // no cycle found
            return null;
        }

        // cycle found
        ListNode slow2 = head;
        while (slow1 != slow2) {
            slow1 = slow1.next;
            slow2 = slow2.next;
        }

        return slow1;
    }

    public static void main(String[] args) {
        int[][][] testcases = {
                {{3, 2, 0, -4}, {1}},
                {{1, 2}, {0}},
                {{1}, {-1}},
                {{}, {-1}}
        };

        for (int[][] testcase : testcases) {
            ListNode head = createListWithCycle(testcase[0], testcase[1][0]);
            System.out.println("Input: " + Arrays.toString(testcase[0]));
            System.out.println("Cycle starts at: " + testcase[1][0]);
            System.out.println("Output: " + detectCycle(head));
        }
    }

    /**
     * Creates a list with or without a cycle in it.
     *
     * @param list        array containing linked list (left to right)
     * @param cycle_index index of the node where cycle starts (-1 for no cycle)
     * @return head of the linked list
     */
    private static ListNode createListWithCycle(int[] list, int cycle_index) {
        if (list.length == 0) {
            return null;
        }
        ListNode head = new ListNode(list[0]);

        // create list
        ListNode tail = head;
        for (int i = 1; i < list.length; i++) {
            tail.next = new ListNode(list[i]);
            tail = tail.next;
        }

        // insert cycle
        if (cycle_index == -1) {
            return head;
        }

        ListNode cycle = head;
        while (cycle_index-- > 0) {
            cycle = cycle.next;
        }
        tail.next = cycle;

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + (next != null ? "..." : "null") +
                    '}';
        }
    }
}
