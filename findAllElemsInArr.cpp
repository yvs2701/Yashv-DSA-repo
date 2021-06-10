/* Given an array of size n, find all elements in array that appear more than n/k times. For example, if the input arrays is
{3, 1, 2, 2, 1, 2, 3, 3} and k is 4, then the output should be [2, 3]. Note that size of array is 8 (or n = 8),
so we need to find all elements that appear more than 2 (or 8/4) times. There are two elements that appear more than two times, 2 and 3. */
#include <iostream>
#include <unordered_map>
using namespace std;

int main(){
    int N, k;
    cin >> N >> k;
    int arr[N];
    for (int i = 0; i < N; i++)
        cin >> arr[i];

    // O(n) approach with O(n) auxillary space
    unordered_map<int, int> freq;
    int min_freq = N/k; // elements should have a frequency greater than this
    for(int i : arr)
        freq[i]++;
    for(auto i : freq)
        if(i.second > min_freq)
            cout << i.first << " ";

    cout << endl;
    return 0;    
}