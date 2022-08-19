import java.util.Scanner;
import java.util.Arrays;

/* A person wants to determine the most expensive computer keyboard and USB drive that can
be purchased with a give budget. Given price lists for keyboards and USB drives and a budget,
find the cost to buy them. If it is not possible to buy both items, return -1.
EXAMPLE:
    INPUT: first line contains budget, n, m. Then keyboard and drive costs are given.
        60 3 3
        50 60 40
        5 8 12
    OUTPUT:
        58
        50 + 8 is the maximum cost here which is in our budget.
*/

public class MaxCost {
    public static void main(String[] args) {
        // Time complexity: O(N.logN), N = min(n, m)
        int b, n, m;
        Scanner sc = new Scanner(System.in);
        b = sc.nextInt();
        n = sc.nextInt();
        m = sc.nextInt();
        int[] keyboard = new int[n], drive = new int[m];
        for (int i = 0; i < n; i++)
            keyboard[i] = sc.nextInt();
        for (int i = 0; i < m; i++)
            drive[i] = sc.nextInt();
        sc.close();

        Arrays.sort(keyboard); // O(n logn)
        Arrays.sort(drive); // O(m logm)

        int i = n-1, j = 0; // 2 pointers, one for each array
        int max = -1, sum;
        while (i >= 0 && j < m) {
            sum = keyboard[i] + drive[j];
            if (sum <= b) {
                max = (sum > max) ? sum : max;
                j++;
            }
            else if (sum > b) i--;
        }
        System.out.println(max);
    }
}
