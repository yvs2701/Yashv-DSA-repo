#include<iostream>
#include<algorithm>
using namespace std;

struct Pair{
    int frst, scnd;
};

Pair kthMaxMin(int k, int* arr, size_t len){
    sort(arr, arr+len-1); // now our arr becomes {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}

    Pair pair;
    pair.frst = arr[k-1];
    pair.scnd = arr[len - k]; // k should be >=1
    return pair;
}

int main(){
    int arr[10] = {1,2,5,3,4,8,0,6,7,9};
    Pair answer = kthMaxMin(3, arr, 10);

    cout << "3rd min: " << answer.frst << ", 3rd max: " << answer.scnd <<"\n";
    return 0;
}

/*
When you pass an array into a function in C, the array decays into a pointer to its first element. 
When you use sizeof on the parameter, you are taking the size of the pointer, not the array itself.
If you need the function to know the size of the array, you should pass it as a separate parameter.
*/