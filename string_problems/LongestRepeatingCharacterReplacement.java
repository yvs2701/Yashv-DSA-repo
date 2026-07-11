/**
 * <h3><a href="https://leetcode.com/problems/longest-repeating-character-replacement/description/">
 * LeetCode 424: Longest Repeating Character Replacement</a></h3>
 * <p>
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * <br/>
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: s = "ABAB", k = 2 <br>
 * Output: 4 <br>
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * </p>
 * <p>
 * Example 2: <br>
 * Input: s = "AABABBA", k = 1 <br>
 * Output: 4 <br>
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA". The substring "BBBB" has the longest repeating letters, which is 4.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= s.length &lt;= 10^5</li>
 * <li>s consists of only uppercase English letters.</li>
 * <li>0 &lt;= k &lt;= s.length</li>
 * </ul>
 * </p>
 */
public class LongestRepeatingCharacterReplacement {
    /* OPTIMAL SOLUTION: */
    static int characterReplacement(String s, int k) {
        final int len = s.length(), count[] = new int[26];
        int left = 0, max_count = -1, max_len = 0;

        for (int right = 0; right < len; right++) {
            max_count = Math.max(max_count, ++count[s.charAt(right) - 'A']);
            while (right - left + 1 - max_count > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            max_len = Math.max(max_len, right - left + 1);
        }
        return max_len;
    }

    public static void main(String[] args) {
        String[] input_strings = {"ABAB", "AABABBA", "ABABBBCCBCCCBCCCCCC"};
        int[] input_k = {2, 1, 7};
        for (int i = 0; i < input_strings.length; i++) {
            System.out.println("Input: " + input_strings[i] + ", k: " + input_k[i]);
            System.out.println("Output: " + characterReplacement(input_strings[i], input_k[i]) + "\n");
        }

    }

    /* MY SOLUTION: */
    /*
        public int characterReplacement(String s, int k) {
            char[] arr = s.toCharArray();
            final int len = arr.length, count[] = new int[26];
            int left = 0, right = 0, max_len = 0;

            while (right < len) {
                int max_count = getMaxCount(count);
                int window_size = right - left;
                max_len = Math.max(max_len, right - left);

                // grow the window until the number of characters replaced < k
                // number of characters replaced = window_size - max_count
                if ((count[arr[right] - 'A'] == max_count) || (window_size - max_count < k)) {
                    count[arr[right] - 'A']++;
                    right++;
                } else { // shrink the window
                    count[arr[left] - 'A']--;
                    left++;
                }
            }
            max_len = Math.max(max_len, right - left);
            return max_len;
        }

        // Returns the index with the maximum value.
        private int getMaxCount(int[] count) {
            int max = -1;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > max) {
                    max = count[i];
                }
            }
            return max;
        }
    */
}
