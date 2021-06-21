/* Given a binary matrix. Find the maximum area of a rectangle formed only of 1s in the given matrix.
Input:
n = 4, m = 4
M[][] = {{0 1 1 0},
         {1 1 1 1},
         {1 1 1 1},
         {1 1 0 0}}
Output: 8. Explanation: For the above test case the matrix will look like
0 1 1 0
1 1 1 1
1 1 1 1
1 1 0 0
the max size rectangle is 
1 1 1 1
1 1 1 1
and area is 4 * 2 = 8.
Expected Time Complexity : O(n*m)
Expected Auixiliary Space : O(m) */
#include <iostream>
#include <stack>
using namespace std;

int maxHistoArea(int *histogram, int len)
{
    // Create an empty stack. The stack holds indexes of histogram[]. The bars stored in stack are always in increasing order of their heights.
    stack<int> s;

    int max_area = 0;  // Initialize max area
    int tp;            // To store top of stack
    int area_with_top; // To store area with top bar as the smallest bar

    // Run through all bars of given histogram
    int i = 0;
    while (i < len)
    {
        // If this bar is higher than the bar on top of the stack then push it to stack
        if (s.empty() || histogram[s.top()] <= histogram[i])
            s.push(i++);

        // If this bar is lower than top of stack, then calculate area of rectangle with stack top as the smallest (or minimum height) bar.
        // 'i' is 'right index' for the top and element before top in stack is 'left index'
        else
        {
            tp = s.top(); // store the top index
            s.pop();      // pop the top

            // Calculate the area with histogram[tp] stack as smallest bar
            area_with_top = histogram[tp] * (s.empty() ? i : i - s.top() - 1);

            // update max area, if needed
            if (max_area < area_with_top)
                max_area = area_with_top;
        }
    }

    // Now pop the remaining bars from stack and calculate area with every popped bar as the smallest bar
    while (s.empty() == false)
    {
        tp = s.top();
        s.pop();
        area_with_top = histogram[tp] * (s.empty() ? i : i - s.top() - 1);

        if (max_area < area_with_top)
            max_area = area_with_top;
    }

    return max_area;
}

int maxAreaRectangle(int **matrix, int row, int col)
{
    int maxArea(0), area(0);
    int histogram[col] = {0}; // stores the height of histograms IN THE CURRENT ROW

    for (int r = 0; r < row; r++)
    {
        for (int c = 0; c < col; c++)
        {
            if (matrix[r][c] == 0) // the running height of histogram bar is dicontinued/broken
                histogram[c] = 0;  // start new histogram height streak, back from to 0
            else
                histogram[c] += matrix[r][c]; // add the height of histogram bar
        }
        // now our histogram is ready, now calculate the max area in this histogram
        area = maxHistoArea(histogram, col); // finds area of histogram in O(col)
        maxArea = area > maxArea ? area : maxArea;
    }
    return maxArea;
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

    cout << "Maximum possible area for a rectangle = " << maxAreaRectangle(matrix, row, col) << endl;
    return 0;
}