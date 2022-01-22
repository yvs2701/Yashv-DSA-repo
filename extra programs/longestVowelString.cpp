#include <iostream>
#include <string>
using namespace std;

int main()
{
    int l, substrlen1, substrlen2, len;
    string s;
    cin >> s;
    l = s.length();
    substrlen1 = substrlen2 = len = 0;
    for (int i = 0; i < l; i++)
    {
        if (s[i] != 'a' && s[i] != 'e' && s[i] != 'i' && s[i] != 'o' && s[i] != 'u')
        {
            len++;
        }
        else
        {
            if (substrlen1 == 0)
                substrlen1 = len;
            if (substrlen2 == 0)
                substrlen2 = len;
            len = 0;
        }
    }
    if (substrlen1 == 0 && substrlen2 == 0 && len != 0)
        cout << 0 << "\n";
    else
        cout << l - (substrlen1 + substrlen2) - 1 << "\n";
    return 0;
}