#include<iostream>
using namespace std;

int main(){
    int arr[10] = {0,1,2,3,4,5,6,7,8,9};
    int l = sizeof(arr)/sizeof(arr[0]);
    int temp;

    for (int i=0 ; i<=l/2 ; i++) // swap elements (looping till the mid)
    {
        temp = arr[i];
        arr[i] = arr[l-i-1];
        arr[l-i-1] = temp;
    }

    for(int i=0 ; i<l ; i++)
        cout << arr[i] << " ";
    return 0;
}