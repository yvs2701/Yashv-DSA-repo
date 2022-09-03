import java.util.Scanner;

/* Given a string, remove characters until the string is made up of any two alternating characters.
When you choose a character to remove, all instances of that character must be removed.
Determine the longest string possible that contains just two alternating letters.

Sample Input: beabeefeab
Sample output: 5
"babab" is the string with alternating characters and maximum length. */

class AlternatingCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        System.out.println(new AlternatingCharacters().alternate(str));
    }

    private int alternate(String s) {
        int n = s.length(), len, max = 0;
        char prev, c;

        // char1:
        for (char c1 = 'a'; c1 <= 'z'; c1++)
            // char2:
            for (char c2 = (char) (c1 + 1); c2 <= 'z'; c2++) {
                len = 0;
                prev = ' ';
                for (int i = 0; i < n; i++) {
                    c = s.charAt(i);
                    if (c != prev && c == c1) {
                        len++;
                        prev = c1;
                    } else if (c != prev && c == c2) {
                        len++;
                        prev = c2;
                    }
                    /* this part is faulty logic because
                    of the first else if block: else if (c == prev && c == c1)
                    Such a condition can easily evaluate to true even when,
                    c != c2 and we skipped that partciular character and never stored it in prev. */
                    /*
                    else if (c == prev && c == c1) // if c1 occurs twice in a row in s
                        // continue searching from next c1
                        continue char1;
                    else if (c == prev && c == c2) // if c2 occurs twice in a row in s
                        // contnue searching from next c2
                        continue char2;
                    */
                    else if (c == prev) {
                        len = 0; // so that we dont update max by mistake
                        break;
                    }
                }
                if (len > max)
                    max = len;
            }
        return max != 1 ? max : 0; // because string must be exactly 2 alternating characters
    }
}
