/* Given a matrix of size r*c. Traverse the matrix in spiral form.
Input: r = 4, c = 4
matrix[][] = {{1, 2, 3, 4},
           {5, 6, 7, 8},
           {9, 10, 11, 12},
           {13, 14, 15,16}}
Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
Input: r = 3, c = 4  
matrix[][] = {{1, 2, 3, 4},
           {5, 6, 7, 8},
           {9, 10, 11, 12}}
Output: 1 2 3 4 8 12 11 10 9 5 6 7
Explanation: Applying same technique as shown above, output for the 2nd testcase will be 1 2 3 4 8 12 11 10 9 5 6 7
Expected Time Complexity: O(r*c)
Expected Auxiliary Space: O(r*c), for returning the answer only */
#include <iostream>
using namespace std;

// i, j: Start index of matrix, row and column respectively 
// m, n: End index of matrix row and column respectively
void _spiralTraversal(int **arr, int i, int j, int m, int n)
{
    // If i or j lies outside the matrix
    if (i >= m or j >= n)
        return;
 
    // Print First Row
    for (int p = j; p < n; p++)
        cout << arr[i][p] << " ";
 
    // Print Last Column
    for (int p = i + 1; p < m; p++)
        cout << arr[p][n - 1] << " ";
 
    // Print Last Row, if Last and
    // First Row are not same
    if ((m - 1) != i)
        for (int p = n - 2; p >= j; p--)
            cout << arr[m - 1][p] << " ";
 
    // Print First Column,  if Last and
    // First Column are not same
    if ((n - 1) != j)
        for (int p = m - 2; p > i; p--)
            cout << arr[p][j] << " ";
 
    _spiralTraversal(arr, i + 1, j + 1, m - 1, n - 1);
}

void spiralTraversal(int *spiral, int **arr, int r, int c)
{
    _spiralTraversal(arr, 0, 0, r, c);
}

int main()
{
    int row, clmn;
    cin >> row >> clmn;
    int **matrix;
    matrix = new int *[row];
    for (int i = 0; i < 4; i++)
        matrix[i] = new int[clmn];

    cout << "Enter the matrix:\n";
    for (int i = 0; i < row; i++)
        for (int j = 0; j < clmn; j++)
            cin >> matrix[i][j];
    // I wanted to keep array parameter in function to be free of size... so i had to use int ** at both the places to avoid errors
    // another method could be to simply declare your 2d array as the following & recieve them in fn as = void fun(int arr[][4]);
    /*  int matrix[][4] = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 16}}; */

    int solution[(sizeof(matrix) / sizeof(matrix[0])) * (sizeof(matrix[0]) / sizeof(matrix[0][0]))]; // solution array of size row*columns
    spiralTraversal(solution, matrix, sizeof(matrix) / sizeof(matrix[0]), sizeof(matrix[0]) / sizeof(matrix[0][0]));
    return 0;
}