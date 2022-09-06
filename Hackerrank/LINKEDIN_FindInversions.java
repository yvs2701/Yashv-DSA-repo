import java.util.Scanner;

/* You are given an array. It is said to have an inversion if any 3 elements in that array follow
these conditions:
    - The 3 elements have indices i, j, k in increasing order, i.e. i < j < k
    - The 3 elements must be in decreasing order: arr[i] > arr[j] > arr[k]
Find the number of inversions in an array.

Example input: {4, 5, 3, 2, 6}
Example output: 2
inversions in this array - {4, 3, 2}, {5, 3, 2}

Example input: {4, 6, 3, 2, 1}
Example output: 7
inversions in this array - {4, 3, 2}, {4, 3, 1}, {4, 2, 1}, {6, 3, 2}, {6, 3, 1}, {6, 2, 1}, {3, 2, 1}

Example input: {4, 6, 5, 3, 2}
Example output: 5
inversions in this array - {4, 3, 2}, {6, 5, 3}, {6, 5, 2}, {6, 3, 2}, {5, 3, 2} */

class LINKEDIN_FindInversions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        sc.close();

        int count = 0;

        /* O(n^2) algorithm: By counting number of smaller elements to the right of current element */
        int[] smaller = new int[n];
        // loop runs till (n-3)th element because question asks for inversion in 3 elements
        // (so we need to have 3 elements atleast that's smaller than the current, with a greater index)
        for (int i = 0; i < n - 2; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] > arr[j])
                    smaller[i]++;
        
        // pick any 2 smaller elements than the current element to make an inversion
        // any 2 out of k elements = kC2 = k! / 2! * (k-2)! = k * (k-1) / 2
        for (int i = 0; i < n; i++)
            count += (smaller[i] * (smaller[i] - 1))/2;

        System.out.println(count);
        /* Naive solution O(n^3), which I submitted as my answer: */
        // for (int i = 0; i < n - 2; i++)
        //     for (int j = i + 1; j < n - 1; j++)
        //         if (arr[j] < arr[i])
        //         for (int k = j + 1; k < n; k++)
        //             if (arr[k] < arr[j])
        //             count++;
        // System.out.println(count);
    }
}
