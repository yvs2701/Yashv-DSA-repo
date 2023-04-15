import java.util.Scanner;

public class countCrossword {
    /* NOT ACCOUNTING THE DIAGONALS. HOWEVER, CODE FOR THAT CAN BE EASILY WRITTEN TOO.
    Program takes a cross word and a string as input and returns the number of times string occurs in the crossword 
    row or column in a normal or a reversed fashion */
    public static void main(String[] args) {
        java.util.Scanner sc = new Scanner(System.in);
        char[][] crossword;
        String key;
        int n = sc.nextInt();
        crossword = new char[n][n];

        // optional: configure scanner delimiter pattern to "" so that it reads only single characters
        // since sc.next() reads the next token (or word) and leaves out all the whitespaces
        sc.useDelimiter("\\s+|"); // read strings after either whitespaces OR a null string "" (2 delimiter patterns are separated by a pipe)

        // input characters
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                crossword[i][j] = sc.next().charAt(0); // next returns a string so charAt(0) converts it into char
        // input key to find
        System.out.println("Enter the key to count:");
        sc.nextLine();
        key = sc.nextLine();
        sc.close();

        System.out.println(countMatches(crossword, key));
    }

    private static int countMatches(char[][] crossword, String key) {
        final int l = key.length(), row = crossword.length, col = crossword.length;
        final char start = key.charAt(0), last = key.charAt(l - 1);
        char ch;
        int count = 0;

        // checking row wise
        for (int i = 0; i < row; i++)
        for (int j = 0; j < col; j++) {
        ch = crossword[i][j];
        if (ch == start) { // left to right
            boolean check = true;
            if (l + j - 1 < col)
                for (int k = 1; k < l; k++) {
                    if (crossword[i][j + k] != key.charAt(k))
                        check = false;
                }
            else
                check = false;

            if (check) {
                count++;
                j += l - 1;
            }}
        else if (ch == last) { // right to left
            boolean check = true;
            if (l + j - 1 < col)
                for (int k = 1; k < l; k++) {
                    if (crossword[i][j + k] != key.charAt(l - k - 1))
                        check = false;
                }
            else
                check = false;

            if (check) {
                count++;
                j += l - 1; // l - 1 because when for loop will update j it will increment it by 1 anyways
            }}}
        for (int j = 0; j < col; j++)
        for (int i = 0; i < row; i++) {
        ch = crossword[i][j];
            if (ch == start) { // top to bottom
            boolean check = true;
            if (l + i - 1 < row)
                for (int k = 1; k < l; k++) {
                    if (crossword[i + k][j] != key.charAt(k))
                        check = false;
                }
            else
                check = false;

            if (check) {
                count++;
                i += l - 1;
            }}
            else if (ch == last) { // bottom to top
            boolean check = true;
            if (l + i - 1 < row)
                for (int k = 1; k < l; k++) {
                    if (crossword[i + k][j] != key.charAt(l - k - 1))
                        check = false;
                }
            else
                check = false;

            if (check) {
                count++;
                i += l - 1; // l - 1 because when for loop will update j it will increment it by 1 anyways
            }}
        }
        return count;
    }
}
