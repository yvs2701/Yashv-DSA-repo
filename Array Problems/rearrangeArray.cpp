/* Given an array of positive and negative numbers, arrange them in an alternate fashion such that every positive number
is followed by negative and vice-versa maintaining the order of appearance.
Number of positive and negative numbers need not be equal. If there are more positive numbers they appear
at the end of the array. If there are more negative numbers, they too appear in the end of the array.
1)  Input:  arr[] = {1, 2, 3, -4, -1, 4}
    Output: arr[] = {-4, 1, -1, 2, 3, 4}
2)  Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
    output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0}*/
#include <iostream>
using namespace std;

int main()
{
    int arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
    int len = sizeof(arr) / sizeof(arr[0]);
    int solution[len];

    // time = O(2n)[since two pointers will traverse the whole array] = O(n), space = O(n) to store the solution
    int pos = 0, neg = 0, index = 0;
    while (pos < len && neg < len)
    {
        cout << pos << " " << neg << "\n";
        if (arr[neg] < 0 && arr[pos] >= 0) // checks if one pointer points at positive and other at negative
        {
            solution[index++] = arr[neg++];
            solution[index++] = arr[pos++];
        }
        else if (arr[neg] < 0) // i.e. pos isn't pointing at a positive number
            pos++;
        else /* if(arr[pos] >= 0) */ // i.e. neg isn't pointing at a negative number
            neg++;
    }
    while (pos < len)
    {
        if (arr[pos] >= 0)
            solution[index++] = arr[pos];
        pos++;
    }
    while (neg < len)
    {
        if (arr[neg] < 0)
            solution[index++] = arr[pos];
        neg++;
    }

    // printing the result
    for (int i = 0; i < len; i++)
        cout << solution[i] << " ";
    cout << endl;
    return 0;
}