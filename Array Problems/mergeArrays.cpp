/* Given two sorted arrays arr1[] of size N and arr2[] of size M. 
Each array is sorted in non-decreasing order. 
Merge the two arrays into one sorted array in non-decreasing order without using any extra space. 
EXAMPLE:
    N = 4, M = 5
    arr1[] = {1, 3, 5, 7}
    arr2[] = {0, 2, 6, 8, 9}
Output: 0 1 2 3 5 6 7 8 9
Since you can't use any 
extra space, modify the given arrays
to form:
arr1[] = {0, 1, 2, 3}
arr2[] = {5, 6, 7, 8, 9} */
#include <iostream>
#include <algorithm>
using namespace std;

void merge(int *arr1, int len1, int *arr2, int len2)
{
    // time complexity = O((n + m ).log(n + m))
    int ptr1 = 0, ptr2 = 0, last1 = len1 - 1;

    while (ptr1 <= last1 and ptr2 < len2)
    {
        // ptr1 should be <= last1 since after last1 our arr1 is unsorted... after this we just need to sort our individual arrays back
        if (arr1[ptr1] < arr2[ptr2])
            ptr1++;
        else // i.e. arr1[ptr1] >= arr2[ptr2]
        {
            swap(arr2[ptr2++], arr1[last1--]);
            // reason for decrementing last1:
            // if arr1[ptr1] >= arr2[ptr2] then all elements after ptr1 are also greater, that means we need to shift all the elements
            // to right by 1, therefore the last element will get out of arr1... it needs to be accomodated in arr2
            // this idea was invented from the brute force approach where we traverse arr2 from end and and check in arr1: where the element
            // in arr1 > arr2 then we place this element of arr2 at its correct place in arr1 and then shift all other elements...
            // this results in last element getting out of arr1 bounds so it is accomodated at this element of arr2
        }
    }
    // Sort first array
    sort(arr1, arr1 + len1); // O(len1.log(len1))
    // Sort second array
    sort(arr2, arr2 + len2); // O(len2.log(len2))
}

int main()
{
    int arr1[] = {1, 3, 5, 7}, arr2[] = {0, 2, 6, 8, 9};
    merge(arr1, sizeof(arr1) / sizeof(arr1[0]), arr2, sizeof(arr2) / sizeof(arr2[0]));

    for (int i = 0; i < sizeof(arr1) / sizeof(arr1[0]); i++)
        cout << arr1[i] << " ";
    for (int i = 0; i < sizeof(arr2) / sizeof(arr2[0]); i++)
        cout << arr2[i] << " ";

    cout << endl;
    return 0;
}