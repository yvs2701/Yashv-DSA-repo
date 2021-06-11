/* In daily share trading, a buyer buys shares in the morning and sells them on the same day. 
If the trader is allowed to make at most 2 transactions in a day, whereas the second transaction can only start after the first one is complete. 
Given stock prices throughout the day, find out the maximum profit that a share trader could have made.
Input:   price[] = {10, 22, 5, 75, 65, 80}
Output:  87. Trader earns 87 as sum of 12, 75. Buy at 10, sell at 22, buy at 5 and sell at 80

Input:   price[] = {2, 30, 15, 10, 8, 25, 80}
Output:  100. Trader earns 100 as sum of 28 and 72. Buy at price 2, sell at 30, buy at 8 and sell at 80

Input:   price[] = {100, 30, 15, 10, 8, 25, 80};
Output:  72. Buy at price 8 and sell at 80.

Input:   price[] = {90, 80, 70, 60, 50}
Output:  0. Not possible to earn. */

#include <iostream>
using namespace std;

/* int maxStockProfit(int *arr, int start, int end){ // return profit in stock transactions for given subarray (from start to end)
    if (end - start <= 1) // length of array is 0 or 1
        return 0;         // not possible to make profit

    int buyingPrice = arr[start], profit = 0;
    for (int i = start; i < end; i++)
    {
        buyingPrice = arr[i] < buyingPrice ? arr[i] : buyingPrice;
        profit = arr[i] (i.e selling price) - buyingPrice > profit ? arr[i] - buyingPrice : profit;
    }
    return profit;
} */

int main(){
    int prices[] = {100, 30, 15, 10, 8, 25, 80}, l = sizeof(prices) / sizeof(prices[0]);

    int maxProfit = 0;

    // O(n^2) approach
    /* for (int i = 0; i < l; i++) // split the array in two halves, one transaction is done in each half. Then we can find the max
        maxProfit = max(maxProfit, maxStockProfit(prices, 0, i) + maxStockProfit(prices, i, l));
    cout << maxProfit; */

    // O(n) approach, we buy today and sell tomorrow, if we can gain profit EVERY TIME the price changes, i.e. for each element in array
    // (also called Valley and Peak approach)
    for(int i = 1; i < l; i++)
    {
        if(prices[i] - prices[i-1] > 0)
            maxProfit += prices[i] - prices[i - 1];
    }
    cout << maxProfit;

    cout << endl;
    return 0;
}
/* other approaches
1) Create a table profit[0..n-1] and initialize all values in it 0.
2) Traverse price[] from right to left and update profit[i] such that profit[i] stores maximum profit achievable from one transaction in subarray price[i..n-1]
3) Traverse price[] from left to right and update profit[i] such that profit[i] stores maximum profit such that profit[i] contains maximum achievable profit from two transactions in subarray price[0..i].
4) Return profit[n-1] */