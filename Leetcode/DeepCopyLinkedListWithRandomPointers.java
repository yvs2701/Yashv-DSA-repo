/**
 * <h3>Leetcode 138: Copy List With Random Pointer</h3>
 * <p>
 * Linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 * </p>
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list
 * such that the pointers in the original list and copied list represent the same list state. None
 * of the pointers in the new list should point to nodes in the original list.
 * </p>
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <br>
 * Return the head of the copied linked list. <br>
 * The linked list is represented in the input/output as a list of n nodes. Each node is
 * represented as a pair of [val, random_index] where:
 * <ul>
 * <li> val: an integer representing Node.val </li>
 * <li> random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node. </li>
 * </ul>
 * Your code will only be given the head of the original linked list. <br>
 * <b> YASH'S NOTE - DO NOT MODIFY THE ORIGINAL LIST OR THE SOLUTION WILL BE REJECTED !!! </b>
 * </p>
 * <p> Example:
 * <br>
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]] <br>
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]] <br>
 * Input: head = [[1,1],[2,1]] <br>
 * Output: [[1,1],[2,1]] <br>
 * Input: head = [[3,null],[3,0],[3,null]] <br>
 * Output: [[3,null],[3,0],[3,null]] <br>
 * </p>
 */
public class DeepCopyLinkedListWithRandomPointers {

    /**
     * Solution: <br>
     * Create the new nodes between the nodes of the old list <br>
     * If the old list (with next pointers) was: <br>
     * A -> B -> C -> D <br>
     * then create A -> A' -> B -> B' -> C -> C' -> D -> D' <br>
     * Finally assign the random pointers for the new nodes by getting the random pointer of the
     * old node and seeing the next pointer of it to get the newly created node.
     */
    public static Node deepCopyList(Node head) {
        if (head == null)
            return null;
        Node oldItr = head;

        // copy the nodes in order (using the `next` property)
        while (oldItr != null) {
            Node next = oldItr.next, copy = copyNode(oldItr);
            oldItr.next = copy;
            copy.next = next;
            oldItr = next;
        }

        // copy the `random` pointers
        oldItr = head;
        while (oldItr != null) {
            if (oldItr.random != null) {
                oldItr.next.random = oldItr.random.next;
            }
            oldItr = oldItr.next.next;
        }

        // All pointers have been set. Separate the lists.
        oldItr = head;
        Node copyHead = head.next;

        while (oldItr != null) {
            Node copy = oldItr.next;
            Node next = copy.next;
            oldItr.next = next;
            copy.next = (next != null) ? next.next : null;
            oldItr = next;
        }

        return copyHead;
    }

    /**
     * Creates a copy of the `Node` by copying the `val` property of the object.
     */
    private static Node copyNode(Node node) {
        if (node == null)
            return null;
        return new Node(node.val, node.idx);
    }

    /* END OF THE SOLUTION. BOILERPLATE CODE STARTS FOR TEST CASE SETUP */
    public static void main(String[] args) {
        Node[] list = {
                new Node(7, 0),
                new Node(13, 1),
                new Node(11, 2),
                new Node(10, 3),
                new Node(1, 4)
        };

        // set next pointers
        for (int i = 0; i < list.length - 1; i++) {
            list[i].next = list[i + 1];
        }
        list[list.length - 1].next = null;

        // set `random` pointers
        list[0].random = null;       // 7 -> null
        list[1].random = list[0];    // 13 -> 7 (index 0)
        list[2].random = list[4];    // 11 -> 1 (index 4)
        list[3].random = list[2];    // 10 -> 11 (index 2)
        list[4].random = list[0];    // 1  -> 7 (index 0)

        Node result = deepCopyList(list[0]); // call the solution class

        // print original and copied list returned by the solution
        System.out.print("Original List: ");
        printList(list[0]);
        System.out.println();
        System.out.print("Copied List: ");
        printList(result);
        System.out.println();
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head + " ");
            head = head.next;
        }
    }

    /**
     * A list node object with idx property.
     */
    public static class Node {
        int val;
        Node next;
        Node random;
        private final int idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "[" + val + "," + (random != null ? String.valueOf(random.idx) : "null") + "]";
        }
    }
}
