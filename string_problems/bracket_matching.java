package string_problems;

import java.util.Scanner;
import java.util.Stack;

public class bracket_matching {
    /* Given an expression string x. Examine whether the pairs and the orders of “{“,”}”,”(“,”)”,”[“,”]” are correct in exp.
    Input:
    {([])}
        Output: true. Explanation: { ( [ ] ) }. 0 number of unbalanced bracket.
    Input: 
    ()
        Output: true. Explanation: (). Same bracket can form balanced pairs, and here only 1 type of bracket is present and in balanced way.
    Input: 
    ([]
        Output: false. Explanation: ([]. Here square bracket is balanced but the small bracket is not balanced hence, the output will be unbalanced.
    Expected Time Complexity: O(|x|)
    Expected Auixilliary Space: O(|x|)
    Constraints: 1 ≤ |x| ≤ 32000 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        sc.close();
        boolean check_brackets = checkBrackets(expression);
        if (check_brackets)
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }

    private static boolean checkBrackets(String expression) {
        Stack<Character> openBrackets = new Stack<Character>(); // push all the open brackets, and pop when the matching closing bracket is found
        int l = expression.length();
        for (int i = 0; i < l; i++) {
            char bracket = expression.charAt(i);
            if (bracket == '(' || bracket == '{' || bracket == '[')
                openBrackets.push(bracket);
            else // we encountered a closing bracket
            switch (bracket) {
                // openBrackets.peek() is the opening bracket corresponding to this closing bracket
                case ')':
                    if (openBrackets.peek() == '(')
                        openBrackets.pop();
                    else
                        return false;
                    break;
                case '}':
                    if (openBrackets.peek() == '{')
                        openBrackets.pop();
                    else
                        return false;
                    break;
                case ']':
                    if (openBrackets.peek() == '[')
                        openBrackets.pop();
                    else
                        return false;
                    break;
                default:
                    return false;
            }
        }
        if (!openBrackets.empty())
            return false;
        return true;
    }
}
