package string_problems;

import java.util.Scanner;

class searchWordInGrid {
    /* Given a 2D grid of characters and a word, find all occurrences of the given word in the grid. 
    A word can be matched in all 8 directions at any point. Word is said to be found in a direction if all characters match 
    in this direction (not in zig-zag form).
    The 8 directions are, Horizontally Left, Horizontally Right, Vertically Up, Vertically Down and 4 Diagonal directions.
    Input:  grid[][] = {"GEEKSFORGEEKS",
                        "GEEKSQUIZGEEK",
                        "IDEQAPRACTICE"};
            word = "EEE"
    Output: pattern found at 0, 2
            pattern found at 0, 10
            pattern found at 2, 2
            pattern found at 2, 12 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row, col;
        row = sc.nextInt();
        col = sc.nextInt();
        sc.useDelimiter("\\s+|"); // read strings after either whitespaces OR a null string "" (2 delimiter patterns are separated by a pipe)
        char[][] grid = new char[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                grid[i][j] = sc.next().charAt(0);
        // right now the reading cursor is still in this line since next() doesn't throw the reading cursor to the next line
        sc.nextLine(); // to bring the reading cursor to the next line where user will enter his word
        String word = sc.nextLine();
        sc.close();
        findWordInGrid(grid, word);
    }

    private static void findWordInGrid(char[][] grid, String word) {
        final int l = word.length(), row = grid.length, col = grid[0].length;
        final char starting_char = word.charAt(0);
        if (l == 1) {
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    if (starting_char == grid[i][j])
                        System.out.println("Found at " + i + ", " + j);
        }
        else {
            char ch; // stores the i,j th character in the grid
            // int row_updater = 0, col_updater = 0; // will increment the value of indices in the required direction
            for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ch = grid[i][j];
                if (ch == starting_char) { // now find in which direction is the next matching character lying (if any)
                    if (i-1 >= 0 && j-1 >= 0 && word.charAt(1) == grid[i-1][j-1]) { // upper left diagonal
                        // row_updater = col_updater = -1;
                        if (searchInDirection(grid, word, i, j, -1, -1))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (i-1 >= 0 && j+1 < col && word.charAt(1) == grid[i-1][j+1]) { // upper right diagonal
                        // row_updater = -1; col_updater = 1;
                        if (searchInDirection(grid, word, i, j, -1, 1))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (i+1 < row && j-1 >= 0 && word.charAt(1) == grid[i+1][j-1]) { // lower left diagonal
                        // row_updater = 1; col_updater = -1;
                        if (searchInDirection(grid, word, i, j, 1, -1))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (i+1 < row && j+1 < col && word.charAt(1) == grid[i+1][j+1]) { // lower right diagonal
                        // row_updater = col_updater = 1;
                        if (searchInDirection(grid, word, i, j, 1, 1))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (i - 1 >= 0) { // vertically up
                        // row_updater = -1; col_updater = 0;
                        if (searchInDirection(grid, word, i, j, -1, 0))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (i + 1 < row) { // vertically down
                        // row_updater = 1; col_updater = 0;
                        if (searchInDirection(grid, word, i, j, 1, 0))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (j - 1 >= 0) { // horizontally left
                        // col_updater = 1; row_updater = 0;
                        if (searchInDirection(grid, word, i, j, 0, 1))
                            System.out.println("Found at " + i + ", " + j);
                    }
                    if (j + 1 < col) { // horizontally right
                        // col_updater = 1; row_updater = 0;
                        if (searchInDirection(grid, word, i, j, 0, 1))
                            System.out.println("Found at " + i + ", " + j);
                    }
                }
            }
        }}
    }

    private static boolean searchInDirection(char[][]grid, String word, int curr_row, int curr_col, int row_direction, int col_direction) {
        final int row = grid.length, col = grid[0].length, l = word.length();
        int index = 1; // start finding from index 1 (2nd character)

        curr_row += row_direction;
        curr_col += col_direction;

        while(index < l && curr_row < row && curr_col < col && curr_row >= 0 && curr_col >= 0) {
            if (word.charAt(index) != grid[curr_row][curr_col])
                return false;
            curr_row += row_direction;
            curr_col += col_direction;
            index++;
        }
        // if we arrive here that means all the character till now has matched
        if (index == l) // if all the characters have matched
            return true;
        return false;
    }
}