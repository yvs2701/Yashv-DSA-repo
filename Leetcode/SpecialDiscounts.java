/* Leetcode Quest Monotonous Stack Q1: "Final Prices With a Special Discount in a Shop"
You are given an integer array prices where prices[i] is the price of the ith item in a shop.
There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.
Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.

Example 1:
    Input: prices = [8,4,6,2,3]
    Output: [4,2,4,2,3]
    Explanation:
    For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
    For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
    For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
    For items 3 and 4 you will not receive any discount at all.

Example 2:
    Input: prices = [1,2,3,4,5]
    Output: [1,2,3,4,5]
    Explanation: In this case, for all items, you will not receive any discount at all.

Example 3:
    Input: prices = [10,1,1,6]
    Output: [9,0,1,6]

Constraints:
    1 <= prices.length <= 500
    1 <= prices[i] <= 1000
 */

import java.util.Stack;

class SpecialDiscounts {

    public int[] finalPrices(int[] prices) {
        int[] discounted = new int[prices.length];

        Stack<Integer> increasingStack = new Stack<>(); // Use a monotonic stack
        for (int i = prices.length - 1; i >= 0; i--) {
            if (increasingStack.isEmpty()) {
                increasingStack.push(prices[i]);
                discounted[i] = prices[i];
            } else {
                while (!increasingStack.isEmpty() && increasingStack.peek() > prices[i]) {
                    increasingStack.pop();
                }
                discounted[i] = prices[i] - (!increasingStack.isEmpty() ? increasingStack.peek() : 0);
                increasingStack.push(prices[i]);
            }
        }

        return discounted;
    }

    public static void main(String[] args) {
        // Generate testcases and call the method finalPrices
        int[][] testcases = {
                { 8, 4, 6, 2, 3 },
                { 1, 2, 3, 4, 5 },
                { 10, 1, 1, 6 }
        };

        SpecialDiscounts specialDiscounts = new SpecialDiscounts();
        for (int[] test : testcases) {
            int[] result = specialDiscounts.finalPrices(test);
            System.out.print("Input: ");
            for (int price : test) {
                System.out.print(price + " ");
            }
            System.out.print("\nOutput: ");
            for (int price : result) {
                System.out.print(price + " ");
            }
            System.out.println("\n");
        }
    }
}
