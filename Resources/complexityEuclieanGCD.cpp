#include <iostream>
using namespace std;

int main()
{
    int m, n; 
    cout << "m = "; cin >> m;
    cout << "and n = "; cin >> n;

    int steps = 0; // just to count the number of times loop's body executes

    // iterative euclidean gcd algorithm:
    while (m != n)
    {
        // printing to see the steps
        cout << "m = " << m << " and n = " << n << "\n";
        steps++; // counting number of times loop's body executes
        
        // part of euclidian algorithm:
        if (m > n)
            m -= n;
        else
            n -= m;
    }
    cout << "m = " << m << " and n = " << n << " " << "Number of steps: " << steps << "\n";
    return 0;
}

/* Time complexity analysis: 
we can clearly see if m is a mutliple of n (assuming m > n) then loop runs for M/N times
however if m is not a multiple of n, then number of times loop executes is Integer_Part(M/N) + N - 1 times (this result was derived observing
various inputs and there outputs and then I drew the conclusion)

Integer_part(M/N) or floor(M/N) can be written as: 
*/