import java.util.Arrays;
import java.util.HashMap;

/**
 * <h3><a href="https://leetcode.com/problems/make-sum-divisible-by-p/">
 * Leetcode 1590: Make Sum Divisible by P
 * </a></h3>
 * <p>
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty)
 * such that the sum of the remaining elements is divisible by p. It is not allowed to remove
 * the whole array.
 * </p>
 * <p>
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 * </p>
 * <p>
 * A subarray is defined as a contiguous block of elements in the array.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: nums = [3,1,4,2], p = 6 <br>
 * Output: 1 <br>
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove
 * the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * </p>
 * <p>
 * Example 2: <br>
 * Input: nums = [6,3,5,2], p = 9 <br>
 * Output: 2 <br>
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to
 * remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * </p>
 * <p>
 * Example 3: <br>
 * Input: nums = [1,2,3], p = 3 <br>
 * Output: 0 <br>
 * Explanation: Here the sum is 6, which is already divisible by 3. Thus we do not need to remove anything.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li> 1 &lt;= nums.length &lt;= 10<sup>5</sup> </li>
 * <li> 1 &lt;= nums[i] &lt;= 10<sup>9</sup> </li>
 * <li> 1 &lt;= p &lt;= 10<sup>9</sup> </li>
 * </ul>
 * </p>
 */
class MakeSumDivisibleByP {
    /**
     * <p>
     * From the prefix sum algorithm we know a subarray sum a+1...b is divisible by p
     * if the sum from 0...a leaves the same remainder as 0...b when divided by p.
     * Mathematical Reason: <br>
     * Let S1 be the sum from 0...a <br>
     * Let S2 be the sum from 0...b and b > a. <br>
     * If S1 % p = S2 % p, <br>
     * then (S2 - S1) % p = 0 <br>
     * which means S2 - S1 is divisible by p.
     * </p>
     * <p>
     * Similarly, if sum of all numbers 0...n in array leaves the same remainder
     * as the sum of the numbers in the subarray a...b on dividing by p,
     * then the array without that subarray will be divisible by p. <br>
     * <b>We only need to find the smallest subarray which leaves the same remainder as
     * the entire array when divide by p.</b><br>
     * Which means we need to find MIN(b - a + 1) such that <br>
     * (Sb - Sa) % p = Sn % p where Sx means Sum of numbers in array from 0...x <br>
     * (Sb % p) - (Sa % p) = Sn % p <br>
     * (Sb % p) - (Sn % p) = Sa % p <br>
     * = We need to find MIN(b - a + 1) such that <br>
     * Remainder of sum 0...b - Remainder of total sum = Remainder of sum 0...a <br>
     * Where b > a, which means we can already store the Sa % p <br>
     * and when Sb % p - Sn % p is equivalent (in case of negative remainder)
     * to Sa % p we need to find if the length of the subarray = b - a + 1 is the minimum. <br>
     * To find the positive equivalent of negative remainders: <br>
     * Let, S3 = k * c3 - r3 <br>
     * = k * c3 - k + k - r3 <br>
     * = k * (c3 - 1) + k - r3 <br>
     * = k * c4 + r4 <br>
     * Which means r4 = k - r3 <br>
     * Just like -5 and 0 and 5 leave an equal remainder of 0 when divided by k. <br>
     * Similarly -6 and -1 and 4 leave the same/equivalent remainder = 4 (= -1).
     * </p>
     */
    static int minSubarray(int[] nums, int p) {
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Find remainder when total sum is divided by p
        int rem = (int) (totalSum % p);
        if (rem == 0) return 0; // If remainder is 0, no subarray needs to be removed

        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);  // Initialize to handle full prefix
        long prefixSum = 0;
        int minLength = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int currentRem = (int) (prefixSum % p);
            int targetRem = (currentRem - rem) % p;
            if (targetRem < 0) targetRem += p;

            if (prefixSumMap.containsKey(targetRem)) {
                minLength = Math.min(minLength, i - prefixSumMap.get(targetRem));
            }

            prefixSumMap.put(currentRem, i);
        }

        return minLength == nums.length ? -1 : minLength;
    }

    public static void main(String[] args) {
        int[][][] testCases = {
                {{3, 1, 4, 2}, {6}},
                {{6, 3, 5, 2}, {9}},
                {{1, 2, 3}, {3}}
        };

        for (int[][] testCase : testCases) {
            int output = minSubarray(testCase[0], testCase[1][0]);
            System.out.printf("Input:\n\tnums: %s\n\tp: %d\n", Arrays.toString(testCase[0]), testCase[1][0]);
            System.out.printf("output: %d\n", output);
        }
    }
}
