import java.util.Scanner;

/* A jail has a number of prisoners and a number of treats to pass out to them.
Their jailer decides the fairest way to divide the treats is to seat the prisoners around
a circular table in sequentially numbered chairs. A chair number will be chosen at random.
Beginning with the prisoner in that chair, one candy will be handed to each prisoner sequentially
around the table until all have been distributed.
The jailer is playing a little joke, though. The last piece of candy looks like all the others,
but it tastes awful. Determine the chair number occupied by the prisoner who will receive that candy.

INPUT: Contains n (number of prisoners), m (number of sweets), s (seat to start distribution from)
OUTPUT: the prisoner who will recieve the awful candy.
Example:
    input:  7 19 2
    output: 6
*/

class SaveThePrisoner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt();
        sc.close();

        // Remainders start from 0 not 1
        // (Remainder, for dividing by n, can be anything in the range [0, n-1])
        // but seat numbers start from 1 hence offset the seats by decerementing by 1
        // (offset = 1 - 0 = 1)
        s--;

        // if we start from first seat we will end at = m % n
        // but we also need to offset the m, by the starting position
        // hence we will end at (m + s) % n if we start from seat s
        int lastSeat = (m + s) % n;

        // we had offset everything to match with the remainders [0, n-1] range
        // here 0 = n (since the seats are arranged in circular fashion)
        // if we offset it back to the range [1, n]:
        if (lastSeat == 0)
            lastSeat = n;

        // print the solution
        System.out.println(lastSeat);
    }
}
