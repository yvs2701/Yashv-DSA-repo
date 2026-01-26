/* Leetcode Quest Monotonous Stack Q2: "Daily Temperatures"
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:
    Input: temperatures = [73,74,75,71,69,72,76,73]
    Output: [1,1,4,2,1,1,0,0]

Example 2:
    Input: temperatures = [30,40,50,60]
    Output: [1,1,1,0]

Example 3:
    Input: temperatures = [30,60,90]
    Output: [1,1,0]

Constraints:
    1 <= temperatures.length <= 10^5
    30 <= temperatures[i] <= 100
*/

import java.util.Stack;

class DailyTemperatures {
    int[] dailyTemperatures(int[] temperatures) {
        // stores index of temperatures in strictly decreasing or equal order!
        final int L = temperatures.length;
        final Stack<Integer> desc = new Stack<>(); // use an array with a variable to top index for better performance
        final int[] answer = new int[L];
        int i = 0;

        while (i < L) {
            if (desc.isEmpty()) {
                desc.push(i);
                System.out.print(" push: " + temperatures[i]); // debug logs
                i++;
            }
            System.out.println(); // debug logs

            while (i < L && temperatures[i] <= temperatures[desc.peek()]) {
                desc.push(i);
                System.out.print(" push: " + temperatures[i]); // debug logs
                i++;
            }
            System.out.println(); // debug logs
            if (i == L) {
                break;
            }

            // now the index `temperature[i]` > `temperature[desc.peek()]`
            while (!desc.isEmpty() &&
                    temperatures[i] > temperatures[desc.peek()]) {
                int prev = desc.pop();
                System.out.print(" pop: " + temperatures[prev]); // debug logs
                answer[prev] = i - prev;
            }
            System.out.println(); // debug logs
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                { 73, 74, 75, 71, 69, 72, 76, 73 },
                { 30, 40, 50, 60 },
                { 30, 60, 90 }
        };

        DailyTemperatures sol = new DailyTemperatures();

        for (int[] testCase : testCases) {
            int[] result = sol.dailyTemperatures(testCase);

            System.out.print("Input: ");
            for (int temp : testCase) {
                System.out.print(temp + " ");
            }
            System.out.println();

            System.out.print("Output: ");
            for (int r : result) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }
}