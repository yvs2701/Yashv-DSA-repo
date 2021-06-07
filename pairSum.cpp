/* Given an array of N integers, and an integer K, find the number of pairs of elements in the array whose sum is equal to K.
Input:
    N = 4, K = 6
    arr[] = {1, 5, 7, 1}
Output: 2. Explanation: arr[0] + arr[1] = 1 + 5 = 6 and arr[1] + arr[3] = 5 + 1 = 6
Input:
    N = 4, X = 2
    arr[] = {1, 1, 1, 1}
Output: 6. Explanation: Each 1 will produce sum 2 with any 1
    Expected Time Complexity: O(N)
    Expected Auxiliary Space: O(N)*/
#include <iostream>
#include <unordered_map>
using namespace std;

int getPairsCount(int *arr, int l, int k)
{
    int pairs = 0;
    // for O(N) time complexity we need to find the elements within O(1) time and traverse the array in O(N) time...
    // so we have to use maps
    unordered_map<int, int> pairToSum; // stores elements which can add up to the value to give K (sum)
    for (int i = 0; i < l; i++)
    {
        /*REMEMBER [key] will create a new element with the given key if it didnt find any and initialize it with default value*/
        if (pairToSum[arr[i]] != 0 /*that means it is created right now we didnt unsert it*/)
            pairs+= pairToSum[arr[i]]; // to count all the permutations
        pairToSum[k - arr[i]]++;
    }
    return pairs;
}

int main()
{
    int N, K;
    cin >> N >> K;
    int arr[N];
    for (int i = 0; i < N; i++)
        cin >> arr[i];

    cout << getPairsCount(arr, sizeof(arr) / sizeof(arr[0]), K) << endl;
    return 0;
}