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
arr2[] = {5, 6, 7, 8, 9}*/
#include <iostream>
using namespace std;

void merge(int *mergedArr, int *arr1, int len1, int *arr2, int len2){
    int ptr1 = 0;
    int ptr2 = 0;
    int index = 0;

    while ((ptr1 < len1) && (ptr2 < len2))
    {
        if (arr1[ptr1] < arr2[ptr2])
            mergedArr[index++] = arr1[ptr1++];
        else
            mergedArr[index++] = arr2[ptr2++];
    }
    while (ptr1 < len1)
        mergedArr[index++] = arr1[ptr1++];
    while (ptr2 < len2)
        mergedArr[index++] = arr2[ptr2++];
}

int main()
{
    int arr1[] = {1, 3, 5, 7}, arr2[] = {0, 2, 6, 8, 9};
    int solution[sizeof(arr1) / sizeof(arr1[0]) + sizeof(arr2) / sizeof(arr2[0])];
    // solution is the container to conatain our answer
    merge(solution, arr1, sizeof(arr1) / sizeof(arr1[0]), arr2, sizeof(arr2) / sizeof(arr2[0]));

    for (int i = 0; i < sizeof(solution)/sizeof(solution[0]); i++)
        cout << solution[i] << " ";

    cout << endl;
    return 0;
}