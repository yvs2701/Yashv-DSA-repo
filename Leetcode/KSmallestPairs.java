/* Leetcode 373: "Find K Pairs with Smallest Sums"
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k. Define a pair (u,v) which consists of one
element from the first array and one element from the second array. Return the k pairs (u1,v1),(u2,v2), ..., (uk,vk) with the smallest sums.

Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Example 3:
Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]

Example 4:
Input: nums1 = [1,2, 4, 5, 6], nums2 = [3, 5, 7, 9], k = 3
Output: [[1, 3],[2, 3],[1, 5]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class KSmallestPairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt(), n2 = sc.nextInt();
        int[] nums1 = new int[n1], nums2 = new int[n2];

        for (int i = 0; i < n1; i++) nums1[i] = sc.nextInt();
        for (int i = 0; i < n2; i++) nums2[i] = sc.nextInt();

        int k = sc.nextInt();
        sc.close();

        KSmallestPairs ksp = new KSmallestPairs();
        List<List<Integer>> pairs = ksp.kSmallestPairs(nums1, nums2, k);
        System.out.println(pairs);
    }

    // Time Complexity: O(K log K) = O(N log N)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> pairs = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return pairs;
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new CompareListPair());

        for (int i = 0; i < nums1.length && i < k; i++) { // iterates on nums[1]
            minHeap.offer(new int[] { nums1[i], nums2[0], 0 });
        }

        while (k--> 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            pairs.add(List.of(curr[0], curr[1]));
            // the smallest pair = nums1[0] and nums2[0] will be added anyhow
            // after that which pair is smaller ({nums1[1], nums2[0]} or {nums1[0], nums2[1]})
            // is decided by following:

            int nums2idx = curr[2]; // checking new pair, iterating on nums2[]
            if (nums2idx + 1 < nums2.length) {
                minHeap.offer(new int[] { curr[0], nums2[nums2idx + 1], nums2idx + 1 });
            }
        }

        return pairs;
    }

    /* // TIME LIMIT EXCEEDED: (Time complexity: O(N^2 Log N^2) = O(N^2 Log N))
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        TreeSet<List<Integer>> minHeap = new TreeSet<>(new CompareListPair());
    
        for (int i = 0; i < nums1.length; ++i)
            for (int j = 0; j < nums2.length; ++j)
                minHeap.add(Arrays.asList(new Integer[]{nums1[i], nums2[j]}));
    
        return minHeap.stream().limit(k).collect(Collectors.toList());
    } */

    class CompareListPair implements Comparator<int[]> {
        @Override
        public int compare(int[] l1, int[] l2) {
            long sum1 = l1[0] + l1[1];
            long sum2 = l2[0] + l2[1];
            return sum1 < sum2 ? -1 : 1;
            // no equality case (hence the tree set will store duplicates)
        }
    }
}
