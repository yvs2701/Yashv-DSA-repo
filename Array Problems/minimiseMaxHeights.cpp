#include <iostream>
#include <algorithm>
using namespace std;

/* Given heights of n towers and a value k. 
We need to either increase or decrease the height of every tower by k (only once) where k > 0. 
The task is to minimize the difference between the heights of the longest and the shortest tower after modifications 
and output this difference.*/

int minimseMaxHeights(int *arr, int len, int k)
{
    sort(arr, arr + len);
    // max difference will be between smallest height and tallest one (arr[len-i-1] - arr[i])
    int minimum = arr[len - 1] - arr[0]; // first and last elements already accounted

    int a, b;
    for (int i = 1; i < len - 1; i++) // for all the towers in the middle
    {
        if (arr[len - 1] > k && arr[i] > k)
        {
            //since adding or subtracting k may mess up tha values we must find new min and max
            a = max(arr[i-1] + k, arr[len - 1] - k);
            b = min(arr[0] + k, arr[i] - k);

            minimum = min(minimum, a - b); // we have ensure a > b
        }
    }

    return minimum;
}

int main()
{
    int arr[] = {1, 5, 15, 10};
    int len = sizeof(arr) / sizeof(arr[0]);
    int k = 3;
    int solution = minimseMaxHeights(arr, len, k);

    cout << "Maximum heights after +/- k is: " << solution << endl;
    return 0;
}