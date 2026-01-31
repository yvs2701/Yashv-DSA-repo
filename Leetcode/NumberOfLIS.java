/* Leetcode 673: "Number of longest increasing subsequence"
Given an integer array nums, return the number of longest increasing subsequences.

Example 1:
  Input: nums = [1,3,5,4,7]
  Output: 2
  Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
  Input: nums = [2,2,2,2,2]
  Output: 5
  Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
*/

class NumberOfLIS {
    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 4, 7, 6 };
        System.out.println(findNumberOfLIS(nums));
    }

    static int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1)
            return nums.length;

        int longest[] = new int[nums.length];
        int count[] = new int[nums.length];

        int max = 1, cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            longest[i] = 1;
            count[i] = 1;

            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (longest[i] < longest[j] + 1) {
                        longest[i] = longest[i] + 1;
                        count[i] = count[j];
                    } else if (longest[i] == longest[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }

            if (longest[i] == max) {
                cnt += count[i];
            } else if (longest[i] > max) {
                max = longest[i];
                cnt = count[i];
            }
        }

        return cnt;
    }
}