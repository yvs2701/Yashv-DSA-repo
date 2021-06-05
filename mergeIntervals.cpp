#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/*merge the overlapping intervals in the following manner:
input: [1,3], [2,6], [8,10], [15,18]
output: [1,6], [8,10], [15,18]
*/

vector<pair<int, int>> mergeIntervals(vector<pair<int, int>> intervals)
{
    // ensure the intervals have increasing starting points
    sort(intervals.begin(), intervals.end(), [&](pair<int, int> a, pair<int, int> b){ return a.first < b.first; });
    vector<pair<int, int>> merged;

    for (int i = 0; i < intervals.size(); i++)
    {
        int start = intervals[i].first,
            end = intervals[i].second;

        for (int j = i + 1; j < intervals.size(); j++)
        {
            if (end > intervals[j].first)
            {
                end = end > intervals[j].second ? end : intervals[j].second;
                i++; // we have merged jth interval so we need to skip it
                // (we know only the immediate interval to i (i.e j = i+1th) will be merged so simple i++ is enough)
            }
            else
                break; // since we have sorted the interval array so need to check for elements beyond this
        }
        merged.push_back(make_pair(start, end));
    }

    return merged;
}

int main()
{
    cout << "How many intervals to contain ? ";
    int n;
    cin >> n;
    vector<pair<int, int>> intervals(n);
    cout << "Enter the intervals:\n";
    for (int i = 0; i < n; i++)
        cin >> intervals[i].first >> intervals[i].second;

    vector<pair<int, int>> solution = mergeIntervals(intervals);
    for (auto i : solution)
        cout << "[" << i.first << ", " << i.second << "] ";

    cout << endl;
    return 0;
}