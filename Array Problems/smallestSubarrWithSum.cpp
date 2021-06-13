/* Given an array of integers (A[])  and a number x, find the smallest subarray with sum greater than the given value.
    ***Note: The answer always exists. It is guaranteed that x doesn't exceed the summation of a[i] (from 1 to N)***
Input:
    A[] = {1, 4, 45, 6, 0, 19}; x  =  51;
Output: 3. Explanation: Minimum length subarray is {4, 45, 6}
Input:
    A[] = {1, 10, 5, 2, 7}; x  = 9;
Output: 1. Explanation: Minimum length subarray is {10}
Expected Time Complexity: O(N)
Expected Auxiliary Space: O(1)
Constraints:
1 ≤ N, x ≤ 105
1 ≤ A[] ≤ 104 */
#include <iostream>
using namespace std;

int subarrWithSumGreaterThan(int *arr, int l, int X)
{
    // start and end both will in worst case traverse the entire array so: time complexity = O(2n) = O(n).

    int start(0), end(0), minLen(l), sum(0);

    while (start <= end && end < l)
    {
        if (sum > X) // if we found a subarray with sum > X then start searching for other subarrays by incrementing the start pointer
        {
            minLen = end - start /* +1 */ < minLen ? end - start /* +1 */ : minLen;
            /* removed + 1 since just after adding the arr[end] we moved end to end + 1 so in the next iteration we will get the wrong
            length if we used an extra +1 */
            sum -= arr[start];
            start++;
        }
        else
        {
            sum += arr[end];
            end++; // if we didn't get the sum yet then keep extending the length of the subarray
        }
    }
    while (sum > X && start < l)
    {
        minLen = end - start /* +1 */ < minLen ? end - start /* +1 */ : minLen;
        sum -= arr[start];
        start++;
    }

    return minLen;
}

int main()
{
    int arr[] = {4, 21, 7, 6, 8, 48, 12, 72, 84}, len = sizeof(arr) / sizeof(arr[0]), X = 108;
    cout << "Minimum length of subarray with sum > " << X << " is " << subarrWithSumGreaterThan(arr, len, X) << endl;
    return 0;
}