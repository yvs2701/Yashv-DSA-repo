#include <iostream>
using namespace std;
/* You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0. 
    Input: prices = [7,1,5,3,6,4]
    Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
----------
    Input: prices = [7,6,4,3,1]
    Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
*/

int stockProfit(int arr[], int l)
{
    int profit = 0, buyPrice = arr[0];
    for (int i = 0; i < l; i++)
    {
        buyPrice = buyPrice > arr[i] ? arr[i] : buyPrice;
        profit = profit < arr[i] - buyPrice /*(selling price)*/ ? arr[i] - buyPrice : profit;
    }
    return profit;
}

int main()
{
    int prices[] = {7, 1, 5, 3, 6, 4}, len = sizeof(prices) / sizeof(prices[0]);

    cout << stockProfit(prices, len);

    cout << endl;
    return 0;
}

/*just a quick fact I learned before starting with this program - arr and &arr may point to the same address but
arr+1 and &arr+1 will poin to different.
WHY? - arr is pointing at the first element of the array and arr + 1 shifts it to second,
&arr is pointer to an array containing n elements and &arr+1 shifts it to n+1th memory index*/