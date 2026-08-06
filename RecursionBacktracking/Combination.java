import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3><a href="https://leetcode.com/problems/combinations/description/">
 * LeetCode 77: Combinations
 * </a></h3>
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * You may return the answer in any order.
 * </p>
 * <p>
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: n = 4, k = 2 <br>
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to
 * be the same combination.
 * </p>
 * <p>
 * Example 2: <br>
 * Input: n = 1, k = 1 <br>
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= n &lt;= 20</li>
 * <li>1 &lt;= k &lt;= n</li>
 * </ul>
 * </p>
 */
class Combinations {
    /* public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        if (k > n || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        // Add current number in the combinations
        result = combine(n - 1, k - 1);
        for (List<Integer> list : result) {
            list.add(n); // add current element to the returned list
        }

        // Do not add current number in the combinations
        result.addAll(combine(n - 1, k));

        // return all the possible combinations
        return result;
    } */

    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        // Recurse and Backtrack using depth first search
        dfs(combinations, new ArrayList<Integer>(), 1, n, k);
        return combinations;
    }

    private static void dfs(final List<List<Integer>> combinations, final List<Integer> combination, final int start, final int n, final int k) {
        // logs:
        // System.out.printf("n: %d, k: %d, start: %d, combination: %s\n combinations: %s\n\n", n, k, start,
        //        combination, combinations);

        if (k == 0) {
            combinations.add(new ArrayList<Integer>(combination));
        } else {
            final int limit = n - k + 1;
            for (int i = start; i <= limit; i++) {
                // "choose i"
                combination.add(i);

                // recurse to find more numbers for this combination
                dfs(combinations, combination, i + 1, n, k - 1);

                // backtrack "don't choose i"
                combination.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[][] testcases = {
                {4, 2},
                {1, 1},
                {5, 3}
        };

        for (int[] testcase : testcases) {
            System.out.println("Input: " + Arrays.toString(testcase));
            System.out.println("Output: " + combine(testcase[0], testcase[1]));
            System.out.println("---");
        }
    }
}