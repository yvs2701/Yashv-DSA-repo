/* Given an array arr[] of N non-negative integers representing the height of blocks.
If width of each block is 1, compute how much water can be trapped between the blocks during the rainy season.
Input:
    N = 6
    arr[] = {3,0,0,2,0,4}
Output: 10. 
Input:
    N = 4
    arr[] = {7,4,0,9}
Output: 10. Explanation: Water trapped by above block of height 4 is 3 units and above block of height 0 is 7 units.
So, the total unit of water trapped is 10 units.
Input:
    N = 3
    arr[] = {6,9,9}
Output:0. Explanation: No water will be trapped.
Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N) */
#include <iostream>
using namespace std;

/* now there are many ways to do this butmy approach would be to find height of blocks on the left and right of the current element
then simply subtract the height of this element from the lesser height (amongst the two maximum height walls) and if the result is
not negative then add this block height to the total volume of water.
Time = O(n), space = O(n) */

int main()
{
    int heights[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, len = sizeof(heights) / sizeof(heights[0]);
    int leftMAX[len], rightMAX[len]; // space complexity = O(2n) = O(n)
    int max;

    max = heights[0];
    for (int i = 0; i < len; i++) // storing max heights for each index to the left of this index
    {
        if (heights[i] > max)
            max = heights[i];

        leftMAX[i] = max;
    }

    max = heights[len - 1];
    for (int i = len - 1; i >= 0; i--)
    {
        if (heights[i] > max)
            max = heights[i];

        rightMAX[i] = max;
    }

    int totalVolume = 0;
    for(int currHeight = 0; currHeight < len; currHeight++)
    {
        int lowerWall = min(leftMAX[currHeight], rightMAX[currHeight]);
        totalVolume += lowerWall > heights[currHeight] ? lowerWall - heights[currHeight] : 0;
    }
    // time complexity for these 3 loops = O(3n) = O(n)
    cout << "Total volume of water captured = " << totalVolume << endl;
    return 0;
}