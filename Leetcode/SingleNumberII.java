/* LeetCode 137: "Single Number II"
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
    Input: nums = [2,2,3,2]
    Output: 3
Example 2:
    Input: nums = [0,1,0,1,0,1,99]
    Output: 99
Constraints:
    1 <= nums.length <= 3 * 10^4
    -2^31 <= nums[i] <= 2^31 - 1
    Each element in nums appears exactly three times except for one element which appears once. */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class SingleNumberII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(singleNumber(nums));
    }

    static int singleNumber(int[] nums) {
        // elements can have frequency of 1 or 3 only
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            // if mapping n=f doesnt exist in the map f = null in the below function:
            int prev = freq.compute(num, (n, f) -> (f == null) ? 1 : f + 1);
            if (prev == 3)
                freq.remove(num); // remove all elements having frequency = 3
        }

        // now map contains only 1 mapping
        return getOnlyElementFrom(freq.keySet());
    }

    static <E> E getOnlyElementFrom(Iterable<E> iterable) {
        Iterator<E> iterator = iterable.iterator();

        if (!iterator.hasNext()) {
            throw new RuntimeException("Collection is empty");
        }

        E element = iterator.next();

        if (iterator.hasNext()) {
            throw new RuntimeException("Collection contains more than one item");
        }

        return element;
    }
}