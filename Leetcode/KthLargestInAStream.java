/* Leetcode 703: "Kth Largest Element in a Stream"
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
Implement KthLargest class:
- KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
- int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
Example 1:
Input:
    k = 3
    array = [4, 5, 8, 2]
    add = 3, 5, 10, 9, 4
Output:
    [4, 5, 5, 8, 8]
Explanation:
    KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
    kthLargest.add(3);   // return 4
    kthLargest.add(5);   // return 5
    kthLargest.add(10);  // return 5
    kthLargest.add(9);   // return 8
    kthLargest.add(4);   // return 8
Example 2:
Input:
    k = 2
    array = [0]
    add = -1, 1, -2, -4, 3
Output:
    [-1, 0, 0, 0, 1] */

import java.util.ArrayList;

class KthLargestInAStream {
    private ArrayList<Integer> arr;
    private int k;

    private KthLargestInAStream(int k, int[] nums) {
        this.k = k;
        this.arr = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; ++i)
            this.add(nums[i]);
    }

    private int add(int val) {
        int n = this.arr.size();
        if (this.arr.size() == 0) {
            this.arr.add(val);
            ++n;
            return this.arr.get(n - 1);
        }

        // add the element using insertion sort
        int i = 0;
        for (; i < n; ++i) {
            if (this.arr.get(i) < val)
                break;
        }

        this.arr.add(i, val);
        ++n;

        return (this.k > n) ? this.arr.get(n - 1) : this.arr.get(this.k - 1);
    }

    public static void main(String[] args) {
        KthLargestInAStream kthLargest = new KthLargestInAStream(3, new int[] { 4, 5, 8, 2 });
        int[] toAdd = { 3, 5, 10, 9, 4 };
        for (int i = 0; i < toAdd.length; ++i)
            System.out.print(kthLargest.add(toAdd[i]) + " ");
        System.out.println();
    }
}
/* OPTIMAL SOLUTION: Using heap
Given N as the length of nums and M as the number of calls to add(),
Time complexity: O(N * log⁡(N) + M * log⁡(k))
Space Complexity: O(N) only when initializing, after we call KthLargest.add(int) we limit the size to K.

class KthLargest {
    private static int k;
    private PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>();

        for (int num: nums) {
            heap.offer(num);
        }

        while (heap.size() > k) {
            heap.poll();
        }
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }

        return heap.peek();
    }
} */
