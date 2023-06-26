/* Leetcode 198: "House Robber"
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping
you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent
houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12. */

import java.util.Scanner;

class HouseRobbery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] houses = new int[n];
        for (int i = 0; i < n; i++)
            houses[i] = sc.nextInt();
        sc.close();

        System.out.println(rob(houses));
    }
    static int rob(int[] nums) {
        /* A robber has 2 options: a) rob current house i; b) don't rob current house.
            If option "a" is selected it means robber can't rob previous i-1 house but
            can safely proceed to the one before previous i-2 and gets all cumulative
            loot that follows.
            If option "b" is selected the robber gets all the possible loot from robbery
            of i-1 and all the following buildings.

            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        */
        int[] dp = new int[nums.length];

        // max profit at 1st house (index 1) = MAX of
        // -> rob this house and rob house at index -2 (doesn't exist) = nums[0],
        // -> dont rob this house and rob house at index -1 (doesn't exist) = 0
        // = nums[0]
        dp[0] = nums[0];

        // max profit at 2st house (index 1) = MAX of
        // -> rob this house and rob house at index -1 (doesn't exist) = nums[1],
        // -> dont rob this house and rob house at index 0 = nums[0]
        // = MAX of nums[0] and nums[1]
        if (nums.length > 1)
            dp[1] = Math.max(nums[1], nums[0]);
        else
            return dp[0];

        for (int i = 2; i < nums.length; ++i) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}
