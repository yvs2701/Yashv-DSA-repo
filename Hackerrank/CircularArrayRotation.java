import java.util.Scanner;

/* John Watson knows of an operation called a right circular rotation on an array of integers.
One rotation operation moves the last array element to the first position and shifts all remaining
elements right one. To test Sherlock's abilities, Watson provides Sherlock with an array of integers.
Sherlock is to perform the rotation operation a number of times then determine the value of the element
at a given position.
For each array, perform a number of right circular rotations and return the values of the elements
at the given indices. 
Example:
Input-
    arr[3]    = 1 2 3
    right rotations = 2
    query[2] = 1 2
Output-
    3
    1
*/

class CircularArrayRotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /* Inputs: */
        int n = sc.nextInt(), rot = sc.nextInt(), q = sc.nextInt();
        int[] arr = new int[n];
        int[] queries = new int[q];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        for (int i = 0; i < q; i++)
            queries[i] = sc.nextInt();
        sc.close();

        /* Logic: */
        // number of left roations (lRot) = length of circle - number of right roations (rRot)
        // also if rRot == n or rRot == 0 then the array is unchanged i.e. rRot = lRot = 0
        // and if rRot > n, do rRot = rRot % n, now rRot < n and we dont have to calculae a large value
        // as rotating n times = rotating 0 times
        rot = n - (rot % n);
        
        // for unrotated array (length L), index = i means: i % L = i (since i < l)
        // for an array left rotated R times, index = i means: (R + i) % L
        for (int i : queries) {
            System.out.println(arr[(rot + i) % n]);
        }
    }
}
