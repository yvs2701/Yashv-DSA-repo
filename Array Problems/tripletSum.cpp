/* Given an array arr of size n and an integer X. Find if there's a triplet in the array which sums up to the given integer X.
Input:
    n = 6, X = 13
    arr[] = [1 4 45 6 10 8]
Output: 1 (true). Explanation: The triplet {1, 4, 8} in the array sums up to 13.
Input:
    n = 5, X = 10
    arr[] = [1 2 4 3 6]
Output: 1 (true). Explanation: The triplet {1, 3, 6} in the array sums up to 10.
Expected Time Complexity: O(n^2)
Expected Auxiliary Space: O(1) */
#include <iostream>
#include <unordered_set>
#include <algorithm>
using namespace std;

/* one way to solve this problem is to iterate the whole array (arr) and for each element call a function:
which will check in the remaining array IF there are 2 elements whose sum = X - arr[i] 
i.e. we can break this problem of triplet sum into pairSum which we have done earlier... See the pairSum program
Time complexity will be O(N^2) {since pairSum need O(N) time} and space needed would be O(N). The implementation is as follows: */
/* bool pairSum(int *start, int l, int sum)
{
    unordered_set<int> pair;
    while (l-- > 0) // runs O(N) times
    {
        if (pair.find(*start) != pair.end())
            return true;
        pair.insert(sum - *start);
        start++; // we can increment the pointer itself it wont effect arr since we are calling by value not by reference
    }

    return false;
} */

int main()
{
    int arr[] = {4, 6, 7, 8, 12}, len = sizeof(arr) / sizeof(arr[0]), X = 21;

    // time = O(n^2) with space = O(n)
    // loop below 0 to l - 3, i.e. runs l - 2 times (even if it runs len times no issues will happen pairSum will return right answer)
    // we cant find any PAIRS after we reach l - 2 th index, since then only 2 elements will be left and paiSum() will recieve only 1 length array
    /* for (int i = 0; i < len - 2; i++) // O(l-3) = O(l) = O(n)
        if (pairSum(arr + i + 1, len - i - 1, X - arr[i]))
        {
            cout << true << endl;
            return 0;
        }
    cout << false << endl; */


    // time = O(n^2) with space = O(1) approach
    sort(arr, arr + len); // O(n.logn) but this will be neglected since operations ahead have worse time complexity
    // nested O(n^2) loopd:
    for (int i = 0; i < len - 2; i++)
    {
        int leftptr = i + 1;
        int rightptr = len - 1;
        int sum = X - arr[i];

        while (leftptr < rightptr)
        {
            if (arr[leftptr] + arr[rightptr] == sum)
            {
                cout << true << endl;
                return 0;
            }
            else if (arr[leftptr] + arr[rightptr] < sum)
                leftptr++;
            else // arr[leftptr] + arr[rightptr] > sum
                rightptr--;
        }
    }
    cout << false << endl;
    return 0;
}