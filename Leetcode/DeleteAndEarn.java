/* Leetcode 740: "Delete and Earn" similar to - Leetcode 198: "House Robber"
You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

Example 1:
Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.

Example 2:
Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points. */

import java.util.Scanner;

class DeleteAndEarn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        sc.close();
        System.out.println(deleteAndEarn(nums));
    }

    static int deleteAndEarn(int[] nums) {
        int n = (int) 1e4, // given range of nums[i] in the constraints
                freq[] = new int[n + 1]; // to store frequency of numbers from 0 to 10000

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            freq[num]++;

            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        /* Pick any nums[i] and delete it to earn nums[i] points.
            Afterwards, you must delete every element equal to nums[i] - 1 and
            every element equal to nums[i] + 1.
            = if we earn nums[i] we cannot earn from nums[i] - 1 and nums[i] + 1
            = if we earn i * freq[i] we cannot earn from freq[i - 1] and freq[i + 1]
            = keep earning from i, check which is maximum. sum at i - 2 + i * freq[i] OR sum at i - 1
        */

        int prev1 = 0; // stores sum at i - 1
        int prev2 = 0; // stores sum at i - 2

        for (int i = min; i <= max; ++i) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + i * freq[i], prev1);
            prev2 = tmp;
        }

        return prev1; // finally contains the maximum sum
    }
}