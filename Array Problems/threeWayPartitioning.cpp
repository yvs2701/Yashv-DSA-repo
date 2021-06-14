/* Given an array of size n and a range [a, b]. The task is to partition the array around the range such that array is divided into three parts.
1) All elements smaller than a come first.
2) All elements in range a to b come next.
3) All elements greater than b appear in the end.
Note: The generated output is 1 if you modify the given array successfully.
The individual elements of three sets can appear in any order. You are required to return the modified array.
Input: 
    n = 5
    A[] = {1, 2, 3, 3, 4}
    [a, b] = [1, 2]
Output: 1. Explanation: One possible arrangement is: {1, 2, 3, 3, 4}. If you return a valid arrangement, output will be 1.
Input: 
    n = 3 
    A[] = {1, 2, 3}
    [a, b] = [1, 3]
Output: 1. Explanation: One possible arrangement is: {1, 2, 3}. If you return a valid arrangement, output will be 1.
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(1) */
#include <iostream>
using namespace std;

bool threeWayPartition(int *arr, int l, int a, int b) // time complexity = O(2n) = O(n), since two pointers traverse the entire array
{
    if (b < a)
        return false;

    int start = 0, end = 1; // breakpoints/checkmarks to our partitions

    // for elements < lower bound of range, i.e. elements < a
    while (start <= end && end < l)
    {
        if (arr[start] < a)
            start++;
        else if (arr[end] < a) // arr[start] > a and arr[end] <= a
            swap(arr[start], arr[end]);

        if (arr[end] >= a)
            end++;

        if (start > end) // then swap these pointers
        {
            start = start + end;
            end = start - end;
            start = start - end;
        }
    }
    // for elements in the range, i.e. a < elements < b (we have already placed elements < a in correct order)
    start = 0, end = 1;
    while (start <= end && end < l)
    {
        if (arr[start] < a)
            start++;
        if (arr[end] < a)
            end++;

        if (arr[start] <= b)
            start++;
        else if (arr[end] <= b) // arr[start] > b and a <= arr[end] <= b
            swap(arr[start], arr[end]);

        if (arr[end] > b)
            end++;

        if (start > end) // then swap these pointers
        {
            start = start + end;
            end = start - end;
            start = start - end;
        }
    }
    // for elements > upper bound of range, i.e. b < elements
    // if < a is filtered and <= b is filtered then > b is filtered too (obviously)
    return true;
}

int main()
{
    int arr[] = {48, 7, 12, 8, 84, 21, 4, 24, 6, 72, 108}, len = sizeof(arr) / sizeof(arr[0]), a = 21, b = 72;
    cout << threeWayPartition(arr, len, a, b) << "\n";

    // printing array
    int i;
    for (i = 0; i < len; i++)
    {
        if (arr[i] < a)
            cout << arr[i] << " ";
        else
            break;
    }
    cout << "- ";
    for (; i < len; i++)
    {
        if (arr[i] <= b)
            cout << arr[i] << " ";
        else
            break;
    }
    cout << "- ";
    for (; i < len; i++)
        cout << arr[i] << " ";

    cout << endl;
    return 0;
}