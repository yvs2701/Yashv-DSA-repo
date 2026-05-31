import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h3><a href="https://leetcode.com/problems/merge-intervals/description/">LeetCode 56. Merge Intervals</a></h3>
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1: <br/>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]] <br/>
 * Output: [[1,6],[8,10],[15,18]] <br/>
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6]. <br/>
 * Example 2: <br/>
 * Input: intervals = [[1,4],[4,5]] <br/>
 * Output: [[1,5]] <br/>
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping. <br/>
 * Example 3: <br/>
 * Input: intervals = [[4,7],[1,4]] <br/>
 * Output: [[1,7]] <br/>
 * Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 * <p>
 * Constraints: <br/>
 * 1 <= intervals.length <= 10^4 <br/>
 * intervals[i].length == 2 <br/>
 * 0 <= start_i <= end_i <= 10^4 <br/>
 */
public class MergeIntervals {
    /**
     * Sort all intervals by their "start" or "opening"
     * then check if an interval's "end" or "closing" is greater than
     * or beyond the "start" or "opening" of the next interval
     * and modify the intervals if they overlap.
     * This implementation uses Java Streams for practice. But a simpler and more readable solution would
     * be to use a simple a for loop and iterate over the sorted intervals to check if they overlap and create
     * the result array list.
     */
    public static int[][] merge(int[][] intervals) {
        return Arrays
                .stream(intervals)
                .sorted(Comparator.comparingInt(interval -> interval[0]))
                .<ArrayList<int[]>>collect(ArrayList::new, (list, interval) -> {
                    int lastIdx = list.size() - 1;
                    int[] lastInterval = list.isEmpty() ? null : list.get(lastIdx);

                    if (list.isEmpty() || lastInterval[1] < interval[0]) {
                        list.add(interval);
                    } else {
                        lastInterval[1] = Math.max(lastInterval[1], interval[1]);
                    }
                }, List::addAll)
                .toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][][] testcases = {
                {{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                {{1, 4}, {4, 5}},
                {{4, 7}, {1, 4}}
        };

        for (int[][] testcase : testcases) {
            System.out.println("Input: " + Arrays.deepToString(testcase));
            System.out.println("Output: " + Arrays.deepToString(merge(testcase)));
        }
    }
}
