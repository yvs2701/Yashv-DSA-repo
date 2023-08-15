/* Leetcode 86: "Partition List"
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.
Example 1:
  1 -> 4 -> 3 -> 2 -> 5 -> 2
  Input: head = [1,4,3,2,5,2], x = 3
  Output: [1,2,2,4,3,5]
  1 -> 2 -> 2 -> 4 -> 3 -> 5
Example 2:
  2 -> 1
  Input: head = [2,1], x = 2
  Output: [1,2]
  1 -> 2
*/

import java.util.Scanner;

class PartitionList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PartitionList obj = new PartitionList();
        int n = sc.nextInt();

        // generate a linked list of random numbers ranging [0, n)
        ListNode head = obj.new ListNode((int) (Math.random() * n));
        ListNode curr = head;
        for (int i = 1; i < n; i++) {
            curr.next = obj.new ListNode((int) (Math.random() * n));
            curr = curr.next;
        }

        // print the linked list
        System.out.println("Generated Linked List:");
        curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");

        // take pivot value as input
        int pivot = sc.nextInt();
        sc.close();

        // partition the linked list
        head = obj.partList(head, pivot);

        // print the linked list
        System.out.println("Partitioned Linked List around the pivot '" + pivot + "':");
        curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    ListNode partList(ListNode head, int pivot) {
        /* divide the list in two lists
        one greater than x and other less than or equal to x
        this algorithm will only traverse each list node just once
        we could have also swapped the greater values to the end of the list
        but that algorithm would not be 'one pass' */

        if (head == null || head.next == null)
            return head;

        ListNode curr = head, nxt = head.next, prev = null;
        ListNode greaterHead = null, greaterCurr = null;

        while (curr != null) {
            nxt = curr.next;

            if (curr.val >= pivot) {
                // extract this node and append to the new list
                if (prev != null) // or curr != head
                    prev.next = nxt;
                else {
                    // curr == head
                    head = nxt;
                }

                if (greaterHead == null) {
                    greaterHead = curr;
                    greaterCurr = curr;
                    greaterCurr.next = null;
                } else {
                    greaterCurr.next = curr;
                    greaterCurr = greaterCurr.next;
                    greaterCurr.next = null;
                }
            } else {
                prev = curr;
            }

            curr = nxt;
        }

        if (prev == null)
            return greaterHead;

        prev.next = greaterHead;
        return head;
    }
}
