// group negative element to one side of the array
#include<iostream>
#include<vector>
using namespace std;

int* groupNegElem(int* arr, int len){
    vector<int> negative, positive;

    for (int i = 0; i < len; i++)
    {
        if(arr[i] < 0)
            negative.push_back(arr[i]);
        else
            positive.push_back(arr[i]);
    }

    int index = 0;

    for (int i = 0; i < negative.size(); i++)
        arr[index++] = negative[i];
    for (int i = 0; i < positive.size(); i++)
        arr[index++] = positive[i];

    return arr;   
}

int main(){
    int arr[10] = {1,2,3,-1,-5,-3,4,-4,5,-2};
    int *solution = groupNegElem(arr,10);

    for (int i = 0; i < 10; i++)
        cout << solution[i] << " ";
    
    return 0;
}