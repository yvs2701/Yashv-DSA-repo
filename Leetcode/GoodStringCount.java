import java.util.Scanner;

/* Leetcode 2466: "Count Ways To Build Good Strings"
Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
- Append the character '0' zero times.
- Append the character '1' one times.
This can be performed any number of times.
A good string is a string constructed by the above process having a length between low and high (inclusive).
Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
Example:
    Input: low = 2, high = 3, zero = 1, one = 2
    Output: 5
    Explanation: Good strings are "00", "11", "000", "110", and "011" */

class GoodStringCount {
    private static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(countGoodStrings(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
        sc.close();
    }

    public static int countGoodStrings(int low, int high, int zero, int one) {
        final int MOD = (int) 1e9 + 7;
        int count = 0;

        dp = new int[high + 1];
        dp[0] = 1; // only one way to form a null string := don't append anything

        // for each step either append 'zero' 0s or 'one' 1s and recurse for the leftover length
        for (int i = 1; i <= high; ++i) {
            dp[i] = (getDP(i - zero) + getDP(i - one)) % MOD;

            if (i >= low) // if length of the string is between low and high start counting
                count = (count + getDP(i)) % MOD;
        }

        return count;
    }

    private static int getDP(int index) {
        return index < 0 ? 0 : dp[index];
    }
}
