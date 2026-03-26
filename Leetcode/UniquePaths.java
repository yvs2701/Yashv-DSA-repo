/**
 * LeetCode 62: "Unique Paths"
 * <p>
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 * <p>
 * <br> Example 1: <br>
 * Input: m = 3, n = 7 <br>
 * Output: 28
 * <br> Example 2: <br>
 * Input: m = 3, n = 2 <br>
 * Output: 3 <br>
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner: <br>
 * 1. Right -> Down -> Down <br>
 * 2. Down -> Down -> Right <br>
 * 3. Down -> Right -> Down
 * <p>
 * Constraints:
 * 1 <= m, n <= 100
 */
public class UniquePaths {
    /**
     * <p>
     * If the Robot is standing at (0,0) grid and it has to reach (m,n)
     * then the robot needs to take 'm' steps downwards and 'n' steps right. <br>
     * Total steps taken = (m - 1) + (n - 1) = m + n - 2 <br>
     * Now, we know robot can take (m-1) Down steps and (n-1) Right steps. <br>
     * Which mean total paths the robot can follow <br>
     * = C(m + n - 2, m - 1) or C(m + n - 2, n - 1) <br>
     * = (m + n - 2)! / ( (m - 1)! * (n - 1)! ) <br>
     * After simplifying the fraction (dividing by (m-1)!) <br>
     * The numerator has exactly (n-1) terms, matching (n-1)! in the denominator <br>
     * = ((m+n-2) * (m+n-3) * ... (m+n-i+1) ...) / (1 * 2 * ... i ... (n-1)) <br>
     * <p>
     * Note that the paths D1 R1 D2 R2 R3 R4 and D2 R3 D1 R1 R4 R2 are
     * the same! Since ordering of 'D's and 'R's was irrelevant we need
     * to divide the number of ways 'D's and 'R's can be ordered
     * amongst themselves from the total Permutations.
     */
    public static int findUniquePaths(int m, int n) {
        final int bigger = Math.max(m, n) - 1;
        final int smaller = Math.min(m, n) - 1;
        final int total = bigger + smaller;

        long uniquePaths = 1;
        for (int i = 1; i <= smaller; i++) {
            // uniquePaths *= (total - i + 1) / i; // wrong answer due to operator precedence and decimal point data loss when storing as `int`
            uniquePaths = uniquePaths * (total - i + 1) / i;
        }

        return (int) uniquePaths;
    }

    static int[][] gridMemo;

    public static int findUniquePathsUsingDFS(int m, int n) {
        gridMemo = new int[m][n];

        // Robot can only move down or right
        // so when robot reaches bottom-most row or rightmost column
        // then it only has one possible path to travel to the finish point
        for (int i = 0; i < n; ++i)
            gridMemo[m - 1][i] = 1;
        for (int i = 0; i < m; ++i)
            gridMemo[i][n - 1] = 1;

        return dfs(0, 0);
    }

    private static int dfs(int x, int y) {
        if (gridMemo[x][y] != 0)
            return gridMemo[x][y];

        // total possible paths at this square
        // = total possible paths on the right square + on the bottom square
        gridMemo[x][y] = dfs(x, y + 1) + dfs(x + 1, y);
        return gridMemo[x][y];
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {3, 7},
                {3, 2},
                {3, 3},
                {1, 1}
        };

        for (int[] testCase : testCases) {
            int m = testCase[0];
            int n = testCase[1];
            System.out.printf("Unique paths for the grid of size (%d,%d) = %d\n", m, n,
                    findUniquePaths(m, n));
        }
    }
}
