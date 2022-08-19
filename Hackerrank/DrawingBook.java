import java.util.Scanner;

/* A teacher asks the class to open their books to a page number.
A student can either start turning pages from the front of the book or from the back of the book.
They always turn pages one at a time. When they open the book, page 1 is always on the right side.
When they flip page 1, they see pages 2 and 3. Each page except the last page will always
be printed on both sides. The last page may only be printed on the front,
given the length of the book. If the book is 'n' pages long, and a student wants to turn to page 'p'
find and print the minimum number of pages that must be turned in order to arrive at page 'p'.

EXAMPLE 1:
    INPUT: 6 2
    OUTPUT: 1
    EXPLANATION:
    from the front page 1 -> page 2 and 3 (1 turn)
    from the back page 6 -> page 5 and 4 -> page 3 and 2 (2 turns)
EXAMPLE 2:
    INPUT: 7 4
    OUTPUT: 1
    EXPLANATION:
    from the front page 1 -> page 2 and 3 -> page 4 and 5 (2 turns)
    from the back page 7 and 6 -> page 5 and 4 (1 turn)
*/

class DrawingBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        sc.close();
        System.out.println(pageCount1(n, p));
    }

    private static int pageCount1(int n, int p) {
        // Time complexity = O(1)
        if ((n & 1) == 1)
            return Math.min(p/2, (n - p)/2);
        // else
        return Math.min(p/2, (n - p + 1)/2);
    }

    private static int pageCount2(int n, int p) {
        // Time complexity = O(n/2) = O(n)
        int turns = 0, start, updater;
        if ((n & 1) == 1) {
            // odd number of total pages
            if (p <= (n - 1) / 2) {
                // closer to first page than the last
                start = 1;
                updater = 2; // if p <= current page STOP else keep turning
            } else {
                // closer to the last page
                start = n - 1; // second last page is on the left side
                updater = -2; // if p >= current page STOP else keep turning
            }

        } else {
            // even number of total pages
            if (p <= n / 2) {
                // closer to first page than the last
                start = 1;
                updater = 2; // if p <= current page STOP else keep turning
            } else {
                // closer to the last page
                start = n; // last page itself is on the left side
                updater = -2; // if p >= current page STOP else keep turning
            }
        }

        if (updater == 2) for (int i = start; i <= n && p > i; i += updater) turns++;
        else for (int i = start; i <= n && p < i; i += updater) turns++;
        return turns;
    }
}
