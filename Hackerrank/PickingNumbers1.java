import java.util.Scanner;

/* Given an array of integers, find the longest subarray where the absolute difference
between any two elements is less than or equal to 1 */

public class PickingNumbers1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        sc.close();
        
        int i = 0, j = 1, max = 0;
        char prevDiff = '=';
        while (i < n && j < n) {
            if (arr[j] - arr[j-1] == 1 && prevDiff != '+') {
                j++;
                prevDiff = '+';
            } else if (arr[j-1] - arr[j] == 1 && prevDiff != '-') {
                j++;
                prevDiff = '-';
            } else if (arr[j] - arr[j-1] == 0) {
                j++;
            }
            else {
                i = j;
                j++;
                prevDiff = '=';
            }
            
            if (j-i > max)
                max = j-i;
        }
        System.out.println(max);
    }
}
