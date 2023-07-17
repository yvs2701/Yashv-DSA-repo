/* Leetcode 445: "Add two numbers II"
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example 1:
  Input: l1 = [7,2,4,3], l2 = [5,6,4]
  Output: [7,8,0,7]
Example 2:
  Input: l1 = [2,4,3], l2 = [5,6,4]
  Output: [8,0,7]
Example 3:
  Input: l1 = [0], l2 = [0]
  Output: [0]
Constraints:
- The number of nodes in each linked list is in the range [1, 100].
- 0 <= Node.val <= 9
- It is guaranteed that the list represents a number that does not have leading zeros.
*/

class AddTwoLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    static ListNode addTwoNumbers(AddTwoLists.ListNode l1, AddTwoLists.ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode sum = new ListNode(0), head = sum;
        int carry = 0;
        while (l1 != null && l2 != null) {
            sum.val += l1.val + l2.val;
            carry = sum.val > 9 ? 1 : 0;
            sum.val = sum.val > 9 ? sum.val - 10 : sum.val;
            sum.next = new ListNode(carry);

            l1 = l1.next;
            l2 = l2.next;
            sum = sum.next;
        }
        while (l1 != null) {
            sum.val += l1.val;
            carry = sum.val > 9 ? 1 : 0;
            sum.val = sum.val > 9 ? sum.val - 10 : sum.val;
            sum.next = new ListNode(carry);

            l1 = l1.next;
            sum = sum.next;
        }
        while (l2 != null) {
            sum.val += l2.val;
            carry = sum.val > 9 ? 1 : 0;
            sum.val = sum.val > 9 ? sum.val - 10 : sum.val;
            sum.next = new ListNode(carry);

            l2 = l2.next;
            sum = sum.next;
        }

        head = reverse(head);
        if (head.val == 0)
            return head.next;
        return head;
    }

    static ListNode reverse(ListNode curr) {
        if (curr == null)
            return null;
        ListNode prev = null, next;
        do {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        } while (curr != null);

        return prev;
    }
}
