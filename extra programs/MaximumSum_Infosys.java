/* You are given a grid of N rows and M (where N*M <= 10^5). Choose a subset of elements from the grid
containing exactly one element from each row following this condition:
Let the sequence S represent the element you choose from each row, Where S[i] is equal to the column index of the
chosen element from the ith row. Also s[i] != s[i+1] i.e. You cannot chose the same column twice in adjacent rows.
Find a valid selection ef elements in which the sum of chosen elements is maximum.

Input Format:
    The first line contains an integer, N, denoting the number of rows in Grid.
    The next line contains an integer, M, denoting the number of columns in Grid.
    Each line i of the N subsequent lines (where 0 <= i <= N) contains M space separated integers each describing the row Grid[i]
Example:
    10 3
    10 4 9
    2 6 9
    9 6 8
    9 6 8
    5 7 3
    4 2 9
    8 5 10
    3 4 1
    4 8 3
    3 8 2
Output - 76
The chosen elements are 10 9 9 8 7 9 8 4 4 8 */
import java.util.*;
import static java.util.stream.Collectors.toList;

class MaximumSum_Infosys {
    private static int getMaxSum(int N, int M, List<List<Integer>> Grid) {
        // but this code will fail in certain cases which may need backtracking solution
        int max, col = -1, sum = 0;
        for (int i = 0; i < N; i++) {
            max = col != 0 ? 0 : 1;
            for (int j = 0; j < M; j++) {
                if (j != col && Grid.get(i).get(j) >= Grid.get(i).get(max)) {
                    max = j;
                }
            }
            sum += Grid.get(i).get(max);
            System.out.println(Grid.get(i).get(max) + " - " + max);
            col = max;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    
        int N = Integer.parseInt(scan.nextLine().trim());
        
        int M = Integer.parseInt(scan.nextLine().trim());
        
        List<List<Integer>> Grid = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            Grid.add(
                Arrays.asList(scan.nextLine().trim().split(" "))
                .stream().map(s -> Integer.parseInt(s)).collect(toList())
            );
        }
    
        scan.close();
        System.out.println(getMaxSum(N, M, Grid));
    }
}
