/* Leetcode 81: "Search in Rotated Sorted Array II"
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting
array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).

For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
You must decrease the overall operation steps as much as possible.

Example 1:
    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true
Example 2:
    Input: nums = [2,5,6,0,0,1,2], target = 3
    Output: false
*/

class BinarySearchRotatedArray {
    public static void main(String[] args) {
        int[] nums = { 2, 5, 6, 0, 0, 1, 2 };
        int target = 0;
        System.out.println(search(nums, target));
    }

    static int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[start] < nums[mid]) {
                //if left part is sorted
                if (target < nums[start] || target > nums[mid]) {
                    //target is in rotated part
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[start] > nums[mid]) {
                //right part is rotated
                if (target < nums[mid] || target > nums[end]) {
                    //target is in rotated part
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // nums[start] == nums[mid] == nums[end]
                // remove duplicates by start++
                start++;
            }
        }

        return -1;
    }
}
