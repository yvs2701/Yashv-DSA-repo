/**
 * <h3><a href="https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/">
 * LeetCode 1545: Find Kth Bit in Nth Binary String
 * </a></h3>
 * <p>
 * Given two positive integers n and k, the binary string Sn is formed as follows:
 * </p>
 * <p>
 * S1 = "0" <br>
 * Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i &gt; 1
 * </p>
 * <p>
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 * </p>
 * <p>
 * For example, the first four strings in the above sequence are:
 * <br>
 * S1 = "0" <br>
 * S2 = "011" <br>
 * S3 = "0111001" <br>
 * S4 = "011100110110001"
 * </p>
 * <p>
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 * </p>
 * <p>
 * <b>Example 1:</b> <br>
 * Input: n = 3, k = 1 <br>
 * Output: "0" <br>
 * Explanation: S3 is "0111001". The 1st bit is "0".
 * </p>
 * <p>
 * <b>Example 2:</b> <br>
 * Input: n = 4, k = 11 <br>
 * Output: "1" <br>
 * Explanation: S4 is "011100110110001". The 11th bit is "1".
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= n &lt;= 20</li>
 * <li>1 &lt;= k &lt;= 2^n - 1</li>
 * </ul>
 * </p>
 */
public class FindKthBitInNthBinaryString {
    static char findKthBit(int n, int k) {
        // 2^n = `Math.pow(2, n)` = `1 << n`
        return _findKthBit(n, k, 1 << n);
    }

    private static char _findKthBit(int n, int k, int bit_length) {
        if (n == 1) {
            return '0';
        }
        // `bit_length / 2` can be written as `bit_length >> 1`

        /* If k is exactly in the middle, we know the value is 1 based on the
        string construction rules (insert '1' in the middle as per the rule),
        so we return 1. */
        if (k == (bit_length >> 1)) {
            return '1';
        }

        /* If k is in the first half, it lies in Sn−1. We can recursively call
        our function with n-1 and the same k. */
        if (k < (bit_length >> 1)) {
            return _findKthBit(n - 1, k, (bit_length >> 1));
        }

        /* The latter half of Sn is actually Sn−1, but flipped and reversed.
        To account for the reversal, we need to find the kth bit from the end.
        We can do so by recursively calling this function on Sn−1 but instead
        of k, we use the length of Sn minus k. The answer we get will be the
        kth bit but flipped. We just need to flip it back before returning it
        as our final answer. */
        // else if (k > (bit_length >> 1))
        return _findKthBit(n - 1, bit_length - k, (bit_length >> 1)) == '0' ? '1' : '0';
    }

    public static void main(String[] args) {
        int[][] testcases = {
                {3, 1}, // Output: "0"
                {4, 11}, // Output: "1"
        };
        for (int[] testcase : testcases) {
            System.out.println("Input: n = " + testcase[0] + ", k = " + testcase[1]);
            System.out.println("Output: " + findKthBit(testcase[0], testcase[1]));
        }
    }
}
