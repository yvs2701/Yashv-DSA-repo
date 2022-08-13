import java.util.Scanner;

/* You are choreographing a circus show with various animals.
For one act, you are given two kangaroos on a number line ready to jump in
the positive direction (i.e, toward positive infinity).
The first kangaroo starts at location x1 and moves at a rate of v1 meters per jump.
The second kangaroo starts at location x2 and moves at a rate of v2 meters per jump.
You have to figure out a way to get both kangaroos at the same location at the same time
as part of the show. If it is possible, return YES, otherwise return NO. */

public class NumberLineJumps {
    private static String kangaroo(int x1, int v1, int x2, int v2) {
        /* while (!((x1 > x2 && v1 >= v2) || (x2 > x1 && v2 >= v1))) {
            x1 += v1;
            x2 += v2;
            if (x1 == x2)
                return "YES";
        }
        return "NO"; */
        

        /* SECOND APPROACH:
        distance = speed * time
        d - x1 = v1*t
        d = x1 + v1 *t
        similarly, d = x2 + v2*t
        x1 + v1*t = x2 + v2*t
        t = (x1 - x2)/(v2 - v1) */

        /* double t = (double)(x1 - x2) / (v2 - v1);
        now check if d is an integer or not
        x and v are both integers so if t is an integer (division remainder = 0)
        d is also and integer */
        if ((x1 > x2 && v1 >= v2) || (x2 > x1 && v2 >= v1))
            return "NO";
        if ((x1 - x2) % (v2 - v1) == 0)
            return "YES";
        // else
        return "NO";
        
    }

    public static void main(String[] args) {
        int x1, x2, v1, v2;
        Scanner sc = new Scanner(System.in);
        x1 = sc.nextInt();
        v1 = sc.nextInt();
        x2 = sc.nextInt();
        v2 = sc.nextInt();
        sc.close();
        System.out.println(kangaroo(x1, v1, x2, v2));
    }
}
