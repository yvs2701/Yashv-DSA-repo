import java.util.HashMap;
import java.util.Map;

/**
 * <h3><a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/">
 * Leetcode 974: Subarray Sums Divisible by K
 * </a></h3>
 * <p>
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 * </p>
 * <p>
 * A subarray is a contiguous part of an array.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: nums = [4,5,0,-2,-3,1], k = 5 <br>
 * Output: 7 <br>
 * Explanation: There are 7 subarrays with a sum divisible by k = 5: [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3].
 * </p>
 * <p>
 * Example 2: <br>
 * Input: nums = [5], k = 9 <br>
 * Output: 0 <br>
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></li>
 * <li>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></li>
 * <li>2 &lt;= k &lt;= 10<sup>4</sup></li>
 * </ul>
 * </p>
 */
class PrefixSumSubarraySumDivisibleByK {
    /** <h3>Prefix sum logic</h3>
     * <p>
     * This approach is used usually when the sums and remainder
     * or divisions are needed in an array.
     * </p>
     * <p>
     * The prefix sum approach relies on the property that if the
     * sum of elements from index 0 to 'a' (let's call it S1) and
     * the sum of elements from index 0 to 'b' (S2) leave the same remainder
     * when divided by 'K', then the sum of the subarray between them
     * (from a+1 to b) must be divisible by 'K'. So we just need to check if
     * we are seeing a remainder again to count the subarrays.
     * </p>
     * <h3>Mathematical explanation:</h3>
     * <p>
     * If S1 = K * c1 + r1 <br>
     * And S2 = K * c2 + r2 <br>
     * Then the sum of the subarray (S2 - S1) = (K * c2 + r1) - (K * c1 + r2) <br>
     * = K * (c2 - c1) = K * c3 <br>
     * Since the result is a multiple of 'K', the subarray sum is divisible by 'K'. <br>
     * Also if the remainder is negative like (-r3), then we need to find its
     * positive equivalent to know if we are seeing a remainder again (or at least its equivalent.) <br>
     * Let, S3 = k * c3 - r3 <br>
     * = k * c3 - k + k - r3 <br>
     * = k * (c3 - 1) + k - r3 <br>
     * = k * c4 + r4 <br>
     * Which means r4 = k - r3 <br>
     * <br>
     * Just like -5 and 0 and 5 leave an equal remainder of 0 when divided by k. <br>
     * Similarly -6 and -1 and 4 leave the same/equivalent remainder = 4 (= -1).
     * </p>
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, 1);

        int count = 0, sum = 0;
        for (int n : nums) {
            sum = (sum + n) % k;
            if (sum < 0)
                sum += k;
            int prevOccurences = remainderMap.getOrDefault(sum, 0);
            count += prevOccurences;
            remainderMap.put(sum, prevOccurences + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        PrefixSumSubarraySumDivisibleByK solution = new PrefixSumSubarraySumDivisibleByK();
        System.out.println(solution.subarraysDivByK(new int[] { 4, 5, 0, -2, -3, 1 }, 5)); // Output: 7
        System.out.println(solution.subarraysDivByK(new int[] { 5 }, 9)); // Output: 0
    }
}