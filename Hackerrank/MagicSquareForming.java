import java.util.Scanner;

/* You will be given a 3x3 matrix of integers in the inclusive range [1,9].
We can convert any digit (a) to any other digit (b) in the range [1,9] at cost of |a-b|.
Convert the input matrix into a magic square at minimal cost. Print this cost. */

class MagicSquareForming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] mat = new int[3][3];

        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                mat[x][y] = sc.nextInt();
        sc.close();

        // all 8 possible magic squares
        int[][][] possible = {{{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                                {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                                {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                                {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                                {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                                {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                                {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},
                                {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}}};


        /* Find which possible magic square is closest to input matrix */
        // since 1<= mat[i][j] <= 9, max difference between a single element of
        // any matrix = 9 - 1 = 8. Max difference between any two matrices = 8*9 = 72
        int min = 72;
        for (int i = 0; i < 8; i++) {
            int diff = 0;

            // for each possibility calculate difference
            // (how much different the matrix is from the possibile magic square)
            for (int x = 0; x < 3; x++)
                for (int y = 0; y < 3; y++)
                    diff += Math.abs(possible[i][x][y] - mat[x][y]);

            if (diff < min)
                min = diff;
        }
        System.out.println(min);
    }
}

/* How to calculate all possible 3x3 magic squares ?
The 8 valid combinations of 3 numbers that add to 15 are:
* 9 5 1
* 7 5 3
* 2 5 8
* 4 5 6
* 2 9 4
* 6 1 8
* 6 7 2
* 8 3 4
All 8 of those combinations need to appear in the square as a row, column or diagonal. The centre cell must appear in the middle row, middle column and both diagonals. So it must be a number that appears four times, and the only digit that does is 5. SO 5 MUST BE IN THE CENTER.

Similarly, each of the corner pieces must form part of a row, a column and a diagonal. So each corner cell must be a number that appears 3 times. Those are the even numbers 2, 4, 6 and 8. That means the DIAGONALS MUST BE "2 5 8", "4 5 6".
That leaves the middle edge cells, each of which needs to appear in a row and a column. These are the odd numbers 1, 3, 7 and 9. So the MIDDLE ROW AND COLUMN MUST BE "9 5 1" and "7 5 3".

a possible matrix:
2 9 4
7 5 3
6 1 8

take its mirror images (horizontally and vertically) for other such possible matrices:
4 9 2 | 2 9 4
3 5 7 | 7 5 3
8 1 6 | 6 1 8
-------------
8 1 6 | 6 1 8
3 5 7 | 7 5 3
4 9 2 | 2 9 4

and rotate these matrices once to get 4 more possibilites:
8 3 4 | 4 3 8
1 5 9 | 9 5 1
6 7 2 | 2 7 6
-------------
6 7 2 | 2 7 6
1 5 9 | 9 5 1
8 3 4 | 4 3 8
*/
