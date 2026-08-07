import java.util.ArrayList;
import java.util.List;

/**
 * <h3><a href="https://leetcode.com/problems/restore-ip-addresses/description/">
 * LeetCode 93: Restore IP Addresses
 * </a></h3>
 * <p>
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * </p>
 * <p>
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245",
 * "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * </p>
 * <p>
 * Given a string s containing only digits, return all possible valid IP addresses that can be
 * formed by inserting dots into s. You are not allowed to reorder or remove any digits in s.
 * You may return the valid IP addresses in any order.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: s = "25525511135" <br>
 * Output: ["255.255.11.135","255.255.111.35"]
 * </p>
 * <p>
 * Example 2: <br>
 * Input: s = "0000" <br>
 * Output: ["0.0.0.0"]
 * </p>
 * <p>
 * Example 3: <br>
 * Input: s = "101023" <br>
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li> 1 &lt;= s.length &lt;= 20 </li>
 * <li> s consists of digits only. </li>
 * </ul>
 * </p>
 */
public class RestoreIpAddresses {
    static List<String> restoreIpAddresses(String s) {
        List<String> restoredIpAddresses = new ArrayList<>();
        // largest IP address = "255.255.255.255" is of length 12
        if (s.length() > 12) {
            // this string cannot be resolved to any proper IP address
            return restoredIpAddresses;
        }
        // recurse and backtrack using depth first search
        dfs(restoredIpAddresses, new StringBuilder(s), 1, 3);

        return restoredIpAddresses;
    }

    private static void dfs(List<String> result, StringBuilder s, int start, int dots) {
        if (dots == 0) {
            if (isValidIpAddress(s)) {
                result.add(s.toString());
            }
        }
        int len = s.length();
        for (int i = start; i < Math.min(len, start + 3); i++) {
            s.insert(i, '.');
            dfs(result, s, i + 2, dots - 1); // recurse
            s.deleteCharAt(i); // backtrack
        }
    }

    public static void main(String[] args) {
        String[] testcases = {"25525511135", "101023", "28576"};
        for (String testcase : testcases) {
            System.out.println("Input: " + testcase);
            System.out.println("Output: " + restoreIpAddresses(testcase));
        }
    }

    /**
     * Checks if the given string is a valid IP Address
     */
    private static boolean isValidIpAddress(StringBuilder s) {
        int dot1 = s.indexOf(".", 0);
        String s1 = s.substring(0, dot1);
        if (!isInIpRange(s1)) {
            return false;
        }
        int dot2 = s.indexOf(".", dot1 + 1);
        String s2 = s.substring(dot1 + 1, dot2);
        if (!isInIpRange(s2)) {
            return false;
        }
        int dot3 = s.indexOf(".", dot2 + 1);
        String s3 = s.substring(dot2 + 1, dot3);
        if (!isInIpRange(s3)) {
            return false;
        }
        String s4 = s.substring(dot3 + 1);
        return isInIpRange(s4);
    }

    /**
     * Checks if the given string is in the range [0, 255]
     */
    private static boolean isInIpRange(String s) {
        int len = s.length();
        if (len == 1 && Character.isDigit(s.charAt(0))) {
            return true;
        }
        if (len == 2 && Character.isDigit(s.charAt(0)) && s.charAt(0) != '0' &&
                Character.isDigit(s.charAt(1))) {
            return true;
        }
        if (len == 3) {
            char c1 = s.charAt(0), c2 = s.charAt(1), c3 = s.charAt(2);
            if (c1 == '0') {
                return false;
            } else if (c1 == '1' && Character.isDigit(c2) && Character.isDigit(c3)) {
                return true;
            } else if (c1 == '2' && ((c2 >= '0' && c2 <= '4' && Character.isDigit(c3)) ||
                    (c2 == '5' && c3 >= '0' && c3 <= '5'))) {
                return true;
            }
        }
        return false;
    }
}
