import java.util.Scanner;

/* The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a contiguous section all of the same character. Then for each group, say the number of characters, then say the character. To convert the saying into a digit string, replace the counts with a number and concatenate every saying.

For example, the saying and conversion for digit string "3322251": 23321511 (two 3's, three 2's, one 5, one 1)
Given a positive integer n, return the nth term of the count-and-say sequence.
Input: n = 1
Output: "1". Explanation: This is the base case.
Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
CONSTRAINTS: 1 <= n <= 30 */

public class CountAndSay_leetcode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        String solution = "1"; // base case for n = 1
        if (n == 1)
            System.out.println("1");
        else {
            for (int i = 0; i < n - 1; i++) {
                solution = countandsay(solution);
            }
            System.out.println(solution);
        }
    }

    private static String countandsay(String str) { // To generate the nth term, just count and say the n-1th term
        char prevChar = str.charAt(0);
        int i, count = 1, l = str.length();
        String solution = "";

        for (i = 1; i < l; i++) {
            if (str.charAt(i) != prevChar) {
                solution += String.valueOf(count) + String.valueOf(str.charAt(i - 1)); // store the solution
                count = 1;                // re-inititialise count
                prevChar = str.charAt(i); // update prevChar
            } else         // i.e. this character = prevChar
                count++;   // then simply increment count
        }
        solution += String.valueOf(count) + String.valueOf(str.charAt(l - 1)); // for the last continuous sequence (loop terminated by i = l)

        return solution;
    }
}
