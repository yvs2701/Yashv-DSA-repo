/* LeetCode Quest Monotonous Stack Q3: "Largest Rectangle in Histogram"
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.
Example 1:
    Input: heights = [2,1,5,6,2,3]
    Output: 10
    Explanation: The above is a histogram where width of each bar is 1.
    The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:
    Input: heights = [2,4]
    Output: 4
Constraints:
    1 <= heights.length <= 10^5
    0 <= heights[i] <= 10^4 */

import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        /* BRUTE FORCE: */
        /* Keep adding the larger bars in the rectangle until a smaller bar is found.
         * Which means - find the first smaller bar on the left and the right of the current bar.
         * Then the area = current bar's height * (rightSmallerIdx - leftSmallerIdx - 1). */

        /* int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            while (left >= 0 && heights[left] >= heights[i]) {
                left--;
            }
            while (right < heights.length && heights[right] >= heights[i]) {
                right++;
            }
            maxArea = Math.max(maxArea, heights[i] * (right - left - 1));
        }
        return maxArea; */

        /* OPTIMIZED APPROACH: USING MONOTONOUS STACK */
        /* We will create a monotonically increasing stack. From left to right traverse the array of bar heights.
         * If the current bar's height >= height of the bar at the top of the stack then add this bar to the stack.
         * Otherwise, the current bar is the right boundary for the rectangle formed by the height of the bar which
         * is at the top of the stack. Repeat this process by popping the bars and expanding the rectangle towards
         * the left, until the current bar is shorter, calculating the area at each step to find the maximum. */
        int maxArea = 0;
        Stack<Integer> ascStack = new Stack<>();

        for (int i = 0; i <= heights.length; i++) {
            while (!ascStack.isEmpty() && ((i == heights.length) || (heights[i] < heights[ascStack.peek()]))) {
                /* if stack contains only one bar, then it means this was the smallest element till now, besides
                 * the current bar. That means we can calculate width from the start (index = 0) till the
                 * current bar (index = i). width = i - 0 = i */
                int tallest = ascStack.pop();
                int width = ascStack.isEmpty() ? i : i - ascStack.peek() - 1;
                maxArea = Math.max(maxArea, heights[tallest] * width);
            }
            ascStack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Generate testcases and call the method largestRectangleArea
        int[][] testcases = {
                {2, 1, 5, 6, 2, 3},
                {2, 4},
                {4, 3, 5, 5, 9, 2, 8, 4, 7, 2, 3, 8, 3, 5, 4, 7, 9},
                {2, 1, 2},
                {3, 6, 5, 7, 4, 8, 1, 0}
        };

        LargestRectangleInHistogram solver = new LargestRectangleInHistogram();
        for (int[] test : testcases) {
            int result = solver.largestRectangleArea(test);
            System.out.print("Input: ");
            for (int height : test) {
                System.out.print(height + " ");
            }
            System.out.println("\nOutput: " + result + "\n");
        }
    }
}
