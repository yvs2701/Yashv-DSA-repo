#include <iostream>
using namespace std;
/* Given an array of integers. Find the Inversion Count in the array. 
Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. 
If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j 
    Input: N = 5, arr[] = {2, 4, 1, 3, 5}
    Output: 3
Explanation: The sequence 2, 4, 1, 3, 5 
has three inversions (2, 1), (4, 1), (4, 3) */

int main(){
    int arr[] = {2, 4, 1, 3, 5};
    int l = sizeof(arr) / sizeof(arr[0]);

    // simple O(n^2) approach, space complexity is O(1) -> this algo runs for = n-1 n-2 n-3.....1 = (n-1)(n-2)/2 = order of n^2
    int inversions = 0;
    for (int i = 0; i < l; i++)
        for (int j = i + 1; j < l; j++)
            if (arr[i] > arr[j])
                inversions++;
    cout << "Number of inversions are: " << inversions << "\n";

    /*I WAS NOT ABLE TO OPTIMISE IT FURTHER HOWEVER IT CAN BE DONE USING MERGE SORT, where you count every time you merge and invert numbers
    i tried to initialise a global count and withing the merge function everytime i merged two elements such that i had to invert them into
    ascending then i incremented count... but it didn't work, neither was i able to come up with something else*/
    return 0;
}