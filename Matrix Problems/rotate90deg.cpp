/* Given a square matrix, turn it by 90 degrees in a clockwise direction without using any extra space.
Input:
1 2 3
4 5 6
7 8 9
Output:
7 4 1 
8 5 2
9 6 3
Input:
 1  2  3  4  5  6
 9  8  7  6  5  4 
 0  0  0  0  0  0
-1 -2 -3 -4 -5 -6
-9 -8 -7 -6 -5 -4
 0  0  0  0  0  0
Output:
0 -9 -1 0 9 1 
0 -8 -2 0 8 2 
0 -7 -3 0 7 3 
0 -6 -4 0 6 4 
0 -5 -5 0 5 5 
0 -4 -6 0 4 6 */
#include <iostream>
void transpose(int **matrix, int n) // transforms the matrix into its transpose in O(n^2)
{
    for (int row = 0; row < n; row++)
        for (int col = row; col < n; col++)
            std::swap(matrix[row][col], matrix[col][row]);
    // since we need to flip along main diagonal so col starts from row... otherwise we will flip all the elements twice and
    // as we know Transpose(Transpose(Matrix)) = Matrix
}
void mirror(int *arr, int l) // mirrors the given array in O(n)
{
    for (int i = 0; i < l / 2; i++)
        std::swap(arr[i], arr[l - i - 1]);
}
void mirrorVertical(int **matrix, int n) // mirrors the matrix along its vertically middle column in O(n)*O(mirror) = O(n^2)
{
    for (int row = 0; row < n; row++)
        mirror(matrix[row], n);
}

void rotate90(int **matrix, int n) // rotates the matrix by 90 degrees in O(transpose) + O(mirrorVertical) = O(n^2)
{
    // NOTICE- that rotating 90 degrees means first rotating by 180 degrees (finding transpose) and then mirroring along the middle column
    // the above statements can also be stated as- rotate along the main diagonal and then along the middle column, so let's code it guys
    transpose(matrix, n);
    mirrorVertical(matrix, n);
}
int main()
{
    using namespace std; // simply for writing cin and cout without resolving scope again and again
    int **matrix, n;
    cin >> n;
    matrix = new int *[n];
    for (int i = 0; i < n; i++)
        matrix[i] = new int[n];
    // input matrix
    for (int r = 0; r < n; r++)
        for (int c = 0; c < n; c++)
            cin >> matrix[r][c];
    rotate90(matrix, n);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
            cout << matrix[i][j] << " ";
        cout << "\n";
    }
    cout << endl;
    return 0;
}