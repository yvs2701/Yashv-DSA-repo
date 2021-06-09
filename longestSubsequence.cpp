/* Given an array of positive integers. Find the length of the longest sub-sequence such that elements in the
subsequence are consecutive integers, the consecutive numbers can be in any order.
Input:
    N = 7
    a[] = {2,6,1,9,4,5,3}
Output: 6. Explanation: The consecutive numbers here are 1, 2, 3, 4, 5, 6. These 6 numbers form the longest consecutive subsquence.

Input:
    N = 7
    a[] = {1,9,3,10,4,20,2}
Output: 4. Explanation: 1, 2, 3, 4 is the longest consecutive subsequence.
Expected Time Complexity: O(N).
Expected Auxiliary Space: O(N).*/
#include <iostream>
#include <unordered_set>

int longestSubsequence(int *arr, int l)
{
    std::unordered_set<int> nums; // ofc we could've used unordered_map which provides same find and insert speeds in avg and worst cases
    // we are using unordered in place of ordered collections since unordered can provide O(1) time complexity in average cases
    for (int i = 0; i < l; i++)
        nums.insert(arr[i]); // storing the elements for faster access

    int max_len = 1, len = 1; // since any element itslef is a part of its subsequence minimum length is 1

    for (int i = 0; i < l; i++)
    {
        // if .find() cant find the element it will return iterator just next to last element i.e. .end()
        if (nums.find(arr[i] - 1) == nums.end()) // i.e. we can't find a number one less than this one, which means our subsequence starts from here
        {
            int key = arr[i];
            while (nums.find(key + 1) != nums.end()) // we can start searching for the next elements that should be present in the subsequence
            {
                len++;
                key++;
            }
            max_len = len > max_len ? len : max_len;
            len = 1;
        }
    }
    return max_len;
}

int main()
{
    int N;
    std::cin >> N;
    int arr[N];
    for (int i = 0; i < N; i++)
        std::cin >> arr[i];

    std::cout << longestSubsequence(arr, N) << std::endl;
    return 0;
}