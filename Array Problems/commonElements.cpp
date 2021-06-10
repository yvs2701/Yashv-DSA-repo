/* Given three arrays sorted in increasing order. Find the elements that are common in all three arrays.
If there are no such elements return an empty array. In this case the output will be printed as -1.
Note: Take care of the duplicates without using any additional Data Structure.
Example:
Input:
    n1 = 6; A = {1, 5, 10, 20, 40, 80}
    n2 = 5; B = {6, 7, 20, 80, 100}
    n3 = 8; C = {3, 4, 15, 20, 30, 70, 80, 120}
Output: 20 80. Explanation: 20 and 80 are the only common elements in A, B and C.
Expected Time Complexity: O(n1 + n2 + n3)
Expected Auxiliary Space: O(n1 + n2 + n3) */
#include <iostream>
#include <unordered_map>
using namespace std;

void countCommon(int *arr1, int l1, int *arr2, int l2, int *arr3, int l3)
{
    // time = O(l1 + l2 + l3), space = O(l1 + l2 + l3)
    unordered_map<int, int> common;
    for (int i = 0; i < l1; i++)
    {
        if (common[arr1[i]] == 0) // prevents duplication
            common[arr1[i]]++;
    }
    for (int i = 0; i < l2; i++)
    {
        if (common[arr2[i]] == 1) // increment only those elements which were common with arr1
            common[arr2[i]]++;
    }
    for (int i = 0; i < l3; i++) // increment only those elements which were common with arr1 and arr2
    {
        if (common[arr3[i]] == 2)
            common[arr3[i]]++;
    }

    cout << "Common elements in the three:\n";
    for(auto i : common)
        if(i.second == 3)
            cout << i.first << " ";
}

int main()
{
    int a[] = {1, 5, 10, 20, 40, 80};
    int b[] = {6, 7, 20, 80, 100};
    int c[] = {3, 4, 15, 20, 30, 70, 80, 120};

    countCommon(a, sizeof(a)/sizeof(a[0]), b, sizeof(b)/sizeof(b[0]), c, sizeof(c)/sizeof(c[0]));
    cout << endl;
    return 0;
}