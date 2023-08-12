/* Leetcode 5: "Longest Plaindromic String"
Given a string s, return the longest palindromic substring in s.
Example 1:
    Input: s = "babad"
    Output: "bab"
    ("aba" is also a valid answer, but "bab" is found first)
Example 2:
    Input: s = "cbbd"
    Output: "bb" */

import java.util.Scanner;

class LongestPalindromicString {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            String s = sc.nextLine();
            System.out.println(new LongestPalindromicString().longestPalindrome(s));
        }
    }

    String longestPalindrome(String s) {
        /* we can start checking from palindrome by expanding
        outwards from a given pivot
        In a string: malayalam
        Number of such pivots can be: m _ a _ l _ a _ y _ a _ l _ a _ m
        i.e. any string has 2n - 1 pivots
        */
        if (s.length() <= 1)
            return s;

        int n = s.length(), start = -1, len = 0;

        for (int i = 0; i < n - 1; ++i) {
            int[] substr1 = expandPalindrome(s, i, i), // odd length palindrome
                    substr2 = expandPalindrome(s, i, i + 1); // even length palindrome

            int[] greater = substr1[1] > substr2[1] ? substr1 : substr2; // or use substr1[1] >= substr2[1]
            if (greater[1] > len) { // or use greater[1] >= len
                start = greater[0];
                len = greater[1];
            }
        }

        return s.substring(start, start + len);
    }

    /**
    * Returns start of the palindrome string, its length as a pair.
    * <p>
    * Note: make sure to pass start and end indices such that {@code s.charAt(start) == s.charAt(end)}
    * </p>
    * @param s the complete input string in which the function would search for palindrome
    * @param start the initial starting index for searching the palindrome and expanding outwards
    * @param end the initital ending index for searching the palindrome and expanding outwards
    * @return an int[] containing: { start of the palindrome string, length of the palindrome string }
    */
    private int[] expandPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        // end and start have both moved outside the actual palindrome substring
        // the actual length would be current length - 2 = (end - start + 1) - 2
        return new int[] { start + 1, end - start - 1 };
    }
}
