/* Leetcode 228: "Summary Ranges"
You are given a sorted unique integer array nums. A range [a,b] is the set of all integers from a to b (inclusive).
Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:
    "a->b" if a != b
    "a" if a == b

Example 1:
    Input: nums = [0,1,2,4,5,7]
    Output: ["0->2","4->5","7"]
    Explanation: The ranges are:
    [0,2] --> "0->2"
    [4,5] --> "4->5"
    [7,7] --> "7"

Example 2:
    Input: nums = [0,2,3,4,6,8,9]
    Output: ["0","2->4","6","8->9"]
    Explanation: The ranges are:
    [0,0] --> "0"
    [2,4] --> "2->4"
    [6,6] --> "6"
    [8,9] --> "8->9"

Constraints:
- 0 <= nums.length <= 20
- -2^31 <= nums[i] <= 2^31 - 1
- nums is sorted in ascending order.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ArrayRanges {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        List<String> summary = summaryRanges(nums);

        System.out.println(summary);

        System.out.println();
    }

    private static List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];

            // Keep iterating until the next element is one more than the current element
            while (i + 1 < nums.length && (long) nums[i + 1] - nums[i] <= 1) {
                i++;
            }

            if (start != nums[i]) {
                summary.add(start + "->" + nums[i]);
            } else {
                summary.add(String.valueOf(start));
            }
        }

        return summary;
    }

    /* // Previous solution:
    private static List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        if (nums.length == 0)
            return summary;
        else if (nums.length == 1) {
            summary.add(String.valueOf(nums[0]));
            return summary;
        }
        int start = 0, i = 1;
    
        for (i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
                start = i;
            }
            else if ((long)nums[i] - nums[i - 1] > 1) {
                String range = String.valueOf(nums[start]);
                if ((i - 1) - start > 0)
                    range = range + "->" + nums[i - 1];
                summary.add(range);
    
                start = i;
            }
        }
    
        --i;
        if (i - start > 0) {
            String range = nums[start] + "->" + nums[i];
            summary.add(range);
        }
        else {
            String range = String.valueOf(nums[i]);
            summary.add(range);
        }
        
        return summary;
    } */
}
