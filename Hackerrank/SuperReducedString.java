import java.util.Scanner;

/* Reduce a string of lowercase characters in range ascii[‘a’..’z’]by doing a series of operations.
In each operation, select a pair of adjacent letters that match, and delete them.
Delete as many characters as possible using this method and return the resulting string.
If the final string is empty, print "Empty String"

Example:
Input -  aaabccddd
Output - abd
Explanation - aaabccddd -> abccddd -> abddd -> abd

Input -  baab
Output - Empty String
Explanation - baac -> bb -> Empty String
*/

class SuperReducedString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder str = new StringBuilder(sc.nextLine());
        sc.close();

        // travel the string, if characters at index i and i+1
        // are same then remove both of them and start checking from
        // index i - 1, as characters at index i+2 of original string
        // and index i-1 can be same too.
        for (int i = 0; i < str.length() - 1;) {
            if (i != -1 && str.charAt(i) == str.charAt(i + 1)) {
                str.deleteCharAt(i); // index i+1 now becomes index i
                str.deleteCharAt(i); // index i+2 now becomes index i
                i--; // move one index back (to compare index i-1 and i+2 of the original string)
            } else
                i++;
        }
        System.out.println(str.length() == 0 ? "Empty String" : str);
    }
}
