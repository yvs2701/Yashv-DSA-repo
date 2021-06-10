#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
// find number of elements in union and intersection of two arrays
template <typename container, typename val>
bool isDuplicate(container iterable, val elem) // to check if the element is already present
{
    for (auto i : iterable)
        if (i == elem)
            return true;
    return false;
}

int Union(int *a, int l1, int *b, int l2)
{
    vector<int> UniqueArr;

    for (int i = 0; i < l1; i++)
        if (!isDuplicate(UniqueArr, a[i]))
            UniqueArr.push_back(a[i]);
    for (int i = 0; i < l2; i++)
        if (!isDuplicate(UniqueArr, b[i]))
            UniqueArr.push_back(b[i]);

    sort(UniqueArr.begin(), UniqueArr.end());
    for (auto i : UniqueArr) // printing the union
        cout << i << " ";

    return UniqueArr.size(); // return the number of elements in this union
}

int Intersection(int *a, int l1, int *b, int l2)
{
    vector<int> intersect;
    int i = 0, j = 0;

    if (l1 < l2)
    {
        for (int i = 0; i < l1; i++)
            for (int j = 0; j < l2; j++)
                if (a[i] == b[j] && !isDuplicate(intersect, b[j]))
                    intersect.push_back(a[i]);
    }
    else
    {
        for (int i = 0; i < l2; i++)
            for (int j = 0; j < l1; j++)
                if (b[i] == a[j] && !isDuplicate(intersect, a[j]))
                    intersect.push_back(b[i]);
    }

    sort(intersect.begin(), intersect.end());
    for (auto i : intersect) // printing the intersection
        cout << i << " ";

    return intersect.size(); // return the number of elements in this intersection
}

int main()
{
    int a[] = {1, 2, 3, 4, 5, 6, 7, 8};
    int b[] = {0, 2, 4, 6, 8, 10};

    int un = Union(a, 8, b, 6);
    cout << endl;
    int in = Intersection(a, 8, b, 6);
    cout << endl;

    cout << "Union is: " << un << "\n";
    cout << "Intersection is: " << in;

    return 0;
}