/* Given an array Arr that contains N integers (may be positive, negative or zero). Find the product of the maximum product subarray.
Your Task:
You don't need to read input or print anything. Your task is to complete the function maxProduct() which takes the
array of integers arr and n as parameters and returns an integer denoting the answer. Use 64-bit integer data type to avoid overflow.
    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(1)
Constraints:
    1 ≤ N ≤ 500
    -102 ≤ Arri ≤ 102 */
#include <iostream>
using namespace std;
typedef long long ll;

ll maxProduct(int *arr, int len)
{
    ll max = arr[0], curr_max = 1, curr_min = 1; // if we find a negative number then we will swap min and max
    for (int i = 0; i < len; i++)
    {
        if (arr[i] > 0)
        {
            curr_max = arr[i] * curr_max;
            curr_min = arr[i] * curr_min;
        }
        else if (arr[i] < 0)
        {
            ll temp = curr_min;
            curr_min = arr[i] * curr_max;
            curr_max = arr[i] * temp;
        }
        else // arr[i] == 0
        {
            // 0 will make the product = 0, so we discontinue our array
            curr_min = 1;
            curr_max = 1;
        }
        max = max > curr_max ? max : curr_max;
    }
    return max;
}

int main()
{
    int N;
    cin >> N;
    int arr[N];
    for (int i = 0; i < N; i++)
        cin >> arr[i];

    cout << maxProduct(arr, N);
    cout << endl;
    return 0;
}

/* Example 1:
Input:
    N = 5
    Arr[] = {6, -3, -10, 0, 2}
Output: 180, Explanation: Subarray with maximum product is 6, -3, -10 which gives product as 180.
Example 2:
Input:
    N = 6
    Arr[] = {2, 3, 4, 5, -1, 0}
Output: 120 Explanation: Subarray with maximum product is 2, 3, 4, 5 which gives product as 120. */