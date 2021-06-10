/* Given an array of positive and negative numbers. Find if there is a subarray (of size at-least one) with 0 sum.
Input:
    5
    4 2 -3 1 6
Output: 
    Yes
Explanation: 2, -3, 1 is the subarray with sum 0

Input:
    5
    4 2 0 1 6
Output:
    Yes
Explanation: there is an element 0 in the array so subarray containin {0} has a sum 0
Expected Time Complexity: O(n).
Expected Auxiliary Space: O(n). */
#include <iostream>
#include <unordered_map> // since its it can access elements in O(1)
using namespace std;

bool findSubarr(int *arr, int l)
{
    /* we will traverse the array, and for each element store the sum from start to this element. If the sum repeats itself, that means,
    that some subarray exists in between these two indices which has a sum = 0...
    Even if we wont find any such condition then for that situtation we will be checking if any element = 0, then the subarray with sum = 0
    would be {0} */
    unordered_map<int, int> freq;
    int sum = 0;

    for (int i = 0; i < l; i++)
    {
        sum += arr[i];
        if (freq[sum] || arr[i] == 0 || sum == 0) // if sum has freq > 0 (0 is treated as false) OR element itself = 0 OR sum till now = 0
            return true;
        freq[sum]++;
    }
    return false;
}

int main()
{
    int len;
    cin >> len;
    int arr[len];
    for (int i = 0; i < len; i++)
        cin >> arr[i];

    if (findSubarr(arr, len))
        cout << "Yes";
    else
        cout << "No";

    cout << endl;
    return 0;
}