/* Leetcode 518: "Coin Change II"
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin. The answer is guaranteed to fit into a signed 32-bit integer.
Example 1:
    Input: amount = 5, coins = [1,2,5]
    Output: 4
    Explanation: there are four ways to make up the amount:
        5=5
        5=2+2+1
        5=2+1+1+1
        5=1+1+1+1+1
Example 2:
    Input: amount = 3, coins = [2]
    Output: 0
    Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:
    Input: amount = 10, coins = [10]
    Output: 1
*/

class CoinChangeII {
    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        int amount = 5;
        System.out.println(change(amount, coins));
    }

    static int change(int amount, int[] coins) {
        /* dp[i][j]: the number of combinations to make up amount j
        by using the first i types of coins
        Possibilities:
        - not using the ith coin to make up amount j = dp[i-1][j] ways
        - using the ith coin, to make j - coins[i - 1] by using
          first i coins(including ith), which is dp[i][j-coins[i-1]]
          ("using ith coin to make j - coins[i - 1]" i and i - 1 because dp
          has one extra row and column than coin.length)*/

        int[][] dp = new int[coins.length + 1][amount + 1];
        // last row (i = coins.length) is used to accumulate the total number of ways

        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }

        return dp[coins.length][amount];
    }
}