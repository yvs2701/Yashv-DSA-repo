/* Given an array of n positive integers and a number k. Find the minimum number of swaps required to bring all the numbers
less than or equal to k together.
Input  : arr[] = {2, 1, 5, 6, 3} and K = 3
Output : 1
Explanation: To bring elements 2, 1, 3 together, swap element '5' with '3' such that final array will be- arr[] = {2, 1, 3, 6, 5}

Input  : arr[] = {2, 7, 9, 5, 8, 7, 4} and K = 6 
Output :  2 
Expected Time Complexity: O(N)
Expected Auxiliary Space: O(1) */
#include <iostream>

/* Failed Algo:
int countSwaps(int *arr, int l, int k)
{
    int leftSwaps = 0, rightSwaps = 0; // counts number of swaps, when we group (or sort) from left side and right side respectively
    // using a technique similar to one used in threeWayPartioning(.cpp) of an array around a range

    // grouping all such elements, starting from left
    int start(0), end(1);
    while (start <= end && end < l)
    {
        if (arr[start] <= k)
            start++;
        else if (arr[end] <= k) // i.e. arr[start] > k and arr[end] <= k... swap needed to group together similar elements
        {
            // std::swap(arr[start], arr[end]);
            // we don't actually swap in order to prevent changes in array, since we still need to check it from right side
            start++;
            end++;
            leftSwaps++; // count this swapping
        }

        if (arr[end] > k)
            end++;

        if (start > end)
            std::swap(start, end);
        // if 'start' > 'end' before end traverses the whole array then that means we haven't checked for all elements so we swap the pointers
        // the loop shall end when 'end' traverse the entire array
    }

    // grouping all such elements, starting from right
    start = l - 1, end = l - 2; // I didn't know that C++ allows you to assign variables separated by commas :)
    while (start >= end && end >= 0)
    {
        if (arr[start] <= k)
            start--;
        else if (arr[end] <= k)
        {
            start--;
            end--;
            rightSwaps++;
        }

        if (arr[end] > k)
            end--;
        if (start < end)
            std::swap(start, end);
    }

    std::cout << leftSwaps << " " << rightSwaps << "\n";

    return std::min(leftSwaps, rightSwaps);
} */

int countSwaps(int *arr, int l, int k)
{
    int swaps = l,  // stores the count of minimum swaps needed
        passed = 0; // stores how many elements pass the condition of being <= k
    // using sliding windows technique
    for (int i = 0; i < l; i++)
        if (arr[i] <= k)
            passed++;
    /* now we will create sliding window of size 'passed' which is supposed to have all the elements <= k
    we will count the number elems in the window that do not pass the condition of <= k and this will be the number of elements to swap 
    finally we will return the minimum number of swaps in the most optimal window */
    int failed = 0;         // stores how many elements in the window are > k
    int start = 0, end = 0; // stores the end of window/subarray

    while (end - start + 1 <= passed) // create window of size passed
    {
        if (arr[end] > k)
            failed++;
        end++;
    }

    end--; // before the loop broke end was incremeneted

    while (end < l)
    {
        std::cout << start << " " << end << " " << failed << "\n";
        swaps = std::min(swaps, failed); // we need to swap out these failed elements in every window

        if (end + 1 != l)
        {
            start++;
            end++;

            if (arr[start - 1] > k) // start of the window was a failed element
                failed--;           // since we have incremented the start & end pointer, window has shift ahead leaving out that failed element
            if (arr[end] > k)       // checking the new element which got included when our window shifted
                failed++;
        }
        else
            end++; // else increment 'end' an break out of the loop
    }

    return swaps;
}

int main()
{
    // int arr[] = {48, 7, 12, 8, 84, 21, 4, 24, 6, 72, 108}, k = 21; /* my testcase */
    // int arr[] = {10, 12, 20, 20, 5, 19, 19, 12, 1, 20, 1}, k = 1; /* I struggled here and enhanced the algo */
    int arr[] = {4, 11, 6, 5, 11, 20, 19, 14, 14, 2, 9, 20, 11, 11, 15, 1, 7, 12, 19, 9}, k = 14; /* the enhanced algo failed here xD */
    // at last I had to get the logic, only then I was able to code it
    std::cout << countSwaps(arr, sizeof(arr) / sizeof(arr[0]), k) << std::endl;
    return 0;
}