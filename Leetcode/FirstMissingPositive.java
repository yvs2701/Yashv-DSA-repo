/**
 * <h3>Leetcode 41: First Missing Positive </h3>
 * <p>(#Hashing, #Array)</p>
 * <p>
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 * </p>
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * </p>
 * <p>
 * <b>Example 1:</b> <br>
 * Input: nums = [1,2,0] <br>
 * Output: 3 <br>
 * Explanation: The numbers in the range [1,2] are all in the array. <br>
 * </p>
 * <p>
 * <b>Example 2:</b> <br>
 * Input: nums = [3,4,-1,1] <br>
 * Output: 2 <br>
 * Explanation: 1 is in the array but 2 is missing. <br>
 * </p>
 * <p>
 * <b>Example 3:</b> <br>
 * Input: nums = [7,8,9,11,12] <br>
 * Output: 1 <br>
 * Explanation: The smallest positive integer 1 is missing. <br>
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li> 1 &lt;= nums.length &lt;= 10^5 </li>
 * <li> -2^31 &lt;= nums[i] &lt;= 2^31 - 1 </li>
 * </ul>
 * </p>
 */
class FirstMissingPositive {

    /**
     * To solve in O(n) time:
     * <ul>
     * <li>First place all the negative numbers and 0 to the start of the array.</li>
     * <li>Also, finds the minimum positive number.</li>
     * <li>Then use specialized cycle sort to sort positive numbers.</li>
     * <li>Skip any number which need to be placed at an out of bound index.</li>
     * </ul>
     */
    public static int firstMissing(int[] nums) {
        int n = nums.length, start = 0, currIdx;
        /* PLACE NEGATIVE NUMBERS AND 0 AT THE START */
        while (start < n && nums[start] <= 0) {
            start++;
        }
        currIdx = start + 1;

        int minPositive = start < n ? nums[start] : nums[0];
        while (currIdx < n) {
            if (nums[currIdx] <= 0) {
                swap(nums, currIdx, start);
                while (start < n && nums[start] <= 0) {
                    start++;
                }
                currIdx = start + 1;
            } else {
                minPositive = Math.min(nums[currIdx], minPositive);
                currIdx++;
            }
        }

        /* CYCLE SORT POSITIVE NUMBERS */
        int offset = start - minPositive; // final index for numbers = number + offset
        currIdx = start;

        while (currIdx < n) {
            int swapPos = nums[currIdx] + offset; // might overflow
            if (swapPos >= 0 && swapPos < n && nums[swapPos] != nums[currIdx]) {
                swap(nums, currIdx, swapPos);
            } else {
                currIdx++;
            }
        }

        /* SEARCH FOR MINIMUM POSITIVE NUMBER WHICH IS MISSING */
        int expectedNumber = 1; // minimum positive number = 1
        for (currIdx = start; currIdx < n; currIdx++) {
            if (expectedNumber != nums[currIdx])
                break; // found
            else
                expectedNumber++;
        }
        return expectedNumber;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        arr[idx1] = arr[idx1] ^ arr[idx2];
        arr[idx2] = arr[idx1] ^ arr[idx2];
        arr[idx1] = arr[idx1] ^ arr[idx2];
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 0},
                {3, 4, -1, 1},
                {7, 8, 9, 11, 12},
                {0},
                {1},
                {2147483647, 2147483647, 2147483646, 2147483645, -2147483648}
        };
        for (int[] testCase : testCases) {
            System.out.print("Input: ");
            for (int i : testCase) {
                System.out.print(i + " ");
            }
            System.out.println();

            System.out.println("Output:\n" + firstMissing(testCase));

            System.out.print("Array after processing: ");
            for (int i : testCase) {
                System.out.print(i + " ");
            }
        }
    }
}