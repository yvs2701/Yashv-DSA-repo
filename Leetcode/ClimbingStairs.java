import java.util.Scanner;

/* Leetcode 70
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Example 1:
    Input: n = 2
    Output: 2
    Explanation: There are two ways to climb to the top.
    - 1 + 1
    - 2
Example 2:
    Input: n = 3
    Output: 3
    Explanation: There are three ways to climb to the top.
    - 1 + 1 + 1
    - 1 + 2
    - 2 + 1 */

class ClimbingStairs {
    private static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(climb(sc.nextInt()));
        sc.close();
    }

    private static int climb(int n) {
        if (n <= 3) // Observation: if stairs <= 3 number of ways to climb them is same as the number of stairs
            return n;

        dp = new int[n];
        dp[0] = 1; // for 1 stair
        dp[1] = 2; // for 2 stairs

        for (int i = 2; i < n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }
}