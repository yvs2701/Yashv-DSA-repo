package string_problems;

import java.util.Scanner;

/* Given a string s1 and a string s2, write a snippet to say whether s2 is a rotation of s1.
Input:
s1 = ABCD
s2 = CDAB
Output: true
Input:
s1 = ABCD
s2 = BCDA
Output: true
Input:
s1 = ABCD
s2 = ACBD
Output: false */
public class IsRotation {
    private static boolean rotationCheck(String s1, String s2) // checks is s2 is a rotation of s1
    {
        if (s1.length() != s2.length()) // if the lengths aren't equal then strings can't be a rotatiom of each other
            return false;
        
        s1 = s1 + s1; // concatenated s1 to itslef
        // algorithm: if s2 is a substring of concatenation of s1, then s2 is a rotation of s1.
        int l1 = s1.length(), l2 = s2.length();
        for (int i = 0; i <= l1 - l2; i++)
        {
            if (s1.substring(i, i + l2).equals(s2))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(rotationCheck(str1, str2));
        sc.close();
    }
}
