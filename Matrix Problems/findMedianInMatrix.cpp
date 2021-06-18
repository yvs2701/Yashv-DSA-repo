/* Given a row wise sorted matrix of size RxC where R and C are always odd, find the median of the matrix.
Input:
R = 3, C = 3
M = [[1, 3, 5], 
     [2, 6, 9], 
     [3, 6, 9]]
Output: 5. Explanation: Sorting matrix elements gives us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median.
Input:
    R = 3, C = 1
    M = [[1], [2], [3]]
Output: 2.
    Expected Time Complexity: O(32 * R * log(C))
    Expected Auxiliary Space: O(1)
Constraints:
    1<= R,C <=150
    1<= matrix[i][j] <=2000 */
#include <iostream>
#include <algorithm>
using namespace std;

/* didn't work as expected
int upperBound(int *arr, int l, int key) // returns the first index at which element is greater than the passes value(key)
{
    // or one can directly use C++ upper_bound() method
    int start = 0, end = l;
    while (start < end)
    {
        int mid = (start + end) / 2;

        if (key >= arr[mid]) // if key is greater than the arr[mid]
            start = mid + 1; // search in the right subarray for just greater element than key
        else                 // key < arr[mid]
            end = mid;       // then our just greater element is in the left subarray
    }

    return start; // return element just greater than the key
} */

int medianMatrix(int **matrix, int row, int col)
{
    /* median of any sorted data is always the middle element of that data... if number of elements in data is even then we find middle
    by taking average of two middle elements 
    -   First, we find the minimum and maximum elements in the matrix. The minimum element can be easily found by comparing 
        the first element of each row, and similarly, the maximum element can be found by comparing the last element of each row.
    -   Then we use binary search on our range of numbers from minimum to maximum, we find the mid of the min and max and get a 
        count of numbers less than our mid. And accordingly change the min or max. For a number to be median, there should be 
        (r*c)/2 numbers smaller than that number (since, median is the middle element of our sorted data).
    -   So for every number, we get the count of numbers less than that, in each row of the matrix, if it is less than the required count, 
        the median must be greater than the selected number, else the median must be less than or equal to the selected number. */
    int min(matrix[0][0]), max(matrix[0][0]);
    for (int i = 0; i < row; i++) // time complexity of this loop = O(row)
    {

        if (matrix[i][0] < min)
            min = matrix[i][0];
        if (matrix[i][col - 1] > max)
            max = matrix[i][col - 1];
    }

    int desired = (row * col + 1) / 2;
    while (min < max)
    {
        int mid = min + (max - min) / 2;
        int place = 0;

        // Find count of elements smaller than mid
        for (int i = 0; i < row; i++)
            place += upper_bound(matrix[i], matrix[i] + col, mid) - matrix[i];
        
        if (place < desired)
            min = mid + 1;
        else
            max = mid;
    }

    return min;
}

int main()
{
    int **matrix, row, col;
    cin >> row >> col;
    matrix = new int *[row];
    for (int rowIndex = 0; rowIndex < row; rowIndex++)
        matrix[rowIndex] = new int[col];

    // input matrix
    for(int r = 0; r < row; r++)
        for(int c = 0; c < col; c++)
            cin >> matrix[r][c];
    // display result
    cout << medianMatrix(matrix, row, col) << endl;
    return 0;
}