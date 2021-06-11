/* Given two arrays: a1[0..n-1] of size n and a2[0..m-1] of size m. Task is to check whether a2[] is a subset of a1[] or not. 
Both the arrays can be sorted or unsorted. It may be assumed that elements in both array are distinct.
Input:
    a1[] = {11, 1, 13, 21, 3, 7}
    a2[] = {11, 3, 7, 1}
Output: Yes. Explanation: a2[] is a subset of a1[]
Input:
    a1[] = {1, 2, 3, 4, 5, 6}
    a2[] = {1, 2, 4}
Output: Yes. Explanation: a2[] is a subset of a1[]
Input:
    a1[] = {10, 5, 2, 23, 19}
    a2[] = {19, 5, 3}
Output: No. Explanation: a2[] is not a subset of a1[]
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(n) */
#include <iostream>
#include <unordered_set>
using namespace std;

bool isSubset(int *arr1, int l1, int *arr2, int l2)
{
    unordered_set<int> set1;
    for(int i = 0; i < l1; i++)
        set1.insert(arr1[i]);
    
    for(int i = 0; i < l2; i++)
        if(set1.find(arr2[i]) == set1.end()) // we can't find arr2 element in arr1
            return false;
    return true;
}

int main()
{
    int a1[] = {4, 5, 6, 7, 8, 11, 12, 21, 24, 48, 72,84};
    int a2[] = {84, 48, 72, 21};

    if(isSubset(a1, sizeof(a1) / sizeof(a1[0]), a2, sizeof(a2) / sizeof(a2[0])))
        cout << "Yes";
    else
        cout << "No";
    cout << endl;
    return 0;
}