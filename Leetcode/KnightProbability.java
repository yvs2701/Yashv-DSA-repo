/* Leetcode 688: "Knight Probability in Chessboard"
On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed,
so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
The knight continues moving until it has made exactly k moves or has moved off the chessboard.
Return the probability that the knight remains on the board after it has stopped moving.

Example 1:
    Input: n = 3, k = 2, row = 0, column = 0
    Output: 0.06250
    Explanation: There are two moves (to (1, 2), (2, 1)) that will keep the knight on the board. From each of those positions, there are also two moves
    that will keep the knight on the board. The total probability the knight stays on the board is 0.0625.
Example 2:
    Input: n = 1, k = 0, row = 0, column = 0
    Output: 1.00000
Example 3:
    Input: n = 8, k = 30, row = 6, column = 4
    Output: 0.00019
*/

import java.util.Arrays;

class KnightProbability {
    double[][][] dp;
    int[] xmove, ymove;

    public static void main(String[] args) {
        KnightProbability obj = new KnightProbability();
        int n = 8, k = 30, row = 6, column = 4;

        obj.dp = new double[n][n][k + 1];
        obj.xmove = new int[] { -2, -2, -1, -1, 1, 1, 2, 2 };
        obj.ymove = new int[] { -1, 1, -2, 2, -2, 2, -1, 1 };
        System.out.println(obj.knightProbability(n, k, row, column));
    }

    private double knightProbability(int n, int k, int row, int column) {
        dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1.0);
            }
        }

        double favourableOutcome = solve(row, column, n, k);
        double totalOutcome = Math.pow(8, k);
        return favourableOutcome / totalOutcome;
    }

    private double solve(int row, int col, int n, int k) {
        if (row < 0 || col < 0 || row >= n || col >= n)
            return 0;
        if (k == 0)
            return 1;
        if (dp[row][col][k] != -1.0)
            return dp[row][col][k];

        double ans = 0;

        for (int i = 0; i < 8; i++) {
            ans += solve(row + xmove[i], col + ymove[i], n, k - 1);
        }

        dp[row][col][k] = ans;
        return ans;
    }
}
