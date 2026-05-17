import java.util.ArrayList;
import java.util.Arrays; // only for debugging/printing results
import java.util.List;

/**
 * <h3>
 * <a href="https://leetcode.com/problems/minimum-absolute-difference/description/">
 * Leetcode 1200: Minimum Absolute Difference
 * </a>
 * </h3>
 * <p>
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
 * </p>
 * <p>
 * Return a list of pairs in ascending order (with respect to pairs), each pair [a, b] follows:
 * <ul>
 * <li>a, b are from arr</li>
 * <li>a &lt; b</li>
 * <li>b - a equals to the minimum absolute difference of any two elements in arr</li>
 * </ul>
 * </p>
 * <p>
 * Example 1: <br>
 * Input: arr = [4,2,1,3] <br>
 * Output: [[1,2],[2,3],[3,4]] <br>
 * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
 * </p>
 * <p>
 * Example 2: <br>
 * Input: arr = [1,3,6,10,15] <br>
 * Output: [[1,3]] <br>
 * </p>
 * <p>
 * Example 3: <br>
 * Input: arr = [3,8,-10,23,19,-4,-14,27] <br>
 * Output: [[-14,-10],[19,23],[23,27]] <br>
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>2 &lt;= arr.length &lt;= 10^5</li>
 * <li>-10^6 &lt;= arr[i] &lt;= 10^6</li>
 * </ul>
 * </p>
 */
public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        int[][] testcases = {
                {4, 2, 1, 3},
                {1, 3, 6, 10, 15},
                {3, 8, -10, 23, 19, -4, -14, 27}
        };

        for (int[] testcase : testcases) {
            System.out.println("Input: " + Arrays.toString(testcase));
            System.out.println("Output: " + minimumAbsDifference(testcase));
        }
    }

    /**
     * Uses merge sort to sort the array anf then find the pairs
     * with minimum difference from the adjacent indices.
     *
     * @see #mergeSort(int[], int, int)
     */
    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        int size = arr.length;
        List<List<Integer>> pairs = new ArrayList<>();
        // sort array
        mergeSort(arr);

        // find minimum and insert the pairs
        int min = arr[size - 1] - arr[0];
        for (int i = 1; i < size; i++) {
            int difference = arr[i] - arr[i - 1];
            if (difference < min) {
                min = difference;
                pairs.clear();
                pairs.add(List.of(arr[i - 1], arr[i]));
            } else if (difference == min) {
                pairs.add(List.of(arr[i - 1], arr[i]));
            }
        }

        return pairs;
    }

    /**
     * Helper overloaded function, simply to call the other overloaded function with the default values.
     */
    private static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursively splits the array and merges the smaller subarray in the ascending order.
     * This sorts the array in-place and returns 'void'.
     */
    private static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            final int mid = start + (end - start) / 2;
            /* The following split will not work:
            ```
            mergeSort(arr, start, mid - 1);
            mergeSort(arr, mid, end);
            ```
            when start = 0, end = 1, then mid = -1
            then the next function call for the left sub array (from start to mid)
            will do nothing,
            then the next function call for the right sub array (from mid to end)
            will have negative index.
            In that call, start = -1, right = 1, and mid = 0
            after this the next to function calls will be:
            from -1 to 0
            from 0 to 1 (just like the last call !!!) - creating an infinite loop
            */
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

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
