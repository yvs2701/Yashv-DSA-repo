/**
 * <h3><a href="https://leetcode.com/problems/ways-to-make-a-fair-array/description/">
 * Leetcode 1664. Ways to Make a Fair Array
 * </a></h3>
 * <p>
 * You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. <br>
 * Notice that the index of the elements may change after the removal. <br>
 * For example, if nums = [6,1,7,4,1]: <br>
 * Choosing to remove index 1 results in nums = [6,7,4,1]. <br>
 * Choosing to remove index 2 results in nums = [6,1,4,1]. <br>
 * Choosing to remove index 4 results in nums = [6,1,7,4]. <br>
 * <br>
 * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values. <br>
 * Return the number of indices that you could choose such that after the removal, nums is fair. <br>
 * </p>
 * <p>
 * Example 1: <br>
 * Input: nums = [2,1,6,4] <br>
 * Output: 1 <br>
 * Explanation: <br>
 * Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair. <br>
 * Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair. <br>
 * Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair. <br>
 * Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair. <br>
 * There is 1 index that you can remove to make nums fair. <br>
 * <br>
 * Example 2: <br>
 * Input: nums = [1,1,1] <br>
 * Output: 3 <br>
 * Explanation: You can remove any index and the remaining array is fair. <br>
 * <br>
 * Example 3: <br>
 * Input: nums = [1,2,3] <br>
 * Output: 0 <br>
 * Explanation: You cannot make a fair array after removing any index. <br>
 * <br>
 * Constraints: <br>
 * 1 <= nums.length <= 10^5 <br>
 * 1 <= nums[i] <= 10^4 <br>
 * </p>
 */
public class WaysToMakeFairArray {
    /**
     * <p>
     * Let {a, w, b, x, c, y, d, z} be the array of numbers (= nums[]), then <br>
     * Original total sum of even (= totalEven) = a + b + c + d <br>
     * Original total sum of odd (= totalOdd) = w + x + y + z <br>
     * </p>
     * <p>
     * If we remove "x" at index = 3 (= odd index), then <br>
     * Array becomes = {a, w, b, (removed "x") c, y, d, z} <br>
     * Sum of even till now (= sumEven) = a + b <br>
     * Sum of odd till now (= sumOdd) = w <br>
     * <br>
     * Sum of all even = a + b + y + z = sumEven + totalOdd - sumOdd - nums[i] <br>
     * Sum of all odd = w + c + d = sumOdd + totalEven - sumEven <br>
     * </p>
     * <p>
     * If we remove "c" at index = 4 (= even index), then <br>
     * Array becomes = {a, w, b, x, (removed "c") y, d, z} <br>
     * Sum of even till now (= sumEven) = a + b <br>
     * Sum of odd till now (= sumOdd) = w + x <br>
     * <br>
     * Sum of all even = a + b + y + z = sumEven + totalOdd - sumOdd <br>
     * Sum of all odd = w + x + d = sumOdd + totalEven - sumEven - nums[i] <br>
     * </p>
     */
    public static int waysToMakeFair(int[] nums) {
        int totalOdd = 0, totalEven = 0, sumOdd = 0, sumEven = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1) { // odd index
                totalOdd += nums[i];
            } else { // even index
                totalEven += nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1) { // odd index
                if (sumOdd + totalEven - sumEven == sumEven + totalOdd - sumOdd - nums[i]) {
                    count++;
                }
                sumOdd += nums[i];
            } else { // even index
                if (sumOdd + totalEven - sumEven - nums[i] == sumEven + totalOdd - sumOdd) {
                    count++;
                }
                sumEven += nums[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] testcases = {
                {6, 1, 7, 4, 1},
                {2, 1, 6, 4},
                {1, 1, 1},
                {1, 2, 3}
        };

        for (int[] testcase : testcases) {
            System.out.println("Input:");
            for (int num : testcase) {
                System.out.print(num + " ");
            }
            System.out.println("\nOutput: " + waysToMakeFair(testcase));
        }
    }
}
