#include <iostream>
using namespace std;

int main(){
    int arr[] = {-1, -2, -3, -4, -7, -6};
    int len = sizeof(arr)/sizeof(arr[0]);

    int p1 = 0, p2 = 0;

    // using kadane's algorithm (with improvement)
    int max = arr[0];
    int max_tillNow = arr[0];
    int negMax = arr[0]; 
    // to store maximum element, this will be used if all elements are negative 
    // (sum of neg number will be lesser than both, so max of neg number = maximum negative number)

    for (int i = 1; i < len; i++)
    {
        max_tillNow += arr[i];
        if(max_tillNow > max)
            max = max_tillNow;
        if(max_tillNow < 0)
            max_tillNow = 0; // that's why kadane's algo is only valid for an array with atleast one positive number

        // our optimisation of stroing the maximum number
        if(arr[i] > negMax)
            negMax = arr[i];
    }

    if(max == 0)
        max = negMax; // greatest of all numbers

    cout << "Maximum sum is: " << max << endl;
    return 0;
}