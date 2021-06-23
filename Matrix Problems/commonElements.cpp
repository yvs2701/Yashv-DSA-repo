/* Given an m x n matrix, find all common elements present in all rows in O(mn) time and ONLY ONE TRAVERSAL OF MATRIX IS ALLOWED.
Input:
mat[4][5] = {{1, 2, 1, 4, 8},
             {3, 7, 8, 5, 1},
             {8, 7, 7, 3, 1},
             {8, 1, 2, 7, 9},}
Output: 
1 8 or 8 1
8 and 1 are present in all rows. */
#include <iostream>
#include <unordered_map> // since its average time complexity of access O(1)
using namespace std;

// finds all the common element in an unsorted matrix in O(row * col) time, with O(row * col) extra space
void filterCommon(int **matrix, int row, int col)
{
    // logic is to store the elements in the row, then compare their frequencies
    unordered_map<int, int> freq;
    for (int r = 0; r < row; r++)
        for (int c = 0; c < col; c++)
        {
            if (freq[matrix[r][c]] == r) // prevents counting duplicates
                freq[matrix[r][c]]++;
            // finally printing the elements, that occur in all rows, in the final row
            if (r == row - 1 && freq[matrix[r][c]] == row)
                cout << matrix[r][c] << " ";
            // this way we don't have to travel our map again to print those numbers
        }

    /* for (pair<int, int> elem : freq) // we can also use auto but all of us are already aware an unordered map stores key value pairs as 'pair'
        if (elem.second == row)
            std::cout << elem.first << " "; */
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
    filterCommon(matrix, row, col);
    cout << endl;
    return 0;
}