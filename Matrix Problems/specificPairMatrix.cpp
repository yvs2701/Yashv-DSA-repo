/* Given an n x n matrix mat[n][n] of integers, find the maximum value of mat(c, d) – mat(a, b) over all choices of indexes such that 
both c > a and d > b.
Input:
 1  2 -1 -4 -20
-8 -3  4  2  1
 3  8  6  1  3
-4 -1  1  7 -6
 0 -4 10 -5  1
Output: 18
The maximum value is 18 as mat[4][2] - mat[1][0] = 18 has maximum difference. 
***The program should do only ONE traversal of the matrix. i.e. expected time complexity is O(n^2)*** */
#include <iostream>

int maxDifferencePair(int **matrix, int n)
{
    // NOTE - the condition of c > a and d > b is a constraint,
    /* due to this constraint our pair elements can never be in the same row or column, so we need to store the maximum at
    (i, j) [which will be max from (i,j) to (n-1, n-1)] in the following way: */
    int maxValue = INT32_MIN, maxtillnow[n][n];
    // no need to initialise the maxtillnow we are doing it in the third loop
    maxtillnow[n - 1][n - 1] = matrix[n - 1][n - 1];
    // storing the last row:
    int max = matrix[n - 1][n - 1];
    for (int c = n - 2; c >= 0; c--)
    {
        if (matrix[n - 1][c] > max)
            max = matrix[n - 1][c];
        maxtillnow[n - 1][c] = max;
    }
    // storing the last column:
    max = matrix[n - 1][n - 1];
    for (int r = n - 2; r >= 0; r--)
    {
        if (matrix[r][n - 1] > max)
            max = matrix[r][n - 1];
        maxtillnow[r][n - 1] = max;
    }
    // for the rest of the matrix calculating difference and storing
    for (int i = n - 2; i >= 0; i--)
        for (int j = n - 2; j >= 0; j--)
        {
            if (maxtillnow[i + 1][j + 1] - matrix[i][j] > maxValue)
                maxValue = maxtillnow[i + 1][j + 1] - matrix[i][j];
            maxtillnow[i][j] = std::max(matrix[i][j], std::max(maxtillnow[i][j + 1], maxtillnow[i + 1][j]));
        }

    return maxValue;
}

int main()
{
    using namespace std; // simply for writing cin and cout without resolving scope again and again

    int n, **matrix;
    cin >> n;
    matrix = new int *[n];
    for (int i = 0; i < n; i++)
        matrix[i] = new int[n];
    // input matrix
    for (int r = 0; r < n; r++)
        for (int c = 0; c < n; c++)
            cin >> matrix[r][c];

    cout << maxDifferencePair(matrix, n) << endl;
    return 0;
}