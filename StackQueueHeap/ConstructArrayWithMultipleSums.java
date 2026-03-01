/* LeetCode Quest Heap Q3: "Construct Target Array With Multiple Sums"
https://leetcode.com/problems/construct-target-array-with-multiple-sums/description/
You are given an array target of n integers. From a starting array arr consisting of n 1's, you may
perform the following procedure :
- let x be the sum of all elements currently in your array.
- choose index i, such that 0 <= i < n and set the value of arr at index i to x.
- You may repeat this procedure as many times as needed.
Return true if it is possible to construct the target array from arr, otherwise, return false.

Constraints:
- n == target.length
- 1 <= n <= 5 * 10^4
- 1 <= target[i] <= 10^9

Example 1:
Input: target = [9,3,5]
Output: true
Explanation: Start with arr = [1, 1, 1]
    [1, 1, 1], sum = 3 choose index 1
    [1, 3, 1], sum = 5 choose index 2
    [1, 3, 5], sum = 9 choose index 0
    [9, 3, 5], Done

Example 2:
Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from [1,1,1,1].

Example 3:
Input: target = [8,5]
Output: true

Example 4:
Input: target = [1000000000,1000000000,1000000000,1000000000,1000000000,294967297]
Output: false
Note: Tests the problem constraint edge case.

Example 5:
Input: target = [1,1000000000]
Output: true
Note: Test to fail the naive solution of sequential subtraction and to test the modulus method.

Example 6:
Input: target = [2] or target = [1]
Output: false or true respectively
*/

import java.util.Comparator;
import java.util.PriorityQueue;

class ConstructArrayWithMultipleSums {
    private static final boolean LOGS_ENABLED = true; // set to true to enable statements

    /**
     * At each step the sum of array will keep on increasing.
     * Since we are replacing smaller values with the sum of entire array. <br>
     * Now, simulate the problem in the reverse direction: <br>
     * If an array of n '1's can be created with the given target then the reverse is also true. <br>
     * Here the largest element in the array = sum of the rest of the array in the previous step. <br>
     * At each step remove the greatest element with what should've been in the previous step to
     * get this array. Greatest element should be replaced with = greatest element - sum of the rest
     * = greatest element - (sum of array - greatest element) = 2 * greatest element - sum of array <br>
     * and the sum will be updated as: sum = greatest element at current step <br>
     * BUT !!! Sequential iterations will exceed the time limit.
     * To solve the problem we will use remainder/modulus instead of subtraction. <br>
     * Example testcase: target = [10, 3] <br>
     * SEQUENTIAL: [10, 3] -> [7, 3] -> [4, 3] -> [1, 3] -> [1, 2] -> [1, 1] <br>
     * since the max element is the same index in multiple iterations we can compute this in one go. <br>
     * REMAINDER METHOD: <br>
     * replace max: max - q * sum of rest of the array
     * = remainder of max / sum of rest of the array (here the quotient was 'q')
     * = max % sum of rest of the array <br>
     * Example: [10, 3] -> [1, 3] -> NOTE THAT 3 % 1 = 0 !!! BUT AS SOON AS sum = 1, and we never
     * had any value less than 1 before, it means this array is a special with length = 2 and
     * the only other value is 1, so we can RETURN TRUE IMMEDIATELY.
     */
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;
        for (int t : target) {
            maxHeap.offer(t);
            sum += t;
        }

        while (!maxHeap.isEmpty() && maxHeap.peek() != 1) {
            if (LOGS_ENABLED) {
                System.out.println(maxHeap);
            }
            int max = maxHeap.poll();
            sum -= max; // now stores sum of rest of the array
            if (sum == 1) {
                return true;
            } else if (max < sum || sum == 0 || max % sum < 1) {
                return false; // array of ones can't be created
            } else {
                max = max % (int) sum;
                sum += max; // now stores sum of the entire array
                maxHeap.offer(max);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ConstructArrayWithMultipleSums obj = new ConstructArrayWithMultipleSums();
        int[][] testCases = {
                {9, 3, 5},
                {1, 1, 1, 2},
                {8, 5},
                {1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 294967297},
                {1, 1000000000},
                {2},
                {1}
        };
        for (int[] testCase : testCases) {
            System.out.println("Input: " + java.util.Arrays.toString(testCase));
            System.out.println("Output: " + obj.isPossible(testCase));
        }
    }
}