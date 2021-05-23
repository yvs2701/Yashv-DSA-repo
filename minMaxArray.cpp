#include<iostream>
using namespace std;

// pair is a keyword use Pair
struct Pair{
    int first,second;
};

Pair minmax(int* arr, int l){
    int min, max; 
    min = max = arr[0];

    for(int index = 1; index < l ; index++){
        if(arr[index] > max)
            max = arr[index];
        else if(arr[index] < min)
            min = arr[index];
    }

    /* answer[0] = min; answer[1] = max; segmentation fault (accessing a memory not belonging to us) */ 
    Pair answer;
    answer.first = min;
    answer.second = max;

    return answer;
}

int main(){
    int arr[7] = {2,1,4,6,3,5,7};
    Pair result = minmax(arr, 7);

    int max = result.second;
    int min = result.first;
    
    cout << "Minimum : " << min << ", Maximum : " << max << "\n";
    return 0;
}