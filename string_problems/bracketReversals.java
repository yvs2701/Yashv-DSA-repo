package string_problems;

import java.util.Scanner;
// import java.util.Stack;

class bracketReversals {
    /* Given a string S consisting of only opening and closing curly brackets '{' and '}', find out the minimum number of 
    reversals required to convert the string into a balanced expression.
    A reversal means changing '{' to '}' or vice-versa.
    Input:
    S = "}{{}}{{{"
    Output: 3. Explanation: One way to balance is: "{{{}}{}}". There is no balanced sequence that can be formed in lesser reversals.
    Input:
    S = "{{}{{{}{{}}{{"
    Output: -1. Explanation: There's no way we can balance this sequence of braces.
    Expected Time Complexity: O(|S|)
    Expected Auxiliary Space: O(1) */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // remember input shall contain only curly braces !
        sc.close();
        System.out.println(countReversals(input));
    }

    // Time complexity = O(|str|) and Space complexity = O(1), i.e. constant space
    private static int countReversals(String str) {
        int open_count = 0, close_count = 0, l = str.length();

        if ((l & 1) != 0) // the length is odd
            return -1;    // then we simply cannot balance the expression

        for (int i = 0; i < l; i++) {
            char ch = str.charAt(i);
            if (ch == '{')
                open_count++;
            else {
                if (open_count == 0)
                    close_count++;
                else
                    open_count--;
            }
        }

        // return ceil(m/2) + ceil(n/2)
        return (int) (Math.ceil((double)open_count / 2) + Math.ceil((double)close_count / 2));
    }
    // below approach is not optimised. It uses O(|str|) extra space & Time Complexity is O(|str|)
    /* private static int countReversals(String str) {
        int open_count = 0, l = str.length();

        if ((l & 1) != 0) // the length is odd
            return -1;    // then we simply cannot balance the expression

        Stack<Character> braces = new Stack<Character>();
        for (int i = 0; i < l; i++) {
            char ch = str.charAt(i);
            if (ch =='}' && !braces.empty()) {
                if (braces.peek() == '{') // we found matching bracket
                    braces.pop();
                else
                    braces.push(ch); // unbalanced braces need to be pushed
            }
            else
                braces.push(ch);
        }

        l = braces.size();
        while(!braces.empty() && braces.peek() == '{') {
            braces.pop();
            open_count++; // counting open brackets
        }

        // return ceil(m/2) + ceil(n/2) which is actually equal to (m+n)/2 + n%2 when m+n is even
        // here m = |closing braces|, n = |opening braces|
        return (l/2 + open_count%2);
    } */
}