/**
 * <h3><a href="https://leetcode.com/problems/shortest-palindrome/description/">
 * LeetCode 214: Shortest Palindrome
 * </a></h3>
 * <p>
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 * Return the shortest palindrome you can find by performing this transformation.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: s = "aacecaaa" <br>
 * Output: "aaacecaaa"
 * </p>
 * <p>
 * Example 2: <br>
 * Input: s = "abcd" <br>
 * Output: "dcbabcd"
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li> 0 &lt;= s.length &lt;= 5 * 10^4 </li>
 * <li> s consists of lowercase English letters only. </li>
 * </ul>
 * </p>
 */
public class ShortestPalindrome {
    static String findShortestPalindrome(String s) {
        final String rev = new StringBuilder(s).reverse().toString();
        final String str = s + "_" + rev; // append string and its reverse with a joining character in between

        // len2 = 2 * len1 + 1
        final int len1 = s.length(), len2 = str.length();
        final int[] lps = new int[len2]; // to find longest palindromic substring
        int idx = 1, lps_ptr = 0;

        while (idx < len2) {
            if (str.charAt(idx) == str.charAt(lps_ptr)) {
                lps[idx] = lps_ptr + 1;
                idx++;
                lps_ptr++;
            } else if (lps_ptr > 0) {
                lps_ptr = lps[lps_ptr - 1];
            } else {
                lps[idx] = 0;
                idx++;
                // lps_ptr = 0;
            }
        }

        // final string will be the extra characters at the start
        // (other than longest palindrome from the start) prefixed to the given string
        String toPrefix = rev.substring(0, len1 - lps[len2 - 1]);
        // System.out.println("[DEBUG] Characters to append: " + toPrefix);
        return toPrefix + s;
    }

    /*
    // ROLLING HASH SOLUTION
    static String shortestPalindrome(String s) {
        int overflow_mod = (int)10e9 + 7; // to prevent overflow
        // Map all the English letters to numbers, 'a' starting at 1
        // the base of that number system (letter mapping) is 26
        // choose a prime number bigger than this to avoid hash collisions
        int base = 29;

        // power will be used to calculate the hash of the reverse substring
        int power = 1;

        // hash for front and end (or front of the reversed string) parts of the string
        // Palindrome => front = end
        int prefix_hash = 0, suffix_hash = 0, last_char = 0;

        int len = s.length();
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i) - 'a' + 1;
            prefix_hash = (prefix_hash * base) % overflow_mod;
            prefix_hash = (prefix_hash + c) % overflow_mod;
            suffix_hash = (suffix_hash + c * power) % overflow_mod;
            power = (power * base) % overflow_mod;

            if (prefix_hash == suffix_hash) {
                last_char = i;
            }
        }

        String rev = "";
        if (last_char < len - 1) {
            for (int i = last_char + 1; i < len; i++) {
                rev = s.charAt(i) + rev;
            }
        }

        return rev + s;
    } */

    public static void main(String[] args) {
        String[] input_strings = {"aacecaaa", "abcd", "ababbbabbaba"};
        for (String s : input_strings) {
            System.out.println("Input: " + s);
            System.out.println("Output: " + findShortestPalindrome(s) + "\n");
        }
    }
}
