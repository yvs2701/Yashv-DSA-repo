package string_problems;

import java.util.Scanner;

public class kmpAlgo {
    /* Given a string of characters, find the length of the longest proper prefix which is also a proper suffix.
    (Then search for the pattern according to KMP algorithm)
    Input: pat = "abab"
        Length: 2. Explanation: "ab" is the longest proper prefix and suffix.
    Input: pat = "aaaa"
        Length: 3. Explanation: "aaa" is the longest proper prefix and suffix.
    Searching for pattern:
    txt =  "AABAACAADAABAABA", pat =  "AABA"
        Output: Pattern found at index 0 Pattern found at index 9 Pattern found at index 12
    Expected Time Complexity: O(|txt|)
    Expected Auxiliary Space: O(|pat|) */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt, pattern;
        // enter the text
        txt = sc.nextLine();
        // enter the pattern
        pattern = sc.nextLine();
        sc.close();

        int[] prefix = lps(pattern);
        
        // kmp searching
        int i = 0, j = 0, l = txt.length();
        while (i < l) {
            if (pattern.charAt(j) == txt.charAt(i)) {
                i++; j++;
            }
            else if (j != 0)
                j = prefix[j - 1];
            else
                i++;

            if (j == pattern.length()) {
                // 'i' was already incremented once so index of matching pattern was the following (& not i - |pattern| - 1)
                System.out.println("Pattern found at index " + (i - j));
                // we have already matched the pattern once, if there were some repititions then we won't match the strings again
                // instead j will move back only those many steps that are required (the pattern wasn't repeating)
                j = prefix[j-1];
            }
        }
    }

    // preprocess the pattern
    private static int[] lps(String pattern) {
        int l = pattern.length();

        // array containing length of 'proper prefix which is also a suffix' found
        int[] prefix = new int[l];
        prefix[0] = 0; // by definition

        // for the rest of the array
        int i = 1,j = 0;
        while (i < l) {
            if (pattern.charAt(j) == pattern.charAt(i))
                prefix[i++] = j++ + 1;
            else if (j!= 0)
                j = prefix[j-1];
            else
                prefix[i++] = 0;
        }
        return prefix;
    }
    /* just a side note 'prefix' function could've returned void... we could accept an array as an argument 
    and we know changes in this array will be reflected in the original array too, since array in java is an object an 
    although java passes everything by value (by making copies) but here copy of the memory reference is passed (and any changes made
    reflect on the same memory location) */
}