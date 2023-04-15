import java.util.HashSet;

class sudoku {
    private static int[][] board = {
            { 7, 0, 0, 0, 0, 0, 2, 0, 0 },
            { 4, 0, 2, 0, 0, 0, 0, 0, 3 },
            { 0, 0, 0, 2, 0, 1, 0, 0, 0 },
            { 3, 0, 0, 1, 8, 0, 0, 9, 7 },
            { 0, 0, 9, 0, 7, 0, 6, 0, 0 },
            { 6, 5, 0, 0, 3, 2, 0, 0, 1 },
            { 0, 0, 0, 4, 0, 9, 0, 0, 0 },
            { 5, 0, 0, 0, 0, 0, 1, 0, 6 },
            { 0, 0, 6, 0, 0, 0, 0, 0, 8 }
    };

    public static void main(String[] args) {
        boolean canSolve = solver(0, 0);

        if (canSolve)
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j)
                    System.out.print(board[i][j] + " ");
                    System.out.println();
                }
        else 
            System.out.println("Cannot solve!");
    }

    private static HashSet<Integer> canPlace(int row, int col) {
        HashSet<Integer> placeable = new HashSet<>(9);

        for (int i = 1; i < 10; ++i)
            placeable.add(i);


        for (int i = 0; i < 9; ++i) {
            if (board[i][col] != 0 && placeable.contains(board[i][col]))
                placeable.remove(board[i][col]);
        }

        for (int i = 0; i < 9; ++i) {
            if (board[row][i] != 0 && placeable.contains(board[row][i]))
                placeable.remove(board[row][i]);
        }


        int grstart = row - (row % 3);
        int gcstart = col - (col % 3);

        for (int i = grstart; i < grstart + 2; ++i)
            for (int j = gcstart; j < gcstart + 2; ++j)
                if ((i != row || j != col) && board[i][j] != 0 && placeable.contains(board[i][j]))
                    placeable.remove(board[i][j]);

        return placeable;
    }

    private static boolean solver(int row, int col) {

        if (col == 9 && row != 9)
            return solver(row + 1, 0);

        if (row == 9)
            return true;


        if (board[row][col] != 0)
            return solver(row, col + 1);

        HashSet<Integer> placeable = canPlace(row, col);
        if (placeable.isEmpty())
            return false;

        for (int num : placeable) {
            board[row][col] = num;

            if (solver(row, col + 1))
                return true;

            board[row][col] = 0;
        }

        return false;
    }
}
