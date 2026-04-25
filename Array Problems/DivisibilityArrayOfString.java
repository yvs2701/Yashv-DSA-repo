/**
 * <h3>Divisibility Array of String</h3>
 * <p>
 * You are given a 0-indexed string word of length n consisting of digits, and a positive integer m.
 * </p>
 * <p>
 * The divisibility array div of word is an integer array of length n such that:
 * </p>
 * <ul>
 * <li>div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or</li>
 * <li>div[i] = 0 otherwise.</li>
 * </ul>
 * <p>
 * Return the divisibility array of word.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: word = "998244353", m = 3 <br>
 * Output: [1,1,0,0,0,1,1,0,0] <br>
 * Explanation: There are only 4 prefixes that are divisible by 3: "9", "99", "998244", and "9982443".
 * </p>
 * <p>
 * Example 2: <br>
 * Input: word = "1010", m = 10 <br>
 * Output: [0,1,0,1] <br>
 * Explanation: There are only 2 prefixes that are divisible by 10: "10", and "1010".
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= n &lt;= 10^5</li>
 * <li>word.length == n</li>
 * <li>word consists of digits from 0 to 9</li>
 * </ul>
 * </p>
 */
class DivisibilityArrayOfString {

    /**
     * This function directly simulates the Basic/Long Division process.
     * @param word a string of digits representing the number to be divided.
     * @param m an integer divisor to divide the number.
     * @return integer array, "div", where div[i] = 1 if remainder was 0 at the step, 0 otherwise.
     */
    static int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] div = new int[n];
        long remainder = 0;

        for (int i = 0; i < n; i++) {
            int digit = word.charAt(i) - '0';
            remainder = remainder * 10 + digit;
            remainder = remainder % m;
            if (remainder == 0)
                div[i] = 1;
        }

        return div;
    }

    public static void main(String[] args) {
        String[][] testcases = {
                { "998244353", "3" },
                { "1010", "10" },
                { "100000000010000000003019999999961000000000919999999907999999993829999999901000000000999999999110000000001000000000100000000010000000007199999999210000000001000000000309999999975999999995100000000010000000001000000000100000000082299999998826999999992100079589999", "1000000000" }
        };
        for (String[] test : testcases) {
            String word = test[0];
            int m = Integer.parseInt(test[1]);
            int[] result = divisibilityArray(word, m);
            System.out.printf("Input:\n\tword: %s\n\tm: %d\n", word, m);
            System.out.println("Output:\n\tdiv: " + java.util.Arrays.toString(result));
        }
    }
}
