#include <iostream>
using namespace std;
/*Given an array of N integers arr[] where each element represents the max number of steps 
that can be made forward from that element. Find the minimum number of jumps to reach the end of the array 
(starting from the first element). If an element is 0, then you cannot move through that element.*/

int main()
{
    int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
    const int N = sizeof(arr) / sizeof(arr[0]);

    if (arr[0] == 0) // i.e. if arr[0] = 0 we simply cant move ahead
    {
        cout << "Can't reach the end !";
        return 0;
    }
    // else
    int maxLeap = arr[0];          // stores the index of the maximum number of steps we can jump and reach to some other element
    int currLeapDistance = arr[0]; // stores the index of the lading element after the previous jump
    int jumps = 0;

    for (int i = 1; i < N; i++)
    {
        if (i + arr[i] > maxLeap)
            maxLeap = i + arr[i];

        if (i == currLeapDistance)
        {
            jumps++;
            currLeapDistance = maxLeap;
        }

        if (currLeapDistance + i >= N)
        { // i.e. we can directly jump to end now
            jumps++;
            break;
        }
    }

    if (currLeapDistance < N)
        cout << "Can't reach the end !";
    else
        cout << "Jumps: " << jumps;

    cout << endl;
    return 0;
}