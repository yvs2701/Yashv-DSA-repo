import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* Travel and print matrix in clockwise spiral order:
Input: 
    3
    4
    1 2 3 4
    5 6 7 8
    9 10 11 12
Output: [1,2,3,4,8,12,11,10,9,5,6,7] */

class SpiralMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt(), input[][] = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                input[i][j] = sc.nextInt();
        sc.close();

        List<Integer> result = spiralOrder(input);
        System.out.print(result);
    }

    private static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, sr = 0, sc = 0;
        // end row, end column, start row, start column
        
        List<Integer> elements = new LinkedList<>();
        while (sr < m && sc < n) {
            int i = sc, j = sr;

            boolean runs = false;
            for (; j < n; ++j) {
                runs = true;
                elements.add(matrix[i][j]);
            }
            if (runs) {
                --j;
                ++i;
                runs = false;
            } else break;

            for (; i < m; ++i) {
                runs = true;
                elements.add(matrix[i][j]);
            }
            if (runs) {
                --i;
                --j;
                runs = false;
            } else break;

            for (; j >= sc; --j) {
                runs = true;
                elements.add(matrix[i][j]);
            }
            if (runs) {
                ++j;
                --i;
                runs = false;
            } else break;

            for (; i > sr; --i) {
                runs = true;
                elements.add(matrix[i][j]);
            }
            if (runs) {
                ++i;
                ++j;
            } else break;

            sr = i; --m;
            sc = j; --n;
        }

        return elements;
    }
}
