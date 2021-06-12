#include <iostream>
#include <algorithm>
using namespace std;

/* Given an array A[ ] of positive integers of size N, where each value represents the number of chocolates in a packet.
Each packet can have a variable number of chocolates. There are M students, the task is to distribute chocolate packets among M students
such that :
1. Each student gets exactly one packet.
2. The difference between maximum number of chocolates given to a student and minimum number of chocolates given to a student is minimum.
Input:
    N = 7, M = 3
    A = {7, 3, 2, 4, 9, 12, 56}
Output: 2. Explanation: The minimum difference between maximum chocolates and minimum chocolates is 4 - 2 = 2
by choosing following M packets : {3, 2, 4}. */

int main()
{
    int N, M; cin >> N >> M;
    int packets[N];
    for (int i = 0; i < N; i++) cin >> packets[i];

    sort(packets, packets + N);
    // now lets use calculate min and max difference in a gap of m
    int minDiff = INT32_MAX, /* INT64_MAX will overflow (it is in long int's range), INT_MAX isn't supported in Ubuntu for some reason */
    minDiff_index;
    for (int i = 0; i <= N - M; i++)
        if(packets[i + M - 1] - packets[i] < minDiff)
        {
            minDiff = packets[i + M - 1] - packets[i];
            minDiff_index = i;
        }

    cout << "Minimum difference is " << minDiff << "\nBy picking the packets { ";
    for(int i = minDiff_index; i < minDiff_index + M; i++)
        cout << packets[i] << " ";

    cout << "}" << endl;
    return 0;
}