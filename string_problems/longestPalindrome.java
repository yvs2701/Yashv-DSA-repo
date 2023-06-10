import java.util.Scanner;

/* Given a string S, find the longest palindromic substring in S. Substring of string S: S[ i . . . . j ] where 0 ≤ i ≤ j < len(S).
Palindrome string: A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S.
Incase of conflict, return the substring which occurs first ( with the least starting index).
Input:
S = "aaaabbaa"
Output: aabbaa. Explanation: The longest Palindromic substring is "aabbaa". 
Input:
S = "abc"
Output: a. Explanation: "a", "b" and "c" are the longest palindromes with same length (= 1).
Expected Time Complexity: O(|S|^2).
Expected Auxiliary Space: O(1).
Constraints:
1 ≤ |S| ≤ 10^3 */

public class longestPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        System.out.println(findLongestPalindrome(str));
    }

    private static String findLongestPalindrome(String str) { // finds longest palindromic string in O(n^2); n = str.length
        int start, end; // start and end store the respective indices for the substring
        int maxStart = 0, maxEnd = 0;
        int l = str.length();

        /*
         * for every iteration we will start from the current element, treating it as
         * the center we will move the pointers in both the sides till we keep getting a
         * palindrome string
         */
        for (int i = 1; i < l; i++) {
            // this loop can only calculate longest palindrome OF ODD LENGTH
            // (since we treat one element as centre) and then compare elements around it
            start = i;
            end = i;
            while (start >= 0 && end < l && str.charAt(end) == str.charAt(start)) {
                start--;
                end++;
            }
            if (start >= -1 && end <= l && (end - start > maxEnd - maxStart)) { // length of subtring end - start + 1
                maxStart = start + 1;
                maxEnd = end - 1;
            }
        }
        for (int i = 0; i < l - 1; i++) {
            // this loop calculates longest palindrome OF EVEN LENGTH
            // since we have our start = i & end = i + 1,
            // and we keep comparing the elements around
            start = i;
            end = i + 1;
            while (start >= 0 && end < l && str.charAt(end) == str.charAt(start)) {
                start--;
                end++;
            }
            if (start >= -1 && end <= l && (end - start > maxEnd - maxStart)) { // length of substring end - start + 1
                maxStart = start + 1;
                maxEnd = end - 1;
            }
        }
        return str.substring(maxStart, maxEnd + 1);
    }
}
