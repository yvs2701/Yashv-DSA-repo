/* Given a boolean 2D array of n x m dimensions where each row is sorted.
Find the 0-based index of the first row that has the maximum number of 1's.
Input: 
N = 4 , M = 4
Arr[][] = {{0, 1, 1, 1},
           {0, 0, 1, 1},
           {1, 1, 1, 1},
           {0, 0, 0, 0}}
Output: 2. Explanation: Row 2 contains 4 1's (0-based indexing).
Input: 
N = 2, M = 2, Arr[][] = {{0, 0}, {1, 1}}
Output: 1. Explanation: Row 1 contains 2 1's (0-based indexing).
Expected Time Complexity: O(N+M)
Expected Auxiliary Space: O(1) */
#include <iostream>
using namespace std;

/* Condition given is that each matrix is sorted */
int rowWithMaxK(int **matrix, int row, int col, int k) // returns index of row with maximum number of K's
{
    // ***works in O(N+M)*** since col pointer will traverse the array only once in O(M)... therefore time complexity won't be O(N*M)
    int count(0), maxCountIndex(0);
    int colptr = col - 1;
    // we will keep decrementing it till we keep getting 1, in the next row if 1 is not present at that index then that row has lesser number of 1s
    for (int r = 0; r < row; r++)
    {
        while (matrix[r][colptr] == 1)
        {
            colptr--;
            // colptr keeps decreasing and the array was boolean, so either it has 0 or 1,
            // further it is sorted
            maxCountIndex = r; // storing index of row with max 1s
        }
        if (colptr < 0) // that means this complete row was filled with 1s
            break;      // hence it will have maximum 1s
    }

    return maxCountIndex;
}

int main()
{
    int row, col, **matrix;
    cin >> row >> col;

    matrix = new int *[row];
    for (int i = 0; i < row; i++)
        matrix[i] = new int[col];

    // input matrix
    for (int r = 0; r < row; r++)
        for (int c = 0; c < col; c++)
            cin >> matrix[r][c];

    cout << "Row " << rowWithMaxK(matrix, row, col, 1) << " has max number of 1's" << endl;
    return 0;
}