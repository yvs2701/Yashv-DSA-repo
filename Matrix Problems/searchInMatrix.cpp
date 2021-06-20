/* Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
- Integers in each row are sorted from left to right.
- The first integer of each row is greater than the last integer of the previous row.
Constraints:
1 <= row, column <= 100
-10e4 <= matrix[i][j], target <= 10e4 */

#include <iostream>
using namespace std;

/*
This approach will fail for this edge case:
10 20 30 40
15 29 35 45
27 32 37 48
32 33 39 50
Key = 29
This approach will work 100% safe, if it was given that first element of row is > last element of previous row

bool binarySearch(int *arr, int start, int end, int key) // time complexity = O( log2(row) ) or O( log2(end-start) )
{
    end--; // just to ensure we don't go out of bounds
    while(start <= end)
    {
        int mid = (start + end) / 2;
        if (arr[mid] == key)
            return true;
        
        if (key > arr[mid])
            start = mid + 1;
        else
            end = mid - 1;
    }
    return false;
}

bool findMatrix(int **matrix, int row, int col, int key) // total time complexity of my algo = O( row + log2(col) )
{
    // we know every element in a row will be greater than previous one...
    // we also know every row will start with the element > last integer of previous row...
    // so start searching from 2nd row... for every row check if key < first element of row, if yes then key is either found in previous row
    // or not present. If key > first element of row then repeat the same process for next row.
    int i;
    for (i = 1; i < row; i++)
    {
        if (key < matrix[i][0])
            break;
        else if (key == matrix[i][0])
            return true;
    }
    // now either we have exhauste the i variable and i == row; or we broke out of the loop and we should start searching in the previous row
    if (i < row)
        return binarySearch(matrix[i - 1], 0, col, key);
    // else
    return false;
} */

// a better approach is to traverse from end and not search only linearly once we break from the loop like we were doing in the above approach
// here we have two pointers and we will traverse the grid by comparing the surrounding elements, we CHANGE OUR DIRECTION OF TRAVERSAL ANYTIME
bool findInMatrix(int **matrix, int row, int clmn, int key)
{
    // earlier we were moving from top left to bottom right, and we were not able to change direction of our search
    // here we are moving from top right to any of the three corners depending upon the values

    int rptr = 0, cptr = clmn - 1; // row and column pointers, we will start traversing from top right
    while (rptr < row && cptr >= 0)
    {
        if (matrix[rptr][cptr] == key)
            return true;
        else if (key < matrix[rptr][cptr]) // then search for smaller elements by searching leftwards in a row
            cptr--;
        else // key > matrix[rptr][cptr] then start searching for bigger elements by searching down in a column
            rptr++;
    }
    // else
    return false;
}

int main()
{
    int row, clmn; cin >> row >> clmn;
    // int matrix[row][clmn]; wont work since in the function i have recieved the array as a pointer
    // btw we can also use a vector... but i will use array itself
    int **matrix;
    matrix = new int *[row];
    for (int i = 0; i < row; i++)
        matrix[i] = new int[clmn];

    // input the matrix
    for (int i = 0; i < row; i++)
        for (int j = 0; j < clmn; j++)
            cin >> matrix[i][j];

    int key;
    cout << "Which element to find ? ";
    cin >> key;

    // search and print result
    // cout << findMatrix(matrix, row, clmn, key) << endl; // time complexity = O(row + log(col))
    cout << findInMatrix(matrix, row, clmn, key) << endl; // time complexity = O(row + col)
    return 0;
}
/* NOTE - here i have mentione the time complexity to be log2... but we usually dont specify the base of the log in time complexity
since in the worst case N would be too big (in logN) so any base can be interchanged by the log base-rule... so anyways base doesn;t matter much
in these cases. */