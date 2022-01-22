/* Chef has N 6-sided standard dice. Each die has dimensions 1×1×1. Chef  decides to stack dice for fun.
First, Chef forms four vertical stacks of dice (not necessarily with the same height; empty stacks are allowed)
on his table, which together make up a pile of dice with base area UP TO 2×2.
Among all such structures, the total visible surface area of Chef's structure must be the smallest possible.

Then, Chef calculates the number of pips on the visible faces of all dice in the structure.
A face of a die is visible if it does not touch the table or another die.
Now, he is wondering: among all possible arrangements of dice, what is the maximum possible total number of visible pips? */
#include <iostream>
using namespace std;

int main () {
    int visiblePips;
    int n; cin >> n; // how many dice chef has ?

    if (n == 1)
        visiblePips = 20; // 6 + 5 + 4 + 3 + 2; (1 will be the base hence not visible)
    else if (n == 2)
        visiblePips = 36; // 6 + 5 + 4 + 3; (1 & 2 will not be visible)
    else if (n == 3)
        visiblePips = 51;
    else if (n == 4)
        visiblePips = 60;
    else {
        int stackHeight = n / 4; 
        n = n % 4; // number of dice in topmost level (= 0 if 4)
        /* each fully covered layer has only 6 and 5 as outward (visible pips): visible pips for this layer = 4*(6 + 5) = 44
        a level that has one of the dice not covered by another layer on top: visible pips for this layer = 44 + 4 = 48
        a level that has two die not covered by another layer on top: visible pips for this layer = 44 + 4 + 4 = 52
        a level that has only one die covered by a dice on top: visible pips for this layer = 44 + 4 + 4 + 4 = 56 */

        if (n == 3) // second layer from top has one die face visible
            visiblePips = (stackHeight - 1)*44 + 48 + 51;
        else if (n == 2) // second layer from top has one die face visible
            visiblePips = (stackHeight - 1)*44 + 52 + 36;
        else if (n == 1) // second layer from top has one die face visible
            visiblePips = (stackHeight - 1)*44 + 56 + 20;
    }
    cout << visiblePips << endl;
}