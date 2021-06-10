/*from leetCode:
Given an array of integers nums containing n + 1 integers where each integer is in the **range [1, n] inclusive**
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.*/
#include <iostream>
using namespace std;

int main()
{
    int N;
    cin >> N; // size of the array
    int arr[N + 1];
    for (int i = 0; i < N + 1; i++)
        cin >> arr[i];

    /*  Brute force
    for (int i = 0; i < N + 1; i++)
        for(int j = i; j < N + 1;j++)
            if(arr[j] == arr[i])
            {
                cout << arr[i];
                break;
            }*/

    /* Using hashing/frequency array
    int freq[N + 1] = {0};

    for (int i = 0; i < N + 1; i++)
    {
        if (freq[arr[i]] > 1) // we already know elements will be in range [1,N], 
        // otherwise we would first find maximum element then make an array with length = max element
        {
            cout << arr[i];
            break;
        }
        freq[arr[i]]++;
    }
    to optimize it more what we can do is use the same array as our freq array...
    now we will add the length of the array to each element at index of value of previous element
    ** to access the original value we can simply use updatedValue % length **
    ** to access the frequency we can simply use updatedValue / length **
    loop(1 to n)
        a[a[i]%n]+= n; // again we know index wont go out of bound since in the question each elem is in the range [1,N]
    
    // printing results
    loop(1 to N)
        if(a[i]/n > 1) cout << i;
    src = https://youtu.be/kV37sZV9pgk
    */

    // Cycle detection (this works the way it does because of the condition that each element of array is in range [1,N])
    // Using FLOYD'S (or hare and tortoise) algorithm
    int fast = arr[arr[0]];
    int slow = arr[0];
    // we did this since we know arr[0] is in range [1,N] otherwise we would start from 0th index and
    // fast would increment twice and slow only once
    while (true)
    {
        if (fast == slow)
            break;
        // else
        fast = arr[arr[fast]]; // elements will be in range [1,N]
        slow = arr[slow];      // elements will be in range [1,N]
    }
    // now we detected a cycle let's find which element was duplicate
    fast = 0; // fast goes back to the 0th index
    // and now both will move with same speed
    while (slow != fast)
    {
        slow = arr[slow]; // elements will be in range [1,N]
        fast = arr[fast]; // elements will be in range [1,N]
    }
    cout << slow; // or cout << fast;

    cout << endl;
    return 0;
}