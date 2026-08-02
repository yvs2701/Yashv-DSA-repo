import java.util.Stack;


/**
 * <h3><a href="https://leetcode.com/problems/decode-string/description/">
 * LeetCode 394: Decode String
 * </a></h3>
 * <p>
 * Given an encoded string, return its decoded string.
 * </p>
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square
 * brackets is being repeated exactly k times. Note that k is guaranteed to be a positive
 * integer.
 * </p>
 * <p>
 * You may assume that the input string is always valid; there are no extra white spaces,
 * square brackets are well-formed, etc. Furthermore, you may assume that the original data
 * does not contain any digits and that digits are only for those repeat numbers, k. For
 * example, there will not be input like 3a or 2[4].
 * </p>
 * <p>
 * The test cases are generated so that the length of the output will never exceed 10^5.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: s = "3[a]2[bc]" <br>
 * Output: "aaabcbc"
 * </p>
 * <p>
 * Example 2: <br>
 * Input: s = "3[a2[c]]" <br>
 * Output: "accaccacc"
 * </p>
 * <p>
 * Example 3: <br>
 * Input: s = "2[abc]3[cd]ef" <br>
 * Output: "abcabccdcdcdef"
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= s.length &lt;= 30</li>
 * <li>s consists of lowercase English letters, digits, and square brackets '[]'.</li>
 * <li>s is guaranteed to be a valid input.</li>
 * <li>All the integers in s are in the range [1, 300].</li>
 * </ul>
 * </p>
 */
public class DecodeString {
    /**
     * Decodes the input string using Stack.
     * Logic is similar to {@link #decodeString(String)}
     *
     * @param s the encoded string
     * @return the decoded string
     */
    static String decodeStringUsingStacks(final String s) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']') {
                stack.push(String.valueOf(s.charAt(i)));
            } else {
                StringBuilder substr = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    substr.insert(0, stack.pop());
                }
                stack.pop();

                StringBuilder k = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
                    k.insert(0, stack.pop());
                }
                int count = Integer.parseInt(k.toString());
                String repeatedStr = substr.toString().repeat(count);
                stack.push(repeatedStr);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    /**
     * <p>
     * Decodes an encoded string using a recursive approach.
     * </p>
     * <ol>
     * <li> Get the index of the first bracket in the input. </li>
     * <li> Find the number before the open bracket. Then find the matching closing bracket
     * for the same. </li>
     * <li> Recursively decode the substring WITHIN this bracket </li>
     * <li> Repeat the decoded substring. </li>
     * <li> Create the final result by merging
     * the substring before the number
     * + repeated substring WITHIN the brackets
     * + recursively decoded substring AFTER the matched closing bracket. </li>
     * </ol>
     *
     * @param s the encoded string
     * @return the decoded string
     */
    static String decodeString(final String s) {
        final int len = s.length();
        // find the number before the first opening bracket
        final int[] number_start_end = findNumberBeforeBracket(s, len);
        if (number_start_end[1] == -1) {
            return s; // there is no bracket to expand
        }
        int timesToRepeat = Integer.parseInt(
                s.substring(number_start_end[0], number_start_end[1]));

        // find the matching closing bracket
        int unmatched_brackets = 1;
        int idx = number_start_end[1] + 1; // stores index of closing brackets
        for (; idx < len && unmatched_brackets != 0; idx++) {
            char c = s.charAt(idx);
            if (c == '[') {
                unmatched_brackets++;
            } else if (c == ']') {
                unmatched_brackets--;
            }
        }
        // since the string is guaranteed to be a valid input
        // now 'idx' = index of matching end bracket + 1
        String toExpand = s.substring(number_start_end[1] + 1, idx - 1);

        toExpand = decodeString(toExpand); // recursive call
        StringBuilder expanded = new StringBuilder(toExpand);
        while (timesToRepeat-- > 1) {
            expanded.append(toExpand);
        }

        return s.substring(0, number_start_end[0]) + expanded.toString() +
                decodeString(s.substring(idx));
    }

    /**
     * if no bracket exists in the string end = -1; Otherwise end will be the index of
     * the first opening bracket
     */
    private static int[] findNumberBeforeBracket(final String s, final int len) {
        final int end = s.indexOf('[');
        int start = end - 1;
        while (start >= 0 && Character.isDigit(s.charAt(start))) {
            start--;
        }
        start++;

        return new int[]{start, end};
    }

    public static void main(String[] args) {
        String[] testCases = {"3[a]2[bc]", "3[a2[c]]", "2[abc]3[cd]ef", "10[a]1[b]"};
        for (String testCase : testCases) {
            System.out.println("Input: " + testCase);
            System.out.println("Output recursion: " + decodeString(testCase));
            System.out.println("Output stack:" + decodeString(decodeString(testCase)));
            System.out.println();
        }
    }
}
