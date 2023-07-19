/* Leetcode 435: "Non-overlapping Intervals" = "Maximum number of meetings a person can be present in"
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of
the intervals non-overlapping.

Example 1:
  Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
  Output: 1
  Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
  Input: intervals = [[1,2],[1,2],[1,2]]
  Output: 2
  Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
  Input: intervals = [[1,2],[2,3]]
  Output: 0
  Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
*/

import java.util.Arrays;

class MinimumIntervalsRemove {
    public static void main(String[] args) {
        int[][] intervals = { { -52, 31 }, { -73, -26 }, { 82, 97 }, { -65, -11 }, { -62, -49 }, { 95, 99 }, { 58, 95 },
                { -31, 49 }, { 66, 98 }, { -63, 2 }, { 30, 47 }, { -40, -26 } };
        System.out.println(eraseOverlapIntervals(intervals));
    }

    /* Minimum number of intervals to remove to get non overlapping set
       = Maximum number of intervals to keep to get non overlapping set
    -> Similar to = Maximum number of meetings one can attend */
    static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prev = 0, // last interval which was kept
                count = 1; // intervals to keep (minimum = 1)

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }

        return n - count; // minimum number of intervals to remove
    }

    /* Algorithm: Remove bigger intervals in case of overlaps:
        - Sort by length of intervals.
        - Create an array to store all the numbers between lowest and highest bound.
        - Lap the numbers.
        - If an overlap is found count it.
    -> FAILS because:
        - Example: [[1, 5], [4, 6], [5, 10]]
        - Here [4,6] is the smaller interval, but it can be removed to form non
          overlapping set with minimum number of removals.
    */
    // static int eraseOverlapIntervals(int[][] intervals) {
    //     // sort by the size of intervals
    //     Arrays.sort(intervals, (int1, int2) -> (int1[1] - int1[0]) - (int2[1] - int2[0]));

    //     // logs
    //     for (int[] interval : intervals) {
    //         System.out.print("[" + interval[0] + "," + interval[1] + "] ");
    //     }
    //     System.out.println();

    //     // create array to lap the numbers
    //     int minStart = intervals[0][0], maxEnd = intervals[0][1];
    //     for (int i = 1; i < intervals.length; ++i) {
    //         if (intervals[i][0] < minStart)
    //             minStart = intervals[i][0];
    //         if (intervals[i][1] > maxEnd)
    //             maxEnd = intervals[i][1];
    //     }

    //     System.out.println(minStart + " <-> " + maxEnd); // logs

    //     int count = 0,
    //         n = maxEnd - minStart + 1;

    //     System.out.println("Length: " + n); // logs

    //     boolean[] included = new boolean[n];

    //     // start lapping the numbers
    //     for (int[] interval : intervals) {
    //         int start = interval[0] - minStart,
    //             end = interval[1] - minStart;

    //         // logs
    //         System.out.print(interval[0] + "," + interval[1] + " : " + start + "," + end);

    //         int idx = start;
    //         while (idx < end) { // runs till end i.e., excludes upper bound
    //             if (included[idx]) {
    //                 // FOUND AN OVERLAP
    //                 count++;

    //                 System.out.print(" (Removing)"); // logs

    //                 int i = idx - 1;
    //                 while (i-->start) {
    //                     included[idx] = false;
    //                 }
    //                 idx = end - 1;
    //                 break;
    //             }
    //             else {
    //                 // LAP THIS NUMBER
    //                 included[idx] = true;
    //             }
    //             idx++;
    //         }
    //         System.out.println(); // logs
    //     }

    //     return count;
    // }
}