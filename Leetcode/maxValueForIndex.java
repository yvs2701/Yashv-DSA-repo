/* Leetcode 1802: "Maximum Value at a Given Index in a Bounded Array"
You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
    nums.length == n
    nums[i] is a positive integer where 0 <= i < n.
    abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
    The sum of all the elements of nums does not exceed maxSum.
    nums[index] is maximized.
Return nums[index] of the constructed array.
Note that abs(x) equals x if x >= 0, and -x otherwise.

Example 1:
    Input: n = 4, index = 2,  maxSum = 6
    Output: 2
    Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
    There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].

Example 2:
    Input: n = 6, index = 1,  maxSum = 10
    Output: 3
*/

import java.util.Scanner;

class maxValueForIndex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(maxValue(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        sc.close();
    }

    private static int maxValue(int n, int index, int maxSum) {
        /*  Given:
                n: nums.length
                index: at which value needs to be maximum
                maxSum: maximum sum contraint of the `nums` array
            Conditions:
                nums[i] > 0; where 0 <= i < n
                abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1
                The sum of all the elements of nums does not exceed maxSum
            Objective:
                Maximize nums[index]
            
            Conclusion:
                Since nums[index] must be maximized first initialize the nums array
                with smallest value possible (= 1; according to the given condition)
                Also the difference between adjacent numbers can be either 1, 0, or -1.
        
                So we need to increase the nums[index] one by one and depending on it
                we need to increase values of surrounding elements too.
        
                Since nums[index] has to be maximized the adjacent elements should always
                be one one less (diff = nums[i + 1] - nums[i] = -1 on right and +1 on left)
                progressively forming a triangle like structure.
        */

        // smartly using binary search to optimize searching through values of nums[index]
        int left = 1, right = maxSum;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (getSum(index, mid, n) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static long getSum(int index, int value, int n) {
        long count = 0;

        // On index's left:
        // If 'value' > 'index', there are index + 1 numbers in the arithmetic sequence:
        // ['value' - 'index', ..., 'value' - 1, 'value'].
        // Otherwise, there are 'value' numbers in the arithmetic sequence:
        // [1, 2, ..., 'value' - 1, value], plus a sequence of length ('index' - 'value' + 1) of 1s.
        if (value > index) {
            count += (long) (value + value - index) * (index + 1) / 2;
        } else {
            count += (long) (value + 1) * value / 2 + index - value + 1;
        }

        // On index's right:
        // If value >= n - index, there are n - index numbers in the arithmetic sequence:
        // [value, value - 1, ..., value - n + 1 + index].
        // Otherwise, there are value numbers in the arithmetic sequence:
        // [value, value - 1, ..., 1], plus a sequence of length (n - index - value) of 1s. 
        if (value >= n - index) {
            count += (long) (value + value - n + 1 + index) * (n - index) / 2;
        } else {
            count += (long) (value + 1) * value / 2 + n - index - value;
        }

        return count - value; // since we added `value` twice (both in left and right sums)
    }
}