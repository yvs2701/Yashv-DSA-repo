import java.util.Arrays;

/**
 * <h3><a href="https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/">
 * LeetCode 1415: The k-th Lexicographical String of All Happy Strings of Length n
 * </a></h3>
 * <p>
 * A happy string is a string that:
 * <ul>
 * <li>consists only of letters of the set ['a', 'b', 'c'].</li>
 * <li>s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).</li>
 * </ul>
 * </p>
 * <p>
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings
 * "aa", "baa" and "ababbc" are not happy strings.
 * </p>
 * <p>
 * Given two integers n and k, consider a list of all happy strings of length n sorted in
 * lexicographical order.
 * Return the kth string of this list or return an empty string if there are less than k
 * happy strings of length n.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: n = 1, k = 3 <br>
 * Output: "c" <br>
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * </p>
 * <p>
 * Example 2: <br>
 * Input: n = 1, k = 4 <br>
 * Output: "" <br>
 * Explanation: There are only 3 happy strings of length 1.
 * </p>
 * <p>
 * Example 3: <br>
 * Input: n = 3, k = 9 <br>
 * Output: "cab" <br>
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb",
 * "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab".
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= n &lt;= 10</li>
 * <li>1 &lt;= k &lt;= 100</li>
 * </ul>
 * </p>
 */
public class KthHappyString {
    static String getHappyString(final int n, final int k) {
        return dfs(n, new int[]{k - 1}, new StringBuilder(n), '\0');
    }

    private static String dfs(final int len, final int[] pos, final StringBuilder s, final char lastChar) {
        if (len == 0) {
            if (pos[0] == 0) {
                return s.toString();
            } else {
                pos[0]--;
                return "";
            }
        }
        for (char c = 'a'; c <= 'c'; c++) {
            if (c == lastChar) {
                continue;
            }
            s.append(String.valueOf(c));
            String res = dfs(len - 1, pos, s, c); // recurse
            if (!"".equals(res)) {
                return res;
            }
            s.deleteCharAt(s.length() - 1); // backtrack
        }
        return "";
    }

    public static void main(String[] args) {
        int[][] testcases = {
                {1, 3},
                {1, 4},
                {3, 9}
        };

        for (int[] testcase : testcases) {
            System.out.println("Input: " + Arrays.toString(testcase));
            System.out.println("Output: " + getHappyString(testcase[0], testcase[1]));
            System.out.println();
        }
    }
}
