#include <iostream>
#include <vector>
using namespace std;

/*
You don't need to read input or print anything. Complete the function factorial() that takes integer N as input parameter and
returns a list of integers denoting the digits that make up the factorial of N.
    Expected Time Complexity : O(N)
    Expected Auxilliary Space : O(1)
Constraints:
    1 ≤ N ≤ 1000
*/

void multiply(int num, int *fac, int l)
{
    int carry = 0, product = 1;
    for (int i = 0; i < l; i++)
    {
        product = num * fac[i];
        product += carry;      // adding carry over from last digit's multiplication
        carry = product / 10;  // getting carry over for the next multiplication
        fac[i] = product % 10; // getting this digit from product
    }
}

vector<int> factorial(int *fac, int len, int N)
{
    if (N == 0)
    {
        vector<int> solution(1);
        solution[0] = 0;
        return solution;
    }
    else if (N < 2) // for 1 and 2
    {
        vector<int> solution(1);
        solution[0] = N;
        return solution;
    }
    for (int i = 1; i <= N; i++)
    {
        multiply(i, fac, len);
    }

    // storing the result in a vector (not necessary)
    int i = len - 1;
    for (; i >= 0; i--)
        if (fac[i] != 0)
            break;
    vector<int> solution;
    while (i >= 0)
        solution.push_back(fac[i--]);

    return solution;
}

int main()
{
    long long n;
    cin >> n;

    int fact[500] = {1};
    vector<int> solution = factorial(fact, sizeof(fact) / sizeof(fact[0]), n);

    for (vector<int>::iterator it = solution.begin(); it != solution.end(); it++)
        cout << *it;
    cout << endl;
    return 0;
}