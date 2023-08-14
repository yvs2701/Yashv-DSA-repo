/* Leetcode 215: "Kth Largest Element in an Array"
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?

Example 1:
    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5
Example 2:
    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4
*/

class kthLargestQuickSelect {
    public static void main(String[] args) {
        int[] nums = { 3, 2, 4, 1, 2, 5, 8, 6, 7 };
        int k = 4;

        System.out.println(new kthLargestQuickSelect().findKthLargest(nums, k));

        System.out.println("The array got modified as:");
        for (int i : nums)
            System.out.print(i + " ");
        System.out.println();
    }

    int findKthLargest(int[] nums, int k) {
        /* based on quickselect algorithm
        just like quick sort this algorithm attempts to
        sort only the needed k largest elements in O(n) average time complexity
        and in worst case O(n^2) */
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    int quickSelect(int[] nums, int low, int high, int k) {
        /* By the end of the swappings `nums[high]` will reach its
        correct position. That will be the value at which `pivot`
        will point in the end: */
        int pivot = low; // choose the first element as the initial pivot

        /* put nums that are <= pivot to the left
        put nums that are > pivot to the right */
        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, pivot++, j);
            }
        }
        swap(nums, pivot, high); // now nums[pivot] is at its CORRECT PLACE

        int count = high - pivot + 1; // number of elements after the index `pivot`
        // nums[pivot] is the `count`th greatest element in the array

        if (count == k)
            return nums[pivot];

        // pivot is smaller than k greatest elements, search in the right subarray
        if (count > k)
            return quickSelect(nums, pivot + 1, high, k);

        // pivot is bigger than kth greatest elements, search in the left subarray
        // till now we are at `count`th greatest element
        // we need to now find `k - count` greatest element from this element
        return quickSelect(nums, low, pivot - 1, k - count);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
