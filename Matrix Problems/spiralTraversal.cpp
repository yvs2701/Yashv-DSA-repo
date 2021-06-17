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

void _spiralTraversal(int *spiral, int index, int **arr, int rstart, int cstart, int rend, int cend)
{
    if (rstart >= rend or cstart >= cend) // base case
        return;

    for (int i = cstart; i < cend; i++) // Traverse First Row
        spiral[index++] = arr[rstart][i];

    for (int i = rstart + 1; i < rend; i++) // Traverse Last Column
        spiral[index++] = arr[i][cend - 1];

    if ((rend - 1) != rstart) // Traverse Last Row, if Last and first Row are not same
        for (int i = cend - 2; i >= cstart; i--)
            spiral[index++] = arr[rend - 1][i];

    if ((cend - 1) != cstart) // Traverse First Column, if Last and first Column are not same
        for (int i = rend - 2; i > rstart; i--)
            spiral[index++] = arr[i][cstart];

    _spiralTraversal(spiral, index, arr, rstart + 1, cstart + 1, rend - 1, cend - 1);
}

void spiralTraversal(int *spiral, int **arr, int r, int c)
{
    _spiralTraversal(spiral, 0, arr, 0, 0, r, c);
}

int main()
{
    int row, clmn;
    cout << "Enter row and column lengths: ";
    cin >> row >> clmn;

    int **matrix;
    // I wanted to keep array parameter in function to be free of size... so i had to use int ** at both the places to avoid errors
    // another method could be to simply declare your 2d array as the following & recieve them in fn as = void fun(int arr[][4]);
    /*  int matrix[][4] = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 16}}; */

    matrix = new int *[row]; // array of pointer (row contains pointers to column start pointers)
    for (int i = 0; i < row; i++)
        matrix[i] = new int[clmn]; // alocating size for column array and storing its pointer in each row index

    cout << "Enter the matrix:\n";
    for (int i = 0; i < row; i++)
        for (int j = 0; j < clmn; j++)
            cin >> matrix[i][j];

    int solution[row * clmn]; // solution array of size row*columns
    spiralTraversal(solution, matrix, row, clmn);

    // printing
    for (int i : solution)
        cout << i << " ";

    cout << endl;
    return 0;
}