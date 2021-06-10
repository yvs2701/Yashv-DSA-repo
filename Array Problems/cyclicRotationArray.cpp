#include <iostream>
#include <string>
using namespace std;

// one way to rotate is obviously extracting one element from last,
// incrementing index of all other elemnents, and reinserting the last element at first.

template<typename type>
type* push_front(type* arr, type elem ,int len){

    type temp1 = elem;
    type temp2;

    for (int i = 0; i < len; i++)
    {
        temp2 = arr[i];
        arr[i] = temp1;
        temp1 = temp2;
    }
    return arr;
}

template<typename t>
t* rotateArray(t* arr, int rotateBy, int len){
    for(int i = 0; i < rotateBy ; i++)
        push_front(arr, arr[len-1], len);
    return arr;
}

int main()
{
    int arr[] = {1, 2, 3, 4, 5};
    int rotateBy = 1;

    int *solution = rotateArray(arr, rotateBy, 5);

    for(int i = 0 ; i < 5 ; i++)
        cout << solution[i] << " ";
    cout << endl;
    return 0;
}