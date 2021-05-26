#include <iostream>
using namespace std;

// how many items can a bag hold (with limit of W weight) if the items have values = {60, 100, 150} and weights = {10, 30, 20}. Let W = 50
// solution = value of 160 (and the wight of 30).
int max(int a, int b)
{
    return (a > b ? a : b);
}

int knapsack(int W, int *wt, int *val, int l, int i = 0)
{
    if (l < 0 || W <= 0) // i.e. weight and value are empty OR the knapsack is filled/capacity was < 0
        return 0;
    if (i == l) // when have traversed the array entirely
        return 0;

    if (W >= wt[i])
    {
        int excl = knapsack(W, wt, val, l, i + 1);                  // if the item is not included
        int incl = knapsack(W - wt[i], wt, val, l, i + 1) + val[i]; // if the item is included in knapsack
        return max(excl, incl);
    }
    // else
    return knapsack(W, wt, val, l, i + 1); // then we anyways can't include this
}

int main()
{
    int W = 50;
    int val[] = {60, 100, 120};
    int wt[] = {10, 20, 30};
    int l = sizeof(wt) / sizeof(wt[0]);

    int solution = knapsack(W, wt, val, l);
    cout << "Max value the knapsack could hold was: " << solution << endl;
    return 0;
}