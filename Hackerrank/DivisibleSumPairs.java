import java.util.Scanner;

/* 
 * Given an array of integers and a positive integer k, determine the number of (i,j)
 * pairs where i < j and arr[i] + arr[j] is divisible by k.
 * SAMPLE INPUT:
 *  6 3
 *  1 3 2 6 1 2
 * SAMPLE OUTPUT:
 *  5
 * EXPLAINATION: the pairs are (1,2), (1,2), (3,6), (2,1), (1,2)
 */

public class DivisibleSumPairs {
    public static void main(String[] args) {
        // O(n + k) time complexity solution by dividing the array into buckets of mod k:
        int n, k, count = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        int[] arr = new int[n];
        int[] modk = new int[k];
        // each index of modk represents number of elements having that remainder when divided by k

        // take input and fill modk array
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            modk[arr[i] % k]++;
        }
        sc.close();
        // NOW COUNT ELEMENTS:
        // elements with remainder = 0 can combine with themselves (nC2 = n*(n-1)/2)
        // to add up to a sum which is completely divisible by k
        count += modk[0] * (modk[0] - 1) / 2;

        // elements with remainder = i can combine with elements with remainder = k-i
        // the number of combinations will be:
        // (number of elements with remainder) i * (number of elements with remainder k-i)
        for (int i = 1; i <= k / 2 && i != k - i; i++)
            count += modk[i] * modk[k - i];
        // if modk has even length (k is even)
        if ((k & 1) == 0)
            count += (modk[k / 2] * (modk[k / 2] - 1)) / 2; // again these elements can combine with themselves
        System.out.print(count);
    }
}
