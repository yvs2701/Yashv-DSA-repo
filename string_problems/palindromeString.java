package string_problems;

import java.util.Scanner;

/* Given a string S, check if it is palindrome or not.
Input: S = "abba"
Output: 1. Explanation: S is a palindrome
Input: S = "abc"
Output: 0. Explanation: S is not a palindrome
Expected Time Complexity: O(Length of S) 
Expected Auxiliary Space: O(1) */

public class palindromeString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int l = str.length();
        boolean flag = true;
        for (int i = 0; i < l / 2; i++)
            if (str.charAt(i) != str.charAt(l - i - 1))
                flag = false;

        if (flag)
            System.out.println("The string is plaindrome");
        else
            System.out.println("Not a palindrome");
        sc.close();
    }
}
