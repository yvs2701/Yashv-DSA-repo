import java.util.Scanner;
import java.util.Stack;

/* Given a string input_str of length n, choose any character that occurs at least twice
and delete any one occurrence. Repeat this until all remaining characters are distinct.
Return the lexicographically maximum string that can be formed this way.

Example
    input_str = "aabcb"
    The length of the string, n = 5. Some of the strings that can be formed are:
    "acb" - delete the first occurrences of 'a' and 'b'
    "abc" - delete the first occurrence of 'a' and the second occurrence of 'b'
    It can be proven that the lexicographically maximum string that can be obtained by
    removing characters is "acb".

Sample Input: abacaba
Sample Output: cba

Constraints:
input_str contains only lowercase English letters
1 <= n <= 10^5 */

class LINKEDIN_ModifyString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input_str = sc.next();
        sc.close();

        int n = input_str.length(),
                freq[] = new int[26]; // stores frequency for 26 english alphabets
        boolean included[] = new boolean[26]; // stores if the character is included in the result string

        for (int i = 0; i < n; i++)
            freq[input_str.charAt(i) - 'a']++;

        Stack<Character> result = new Stack<>();

        char c;
        for (int i = 0; i < n; i++) {
            c = input_str.charAt(i);

            freq[c - 'a']--; // decrease frequency of this character

            if (!included[c - 'a']) {
                while (!result.isEmpty() && c > result.peek() && freq[result.peek() - 'a'] > 0) {
                    included[result.peek() - 'a'] = false; // removing this from the stack
                    result.pop();
                }
                // either result.peek() > c OR freq[result.peek() - 'a'] == 0
                result.push(c);
                included[c - 'a'] = true;
            }
        }

        String res = "";
        while (!result.isEmpty()) {
            res = result.pop() + res;
        }
        System.out.println(res);
    }
}
