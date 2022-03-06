/* You have N stones having integer weights given in array W.
You put the stones into two buckets such that stones having a weight greater than or equal to K are placed in the first bucket and others are placed in the second bucket.
Find the total number of possible values of K such that the number of stones in each bucket is the same.
Input Format:
    The first line contains an integer, N, denoting the number of elements in W.
    Each line i of the N subsequent lines (where 0 ≤ i < N) contains an integer describing W[i].
Constraints:
    1 <= N <= 10^5
    1 <= W[i] <= 10^9
Sample:
    Input - 4 6 2 6 3 
    Output - 3
    K = {4,5,6}

    Input - 4 2 3 6 5 
    Output - 2
    K = {4,5}

    Input - 2 2 2
    Output - 0
    No such K exists
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class DivideWeight_Infosys {
    private static int numberOfK(int N, ArrayList<Integer> W) {
        Collections.sort(W); // merge sort in NlogN complexity
        
        // K will contain elements in the range of median weights:
        return W.get((N + 1) / 2) - W.get((N - 1) / 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine().trim());
        ArrayList<Integer> W = new ArrayList<>(N);
        for (int j = 0; j < N; j++) {
            W.add(Integer.parseInt(sc.nextLine().trim()));
        }
        sc.close();
        System.out.println(numberOfK(N, W));
    }
}