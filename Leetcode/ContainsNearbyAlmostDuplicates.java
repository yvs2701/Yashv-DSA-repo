import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3><a href="https://leetcode.com/problems/contains-duplicate-iii/description/">
 * LeetCode 220: Contains Duplicate III
 * </a></h3>
 * <p>
 * You are given an integer array nums and two integers indexDiff and valueDiff.
 * </p>
 * <p>
 * Find a pair of indices (i, j) such that:
 * </p>
 * <ul>
 * <li>i != j,</li>
 * <li>abs(i - j) &lt;= indexDiff,</li>
 * <li>abs(nums[i] - nums[j]) &lt;= valueDiff, and</li>
 * </ul>
 * Return true if such pair exists or false otherwise.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0 <br>
 * Output: true <br>
 * Explanation: We can choose (i, j) = (0, 3).
 * We satisfy the three conditions: <br>
 * i != j --> 0 != 3, <br>
 * abs(i - j) &lt;= indexDiff --> abs(0 - 3) &lt;= 3, <br>
 * abs(nums[i] - nums[j]) &lt;= valueDiff --> abs(1 - 1) &lt;= 0
 * </p>
 * <p>
 * Example 2: <br>
 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3 <br>
 * Output: false <br>
 * Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>2 &lt;= nums.length &lt;= 10^5</li>
 * <li>-10^9 &lt;= nums[i] &lt;= 10^9</li>
 * <li>1 &lt;= indexDiff &lt;= nums.length</li>
 * <li>0 &lt;= valueDiff &lt;= 10^9</li>
 * </ul>
 * </p>
 */
public class ContainsNearbyAlmostDuplicates {
    static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        final Map<Long, Long> buckets = new HashMap<>();

        // `valueDiff` can be 0, which can result in divide by zero error
        // while calculating `bucketId`
        final int bucketSize = valueDiff + 1;

        for (int i = 0; i < nums.length; i++) {
            final long bucketId = getBucketId(nums[i], bucketSize);

            if (buckets.containsKey(bucketId)
                    || buckets.containsKey(bucketId - 1) && nums[i] - buckets.get(bucketId - 1) <= valueDiff
                    || buckets.containsKey(bucketId + 1) && buckets.get(bucketId + 1) - nums[i] <= valueDiff) {
                return true;
            }

            // remove buckets storing obsolete index
            if (buckets.size() == indexDiff) {
                buckets.remove(getBucketId(nums[i - indexDiff], valueDiff + 1));
            }

            // add current bucket
            buckets.put(bucketId, (long) nums[i]);
        }

        return false;
    }

    public static void main(String[] args) {
        int[][][] testcases = {
                {{1, 2, 3, 1}, {3, 0}},
                {{1, 5, 9, 1, 5, 9}, {2, 3}}
        };

        for (int[][] testcase : testcases) {
            System.out.println("Input: " + Arrays.toString(testcase[0]) + " indexDiff = " + testcase[1][0] + ", valueDiff = " + testcase[1][1]);
            System.out.println("Output: " + containsNearbyAlmostDuplicate(testcase[0], testcase[1][0], testcase[1][1]) + "\n");
        }
    }

    /**
     * If we divide numbers by N, then all the numbers in the range
     * [a, a + N - 1] will have the same result/quotient.
     * Which can be used as `bucketId`.
     */
    private static long getBucketId(long num, long size) {
        /* // convert all numbers to positive to avoid negative and positive
        // numbers' buckets colliding (numbers in range [-N, N] will have same
        // `bucketId` if divided by any number > N).
        // `- Integer.MIN_VALUE` (2^31) is chosen, because as per the constraints
        // -10^9 <= nums[i] <= 10^9 and we know 2^31 > 10^9
        final long num = (long) nums[i] - Integer.MIN_VALUE;
        // If we divide numbers by N, then all the numbers in the range
        // [a, a + N - 1] will have the same result/quotient (= `bucket` here).
        // `valueDiff` can be 0, hence we need to change it to avoid
        // dividing by zero error.
        final long bucket = num / ((long) valueDiff + 1);
        return bucket; */
        if (num >= 0) { // positive numbers
            return num / size;
        }
        // negative numbers
        return ((num + 1) / size) - 1;
    }
}
