/* Given an array of positive integers. We need to make the given array a ‘Palindrome’. The only allowed operation is ”merging” 
(of two adjacent elements). Merging two adjacent elements means replacing them with their sum. The task is to find 
the minimum number of merge operations required to make the given array a ‘Palindrome’.
To make any array a palindrome, we can simply apply merge operation n-1 times where n is the size of the array 
(because a single-element array is always palindromic, similar to single-character string). 
In that case, the size of array will be reduced to 1. But in this problem, we are asked to do it in the minimum number of operations.
Input : arr[] = {15, 4, 15}
Output : 0. Array is already a palindrome. So we do not need any merge operation.

Input : arr[] = {1, 4, 5, 1}
Output : 1. We can make given array palindrome with minimum one merging (merging 4 and 5 to make 9)

Input : arr[] = {11, 14, 15, 99}
Output : 3. We need to merge all elements to make a palindrome.
The expected time complexity is O(n) */
#include <iostream>
#include <vector>
using namespace std;

int _countMergeToPalindrome(int *arr, int start, int end)
{
    if (end - start + 1 <= 1) // if end and start point at same element or lets say start is > end
        return 0;             // 0 merges needed a single element is always palindrome

    if (arr[start] == arr[end])
        return _countMergeToPalindrome(arr, start + 1, end - 1); // j = end - 1 already

    else if (arr[start] > arr[end]) // till here the array is palindrome so we will merge (end-1) and (end-2)th element to check palindrome
    {
        arr[end - 1] += arr[end];
        return (1 + _countMergeToPalindrome(arr, start + 1, end - 1));
        // add 1 to number of merges returned by rest of the array, since we have merged at this step
    }
    /* else if (arr[start] > arr[end]) 
    if above conditions are not executed this will obv. execute...The advantage of not writing it is we will avoid compiler warnings
    { */
    arr[start + 1] += arr[start];
    return (1 + _countMergeToPalindrome(arr, start + 1, end - 1));
    // add 1 to number of merges returned by rest of the array, since we have merged at this step
    // }
}

int countMergeToPalindrome(int *arr, int l)
{
    int newArr[l]; // so that we don't modify our original array
    for (int i = 0; i < l; i++)
        newArr[i] = arr[i];
    return _countMergeToPalindrome(newArr, 0, l - 1);
}

int main()
{
    int arr[] = {4, 5, 2, 7, 4}; // 5 and 2 should be merged... then {4,7,7,4} will be palindrome
    cout << "Minimum " << countMergeToPalindrome(arr, sizeof(arr) / sizeof(arr[0])) << " element(s) must be merged to make the array palindrome."
         << endl;
    return 0;
}