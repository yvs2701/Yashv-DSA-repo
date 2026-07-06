/**
 * <h3><a href="https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/">
 * LeetCode 1358: Number of Substrings Containing All Three Characters
 * </a></h3>
 * <p>
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 * </p>
 * <p>
 * Example 1: <br/>
 * Input: s = "abcabc" <br/>
 * Output: 10 <br/>
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc",
 * "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 * <br/>
 * Example 2: <br/>
 * Input: s = "aaacb" <br/>
 * Output: 3 <br/>
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are
 * "aaacb", "aacb" and "acb".
 * <br/>
 * Example 3: <br/>
 * Input: s = "abc"  <br/>
 * Output: 1
 * </p>
 * <p>
 * Constraints: <br/>
 * 3 <= s.length <= 5 x 10^4  <br/>
 * s only consists of a, b or c characters.
 * </p>
 */
public class SubstringsContainingAll3Characters {
    /**
     * Use Sliding Window. Expand window to right till we have at least one
     * occurrence of each of a, b and c. <br/> For a window (starting at `left`
     * and ending at `right`) which has at least one a, b and c,
     * all substrings that start at position left and end at or after
     * position right will also be valid. <br/>
     * There are `s.length() - right` such substrings. <br/>
     * Shrink the window until one character is not present. Then expand again.
     */
    static int numberOfSubstrings(String s) {
        int left = 0, right = 0, len = s.length(), count = 0;
        int[] occurrence = {0, 0, 0};
        while (right < len) {
            occurrence[s.charAt(right) - 'a']++;
            while (occurrence[0] > 0 && occurrence[1] > 0 && occurrence[2] > 0) {
                count += len - right;
                occurrence[s.charAt(left) - 'a']--;
                left++;
            }
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        String[] testcases = {"abcabc", "aaacb", "abc"};
        for (String s : testcases) {
            System.out.println("Input: " + s + "\nOutput: " + numberOfSubstrings(s) + "\n");
        }
    }
}
