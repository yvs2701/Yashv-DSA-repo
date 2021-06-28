package string_problems;

import java.util.Scanner;

/* Given a string, find the length of the longest repeating subsequence such that the two subsequences don't have same string character at the same
position, i.e., any i'th character in the two subsequences shouldn't have the same index in the original string.
Input: str = "axxxy"
Output: 2. Explanation: The longest repeating subsequenece is "xx" ('x' at 1 + 2 && 'x' at 1 + 3 && 'x' at 2 + 3).
Input: str = "aabebcdd"
output: 3. Explanation: The longest repeting subsequenece is "abd".
Expected Time Complexity: O(n^2)
Expected Space Complexity: O(n^2) */

public class recurringSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        System.out.println(longestSubsequence(str));
    }

    private static int longestSubsequence(String str) {
        int l = str.length();
        int sequences[][] = new int[l + 1][l + 1];
        // stores the number of matching characters till this ith or jth index (dynamic
        // progamming)
        for (int i = 0; i < l + 1; i++)
            for (int j = 0; j < l + 1; j++)
                sequences[i][j] = 0;

        for (int i = 1; i < l + 1; i++) {
            for (int j = 1; j < l + 1; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j)
                    sequences[i][j] = 1 + sequences[i - 1][j - 1];
                /*
                 * in the else block we stored whatever streak we got last time the characters
                 * matched so now we will be adding one to that streak (or count... whatever)
                 */
                else // this is not substring or subarray so don't break the running streak
                    sequences[i][j] = Math.max(sequences[i][j - 1], sequences[i - 1][j]);
                // store whatever maximum we get from previous running streaks
            }
        }

        return sequences[l][l];
    }
}
