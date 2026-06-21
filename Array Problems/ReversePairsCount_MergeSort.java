import java.util.Arrays;

/**
 * <h3><a href="https://leetcode.com/problems/reverse-pairs/description/">Leetcode 493: Reverse Pairs</a></h3>
 * <p>
 * Given an integer array nums, return the number of reverse pairs in the array. <br>
 * A reverse pair is a pair (i, j) where:
 * <ul>
 * <li>0 &lt;= i &lt; j &lt; nums.length and</li>
 * <li>nums[i] &gt; 2 * nums[j]</li>
 * </ul>
 * </p>
 * <p>
 * <b>Examples:</b> <br>
 * <b>Example 1:</b> <br>
 * Input: nums = [1,3,2,3,1] <br>
 * Output: 2 <br>
 * Explanation: The reverse pairs are:
 * <ul>
 * <li>(1, 4) --&gt; nums[1] = 3, nums[4] = 1, 3 &gt; 2 * 1</li>
 * <li>(3, 4) --&gt; nums[3] = 3, nums[4] = 1, 3 &gt; 2 * 1</li>
 * </ul>
 * <b>Example 2:</b> <br>
 * Input: nums = [2,4,3,5,1] <br>
 * Output: 3 <br>
 * Explanation: The reverse pairs are:
 * <ul>
 * <li>(1, 4) --&gt; nums[1] = 4, nums[4] = 1, 4 &gt; 2 * 1</li>
 * <li>(2, 4) --&gt; nums[2] = 3, nums[4] = 1, 3 &gt; 2 * 1</li>
 * <li>(3, 4) --&gt; nums[3] = 5, nums[4] = 1, 5 &gt; 2 * 1</li>
 * </ul>
 * </p>
 * <p>
 * <b>Constraints:</b>
 * <ul>
 * <li>1 &lt;= nums.length &lt;= 5 * 10^4</li>
 * <li>-2^31 &lt;= nums[i] &lt;= 2^31 - 1</li>
 * </ul>
 * </p>
 */
public class ReversePairsCount_MergeSort {
    static int revPairsCount = 0;

    static int reversePairs(int[] nums) {
        /* Brute Force: */
        /* int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > 2 * nums[j]) {
                    count++;
                }
            }
        }
        return count; */

        /* Merge Sort based approach */
        revPairsCount = 0;
        mergeSort(nums, 0, nums.length - 1);
        return revPairsCount;
    }

    public static void main(String[] args) {
        int[][] testcases = new int[][]{
                {1, 3, 2, 3, 1},
                {2, 4, 3, 5, 1},
                {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647}
        };

        for (int[] testcase : testcases) {
            System.out.println("Input: " + Arrays.toString(testcase));
            System.out.println("Reverse Pairs Count: " + reversePairs(testcase));
            System.out.println();
        }
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            final int mid = start + (end - start) / 2;

            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            // COUNT REVERSE PAIRS:
            for (int i = start; i <= mid; i++) {
                int j = mid + 1;
                for (; j <= end &&
                        arr[i] > (long) arr[j] << 1; j++)
                    ;
                revPairsCount += j - (mid + 1); // time/calculations optimization
            }

            // MERGE ARRAYS IN SORTED ORDER:
            // copy the left sub array as it might get overridden if values from
            // right sub array are placed in the sorted order
            int[] leftSubArray = new int[mid - start + 1];
            int idx = start, leftIdx = 0, rightIdx = mid + 1;

            while (leftIdx < leftSubArray.length) {
                leftSubArray[leftIdx++] = arr[idx++];
            }

            idx = start;
            leftIdx = 0;
            while (idx <= end && leftIdx < leftSubArray.length && rightIdx <= end) {
                if (leftSubArray[leftIdx] <= arr[rightIdx]) {
                    arr[idx++] = leftSubArray[leftIdx++];
                } else {
                    arr[idx++] = arr[rightIdx++];
                }
            }
            while (leftIdx < leftSubArray.length) {
                arr[idx++] = leftSubArray[leftIdx++];
            }
            while (rightIdx <= end) {
                arr[idx++] = arr[rightIdx++];
            }
        }
    }
}
