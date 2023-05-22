import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

/* Leetcode 347: "Top K Frequent Elements"
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
Example 2:
    Input: nums = [1, 2], k = 2
    Output: [1, 2] */

class KMostFrequent {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // INPUT
        int n = sc.nextInt(), nums[] = new int[n], k;
        for (int i = 0; i < n; ++i)
            nums[i] = sc.nextInt();
        k = sc.nextInt();
        sc.close();

        // START finding Top K frequent elements
        // store frequency of each element
        HashMap<Integer, Integer> freq = new HashMap<>(n);
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        // sort all the numbers by their frequency values
        TreeSet<Entry<Integer, Integer>> sorted = new TreeSet<>((o1, o2) -> o1.getValue() > o2.getValue() ? -1 : 1);
        sorted.addAll(freq.entrySet());

        // OUTPUT
        for (Entry<Integer, Integer> entry : sorted) {
            if (k-- == 0)
                break;
            System.out.print(entry.getKey() + " ");
        }
        System.out.println();
    }
}
