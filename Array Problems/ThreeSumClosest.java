import java.util.Arrays;

/**
 * <h3><a href="https://leetcode.com/problems/3sum-closest/description/">
 * LeetCode 16: 3Sum Closest
 * </a></h3>
 * <p>
 * Given an integer array nums of length n and an integer target, find three integers
 * at distinct indices in nums such that the sum is closest to target.
 * </p>
 * <p>
 * Return the sum of the three integers.
 * </p>
 * <p>
 * You may assume that each input would have exactly one solution.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: nums = [-1,2,1,-4], target = 1 <br>
 * Output: 2 <br>
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * </p>
 * <p>
 * Example 2: <br>
 * Input: nums = [0,0,0], target = 1 <br>
 * Output: 0 <br>
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li> 3 &lt;= nums.length &lt;= 500 </li>
 * <li> -1000 &lt;= nums[i] &lt;= 1000 </li>
 * <li> -10^4 &lt;= target &lt;= 10^4 </li>
 * </ul>
 * </p>
 */
public class ThreeSumClosest {
    static int threeSumClosest(int[] nums, int target) {
        // sort the array
        mergeSort(nums, 0, nums.length - 1);

        // find the sum closes to target
        int sum = 0, minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right && minDiff > 0) {
                int _sum = nums[i] + nums[left] + nums[right];
                int diff = target > _sum ? target - _sum : _sum - target;

                if (_sum > target) {
                    right--;
                } else if (_sum < target) {
                    left++;
                }

                if (diff < minDiff) {
                    sum = _sum;
                    minDiff = diff;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] testcases = {
                {-1, 2, 1, -4},
                {0, 0, 0},
                {0, 1, 2},
                {4, 0, 5, -5, 3, 3, 0, -4, -5}
        };
        int[] targets = {1, 1, 0, -2};

        for (int i = 0; i < testcases.length; i++) {
            System.out.println("Input: nums = " + Arrays.toString(testcases[i]) + ", target = " + targets[i]);
            System.out.println("Output: " + threeSumClosest(testcases[i], targets[i]));
            System.out.println();
        }
    }

    /**
     * Sorts the input array, in-place.
     */
    private static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            final int mid = start + (end - start) / 2;

            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            // copy the left subarray (to prevent losing data while inserting elements)
            int[] left = new int[mid - start + 1];
            System.arraycopy(arr, start, left, 0, left.length);

            // merge the left and right subarrays in sorted order
            int i = 0, j = mid + 1;
            while (i < left.length && j <= end) {
                if (left[i] <= arr[j]) {
                    arr[start++] = left[i++];
                } else {
                    arr[start++] = arr[j++];
                }
            }
            while (i < left.length) {
                arr[start++] = left[i++];
            }
            while (j <= end) {
                arr[start++] = arr[j++];
            }
        }
    }
}
