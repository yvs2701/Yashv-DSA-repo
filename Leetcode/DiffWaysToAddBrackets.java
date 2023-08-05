/* Leetcode 241: "Different ways to add parentheses"
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators.
You may return the answer in any order.
The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 10^4.
Example 1:
    Input: expression = "2-1-1"
    Output: [0,2]
    Explanation:
        ((2-1)-1) = 0 
        (2-(1-1)) = 2
Example 2:
    Input: expression = "2*3-4*5"
    Output: [-34,-14,-10,-10,10]
    Explanation:
        (2*(3-(4*5))) = -34 
        ((2*3)-(4*5)) = -14 
        ((2*(3-4))*5) = -10 
        (2*((3-4)*5)) = -10 
        (((2*3)-4)*5) = 10
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DiffWaysToAddBrackets {
    Map<String, List<Integer>> cache = new HashMap<>();

    public static void main(String[] args) {
        String expression = "4*5-2*3";
        System.out.println(expression + " = " + new DiffWaysToAddBrackets().diffWaysToCompute(expression));
    }

    List<Integer> diffWaysToCompute(String input) {
        if (cache.containsKey(input))
            return cache.get(input);

        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '-' || c == '+' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);

                List<Integer> _left = diffWaysToCompute(left);
                List<Integer> _right = diffWaysToCompute(right);

                for (int x : _left) {
                    for (int y : _right) {
                        if (c == '-') {
                            res.add(x - y);
                        } else if (c == '+') {
                            res.add(x + y);
                        } else if (c == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }

        if (res.size() == 0)
            res.add(Integer.valueOf(input));

        cache.put(input, res);

        return res;
    }
}
