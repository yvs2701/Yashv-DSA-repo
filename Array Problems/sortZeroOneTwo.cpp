#include <iostream>
using namespace std;

int *group(int *arr, int len)
{
    int zero, one, two;
    zero = one = two = 0;

    for (int i = 0; i < len; i++)
    {
        if (arr[i] == 0)
            zero++;
        else if (arr[i] == 1)
            one++;
        else
            two++;
    }
    int i = 0, index = 0;
    for (int i = 0; i < zero; i++)
    {
        arr[index++] = 0;
    }
    for (int i = 0; i < one; i++)
    {
        arr[index++] = 1;
    }
    for (int i = 0; i < two; i++)
    {
        arr[index++] = 2;
    }

    return arr;
}

int main()
{
    int array[] = {1, 2, 2, 1, 0, 2, 1};
    int *solution = group(array, sizeof(array) / sizeof(array[0]));

    for (int i = 0; i < (sizeof(array) / sizeof(array[0])); i++)
    {
        cout << solution[i] << " ";
    }
    return 0;
}