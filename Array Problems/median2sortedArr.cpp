/* There are 2 sorted arrays A and B of size n each. Write an algorithm to find the median of the array obtained after merging the
above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n)). 
The median is the middle number in a sorted, ascending or descending, list of numbers.
If there is an odd amount of numbers, the median value is the number that is in the middle, with the same amount of numbers below and above.
If there is an even amount of numbers in the list, the middle pair must be determined, added together, and divided by two to find the median value.
It can be more descriptive of that data set than the average.
The median is sometimes used as opposed to the mean when there are outliers in the sequence that might skew the average of the values.
*/
#include <iostream>
#include <algorithm>
using namespace std;

void sortedUnion(int *arr1, int l1, int *arr2, int l2)
{
    // time complexity = O((n + m ).log(n + m))
    int ptr1 = 0, ptr2 = 0, last1 = l1 - 1;

    while (ptr1 <= last1 and ptr2 < l2) // O(min(l1, l2)) or O(min(n , m))
    {
        // ptr1 should be <= last1 since after last1 our arr1 is unsorted... after this we just need to sort our individual arrays back
        if (arr1[ptr1] < arr2[ptr2])
            ptr1++;
        else // i.e. arr1[ptr1] >= arr2[ptr2]
            swap(arr2[ptr2++], arr1[last1--]);
    }
    // Sort first array
    sort(arr1, arr1 + l1); // O(len1.log(len1))
    // Sort second array
    sort(arr2, arr2 + l2); // O(len2.log(len2))
}

int calcMedian(int *arr1, int l1, int *arr2, int l2)
{
    // before calculating median we need to sort arr1 + arr2 (they maybe sorted, but we need to sort them as if they were one big array)
    // so first make Union array:
    sortedUnion(arr1, l1, arr2, l2);
    // now let's calculate median
    if ((l1 + l2) & 1) // if l1 + l2 is odd
    {
        int middleIndex = (l1 + l2 + 1) / 2;
        middleIndex--; // since array index starts from 0
        if (middleIndex >= l1)
            return arr2[middleIndex - l1];
        // else
        return arr1[middleIndex];
    }
    // else l1 + l2 is even
    int mid1 = ((l1 + l2) / 2) - 1 /*since array index starts from 0*/, mid2 = mid1 + 1;
    if (mid1 < l1 && mid2 < l1)
        return ((arr1[mid1] + arr1[mid2]) / 2);
    else if (mid1 < l1)
        return ((arr1[mid1] + arr2[mid2 - l1]) / 2);
    // else // mid1 and mid2 > l1 (mid2 > mid1 so any other case can't arise)
    return ((arr2[mid1 - l1] + arr2[mid2 - l1]) / 2);
}

int main()
{
    int arr1[] = {-5, 3, 6, 12, 15}, arr2[] = {-12, -10, -6, -3, 4, 10};
    int len1 = sizeof(arr1) / sizeof(arr1[0]), len2 = sizeof(arr2) / sizeof(arr2[0]);
    return 0;
}

/* Another method: O(n + m). 
To merge the both arrays, keep two indices i and j. Compare the ith index of 1st array and jth index of second, 
increase the index of the smallest element and increase the count. ***DOESN'T MODIFY ANY OF THE ARRAYS***
int getMedian(int ar1[], int ar2[], int n, int m)
{
    int i = 0; // Current index of input array ar1[]
    int j = 0; // Current index of input array ar2[]
    int count;
    int med1 = -1, med2 = -1;
    if((m + n) % 2 == 1) 
    {
        for (count = 0; count <= (n + m)/2; count++)
        {
            if(i != n && j != m)
            {
                med1 = (ar1[i] > ar2[j]) ? ar2[j++] : ar1[i++];
            }
            else if(i < n)
            {
                med1 = ar1[i++];
            }
            else // j < m
            {
                med1 = ar2[j++];
            }
        }
        return m1;
    }
    else
    {
        for (count = 0; count <= (n + m)/2; count++)
        {
            med2 = med1;
            if(i != n && j != m)
            {
                med1 = (ar1[i] > ar2[j]) ? ar2[j++] : ar1[i++];
            }
            else if(i < n)
            {
                med1 = ar1[i++];
            }
            else // j < m
            {
                med1 = ar2[j++];
            }
        }
        return (med1 + med2)/2;
    }
} */