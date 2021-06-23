/* Given a N x N matrix, where every row and column is sorted in non-decreasing order. Find the kth smallest element in the matrix.
Input:
N = 4
mat[][] = {{16, 28, 60, 64},
          {22, 41, 63, 91},
          {27, 50, 87, 93},
          {36, 78, 87, 94 }}
K = 3
Output: 27. Explanation: 27 is the 3rd smallest element.
Input:
N = 4
mat[][] = {{10, 20, 30, 40}
          {15, 25, 35, 45}
          {24, 29, 37, 48}
          {32, 33, 39, 50}}
K = 7
Output: 30. Explanation: 30 is the 7th smallest element.
Expected Time Complexity: O(N*Log(N))
Expected Auxiliary Space: O(N) */
#include <iostream>
#include <queue> // to use priority queue (max/min heap or binary search tree)
using namespace std;

int kMin(int **matrix, int n, int k) // finds Kth minimum number in a matrix in O(nLogn) time, with O(n) extra space
{
    // logic is to keep storing elements till the number of elements are < k, in a minHeap
    // time complexity to store the elements in sorted order in a min heap is O(log n)...
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> heap;
    // storing first row
    for (int c = 0; c < n; c++)
        heap.push({matrix[0][c], {0, c}}); // sotring the element and its position in the heap

    int min;
    while (k--)
    {
        min = heap.top().first;                         // stores the smallest element in every iteration
        pair<int, int> coordinates = heap.top().second; // storing coordinates of the minimum element in the heap
        heap.pop();                                     // removing this minimum element from heap
        if (heap.top().second.first != n - 1)
            heap.push({matrix[coordinates.first + 1][coordinates.second], {coordinates.first + 1, coordinates.second}});
        // pushing the element just below (in the row) the former min
    }
    
    return min;
}

int main()
{
    int **matrix, n, k;
    cin >> n;
    matrix = new int *[n];
    for (int i = 0; i < n; i++)
        matrix[i] = new int[n];
    // input matrix
    for (int r = 0; r < n; r++)
        for (int c = 0; c < n; c++)
            cin >> matrix[r][c];
    cin >> k; // Which smallest number to find ?
    cout << k << "th smallest number is = " << kMin(matrix, n, k) << endl;
    return 0;
}