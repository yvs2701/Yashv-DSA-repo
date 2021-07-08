package string_problems;

import java.util.Scanner;

public class editDistance {
    /* Given two strings s and t. Find the minimum number of operations that need to be performed on str1 to convert it to str2.
       The possible operations are:
        * Insert
        * Remove
        * Replace
    Input: s = "geek", t = "gesek"
        Output: 1. Explanation: One operation is required inserting 's' between two 'e's of str1.
    Input: s = "gfg", t = "gfg"
        Output: 0. Explanation: Both strings are same.
    Input: s = "sunday", t = "saturday"
        Output:  3. Explaination: Last three and first characters are same. We basically need to convert "un" to "atur". 
        Replace 'n' with 'r', insert t, insert a.
    Expected Time Complexity: O(|s|*|t|).
    Expected Space Complexity: O(|s|*|t|).
    Constraints: 1 ≤ Length of both strings ≤ 100 Both the strings are in lowercase. */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /* StringBuffer s, t;
        s = new StringBuffer();
        t = new StringBuffer();
        s = s.append(sc.nextLine());
        t = t.append(sc.nextLine()); */
        String s, t;
        s = sc.nextLine();
        t = sc.nextLine();
        sc.close();
        System.out.println(numberOfOperations(s, t, s.length(), t.length()));
    }
    
    // TIME COMPLEXITY = O(|s|*|t|), SPACE COMPLEXITY = O(|s|*|t|)
    private static int numberOfOperations(String str1, String str2, int m, int n) {
        // Create a table to store results of subproblems
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to insert all characters of second string
                if (i == 0)
                    dp[i][j] = j; // Min. operations = j
 
                // If second string is empty, only option is to remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i
 
                // If last characters are same, ignore last char and recur for remaining string
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
 
                // If the last character is different, consider all possibilities and find the minimum
                else
                    dp[i][j] = 1 + Math.min(dp[i][j - 1]/* Insert */, Math.min(dp[i - 1][j]/* Remove */, dp[i - 1][j - 1])/* Replace */);
            }

        return dp[m][n];
    }

    /* this method fails to provide the MINIMUM (however you may have a look)
    // TIME COMPLEXITY = O(|s| + |t|), SPACE COMPLEXITY = if |s| < |t| then O(|t| - |s|) otherwise O(1),
    // since we modify s to become equal to t.
    private static int numberOfOperations(StringBuffer s, StringBuffer t) {
        int operationCount = 0;
        int ls = s.length(), lt = t.length();

        if (ls == 0) 
            return lt; // we need to insert lt times
        else if (lt == 0)
            return ls; // we need to remove ls times

        // if (ls > lt) then remove extra elements
        int indexS = 0, indexT = 0;
        while ((ls > lt) && (indexS < ls) && (indexT < lt)) {
            if (s.charAt(indexS) != t.charAt(indexT)) {
                s.deleteCharAt(indexS);
                ls--;
                operationCount++;
            }
            else {
                indexS++;
                indexT++;
            }
        }

        // if (ls < lt) then insert extra elements
        indexS = indexT = 0;
        while ((ls < lt) && (indexS < ls) && (indexT < lt)) {
            if (s.charAt(indexS) != t.charAt(indexT)) {
                s.insert(indexS, t.charAt(indexT));
                ls++;
                operationCount++;
            }
            indexS++;
            indexT++;
        }

        // if (ls == lt) then make the two string equal by replacing wrong elements
        indexS = indexT = 0;
        while ((indexS < ls) && (indexT < lt)) {
            if (s.charAt(indexS) != t.charAt(indexT)) {
                s.setCharAt(indexS, t.charAt(indexT));
                operationCount++;
            }
            indexS++;
            indexT++;
        }

        return operationCount;
    } */
}