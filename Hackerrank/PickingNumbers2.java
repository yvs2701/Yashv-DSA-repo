import java.util.Scanner;

/* Given an array of integers, find the longest subarray or multiset (subsequence) where the
absolute difference between any two elements is less than or equal to 1.
Examples-
input:
    6
    4 6 5 3 3 1
output: 3
reason: multiset {4, 3, 3} is the longest sequence.

input:
    6
    1 2 2 3 1 2
output: 5
reason: multiset {1, 2, 2, 1, 2} is the longest sequence. */

public class PickingNumbers2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        sc.close();

        /* O(n^2) approach (Python code):
        maximum=0
        for i in arr:
            a = arr.count(i)
            b = arr.count(i-1)
            a = a+b
            if a > maximum:
                maximum = a
        print(maximum)
        */

        /* O(nlogn) time approach can be- to sort the array and then
        use O(n) approach to find longest SUBARRAY with absolute difference = 1
        */

        /* GIVEN THAT 0 < arr[i] < 100
        We can solve the problem in O(n) time and O(n) space */
        int[] freq = new int[99];
        for (int i : arr)
            freq[i - 1]++; // counting occurences of each element

        int max = 0;
        for (int i = 0; i < 98; i++)
            if (freq[i] + freq[i + 1] > max) // difference between i+1 and i will always be 1
                max = freq[i] + freq[i + 1]; // find the maximum length of subsequence

        System.out.println(max);
    }
}
