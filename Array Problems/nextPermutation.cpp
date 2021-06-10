#include <iostream>
using namespace std;
/*Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
The replacement must be in place and use only constant extra memory*/

void reverse(int *start, int *end)
{
    int len = end - start;
    for (int i = 0; i < len / 2; i++)
    {
        *(start + i) = *(start + i) + *(end - 1 - i);
        *(end - 1 - i) = *(start + i) - *(end - 1 - i);
        *(start + i) = *(start + i) - *(end - 1 - i);
    }
}

int main()
{
    int arr[] = {1, 5, 8, 4, 7, 6, 5, 3, 1}; // input number 158476531
    int l = sizeof(arr) / sizeof(arr[0]);
    // solution with O(l) [worst case] complexity
    for (int i = l - 2; i >= 0; i--)
        if (arr[i] < arr[i + 1])
        {
            int justBig = i + 1;
            for (int j = i + 2; j < l; j++)
                if (arr[j] < arr[justBig] && arr[j] > arr[i]) // finding minimum number greater than arr[i]
                    justBig = j;

            // swapping the elements
            arr[i] = arr[justBig] + arr[i];
            arr[justBig] = arr[i] - arr[justBig];
            arr[i] = arr[i] - arr[justBig];

            // arr becomes 1585.76431, now we need to reverse the rest of the array
            // and since even after swapping this part of array is sorted we will get our desired result, i.e. just greater permutation
            reverse(arr + i + 1, arr + l); // arr becomes 1585.13467
            break;                         // i.e. this operation will only be performed once
        }

    cout << "Next Permutation: ";
    for (int i : arr) // output number 158513467
        cout << i;

    cout << endl;
    return 0;
}