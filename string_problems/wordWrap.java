import java.util.Scanner;

public class wordWrap {
/* Given an array nums[] of size n, where nums[i] denotes the number of characters in one word. 
Let K be the limit on the number of characters that can be put in one line (line width). 
Put line breaks in the given sequence such that the lines are printed neatly.

Assume that the length of each word is smaller than the line width. When line breaks are inserted there is a possibility that 
extra spaces are present in each line. The extra spaces include spaces put at the end of every line except the last one. 

You have to MINIMIZE the following total cost where TOTAL COST = Sum of cost of all lines & COST OF ONE LINE = (Number of extra spaces in the line)^2.
Expected Time Complexity: O(n^2)
Expected Space Complexity: O(n) */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n; // size of array
        n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        int k = sc.nextInt(); // character limit for each line
        sc.close();
        
        int totalCost = word_wrap(nums, k);
        System.out.println(totalCost);
    }

    // static int[] costs;

    private static int word_wrap(int[] nums, int k) {
        // int l = nums.length;
        // costs = new int[l];
        // for (int i = 0; i < l; i++)
        //     costs[i] = 0;
        if (nums.length == 1)
            return 0; // since in the question its given that nums[i] < k

        return _word_wrap(nums, 1, k - nums[0] - 1, k);
    }

    // TIME COMPLEXITY = O(2^n), SPACE COMPLEXITY = O(1)
    private static int _word_wrap(int[] nums, int index, int spaces_left, int line_width) {
        if (index == nums.length - 1) {
            if (nums[index] > spaces_left) {
                spaces_left++; // since we decremented the number of spaces at lat step of recursion
                return spaces_left * spaces_left;
            }
            return 0;
        }
        // else
        if (nums[index] > spaces_left) {// just return the cost with the word not included in this line
            spaces_left++;
            return spaces_left * spaces_left + _word_wrap(nums, index + 1, line_width - nums[index] - 1, line_width);
            // -1 since every word will have a space after it except the last one whose spaces will be counted in the cost
        }

        
        int cost_incl, cost_excl; // stores total cost for = this word is included in this line and not

        // if we do not include this word in this line
        cost_excl = (spaces_left + 1) * (spaces_left + 1) + _word_wrap(nums, index + 1, line_width - nums[index] - 1, line_width);
        
        // if we include this word in this line
        cost_incl = _word_wrap(nums, index + 1, spaces_left - nums[index] - 1, line_width);

        return Math.min(cost_incl, cost_excl);
    }
}