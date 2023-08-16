/* Leetcode 516: "Longest Palindromic Subsequence"
Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Example 1:
    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb"
Example 2:
    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb"
*/

import java.util.Scanner;

class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        System.out.println(new LongestPalindromicSubsequence().longestPalindrome(s));
    }

    int longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // dp[i][j] := length of maximum subsequence
        // in the substring from i to j (incl.)
        // also j >= i, so the bottom left half of the dp is useless

        for (int i = s.length() - 1; i >= 0; i--) {
            // maximum palindromic string for each single character is 1 only
            dp[i][i] = 1;

            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // start of subsequence = end of subsequence
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}
