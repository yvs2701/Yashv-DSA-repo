import java.util.Scanner;

public class countStringIn2dArray {
    /* Given a 2-Dimensional character array and a string, we need to find the given string in 2-dimensional character array such that individual characters can be present left to right, right to left, top to down or down to top.
    Input : a ={
                {D,D,D,G,D,D},
                {B,B,D,E,B,S},
                {B,S,K,E,B,K},
                {D,D,D,D,D,E},
                {D,D,D,D,D,E},
                {D,D,D,D,D,G}
            }
            str= "GEEKS"
            Explaination:  {{.,.,.,G,.,.},
                            {.,.,.,E,.,S},
                            {.,S,K,E,.,K},
                            {.,.,.,.,.,E},
                            {.,.,.,.,.,E},
                            {.,.,.,.,.,G}}
    Output: 2
    Input : a = {
                {B,B,M,B,B,B},
                {C,B,A,B,B,B},
                {I,B,G,B,B,B},
                {G,B,I,B,B,B},
                {A,B,C,B,B,B},
                {M,C,I,G,A,M}
                }
            str= "MAGIC"
    Output: 4 */
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
        final int row = crossword.length, col = crossword.length;
        int count = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                count += findByNeighbour(crossword, i, j, key);
        return count;
    }

    private static int findByNeighbour(char[][] crossword, int curr_row_index, int curr_col_index, String key) {
        int count = 0;
        if (crossword[curr_row_index][curr_col_index] == key.charAt(0)) { 
            // i.e. we will find the starting character and then search for rest of the characters
            boolean[][] visited = new boolean[crossword.length][crossword[0].length]; // by default all elements are false i.e. we haven't visited any index
            visited[curr_row_index][curr_col_index] = true;       // we have visited this node so next recursion steps must remember not to visit here again
            count += findByNeighbour(crossword, curr_row_index + 1, curr_col_index, key, 1, visited); // top to bottom search
            count += findByNeighbour(crossword, curr_row_index - 1, curr_col_index, key, 1, visited); // bottom to top search
            count += findByNeighbour(crossword, curr_row_index, curr_col_index + 1, key, 1, visited); // left to right search
            count += findByNeighbour(crossword, curr_row_index, curr_col_index - 1, key, 1, visited); // right to left search
        }
        return count;
    }

    private static int findByNeighbour(char[][] crossword, int row_index, int col_index, String key, int index, boolean[][] visited) {
        int count = 0;
        // base case
        /* i.e. the whole string from 0 to key.length() - 1 is found and end up on this recursion loop. SO RETURN 1 (we have matched the string once)
        if (index == key.length()) 
            return 1;
        this caused logical error since at the last character we still had 3 directions to go and count incremented thrice for everytime
        we found the complete key, this logic has been restated at line 90 */
        if (index == key.length() || row_index == crossword.length || col_index == crossword[0].length || row_index == -1 || col_index == -1)
            return count; // 0
        if (index == key.length() - 1 && crossword[row_index][col_index] == key.charAt(index))
            return 1;

        visited[row_index][col_index] = true; // we have visited this node so next recursion steps must not to visit here again
        
        if (crossword[row_index][col_index] == key.charAt(index)) {
            // search the unvisited neighbours
            if (row_index + 1 < crossword.length && !visited[row_index + 1][col_index])
                count += findByNeighbour(crossword, row_index + 1, col_index, key, index + 1, visited); // top to bottom search
            if (row_index - 1 >= 0 && !visited[row_index - 1][col_index])
                count += findByNeighbour(crossword, row_index - 1, col_index, key, index + 1, visited); // bottom to top search
            if (col_index + 1 < crossword[0].length && !visited[row_index][col_index + 1])
                count += findByNeighbour(crossword, row_index, col_index + 1, key, index + 1, visited); // left to right search
            if (col_index - 1 >= 0 && !visited[row_index][col_index - 1])
                count += findByNeighbour(crossword, row_index, col_index - 1, key, index + 1, visited); // right to left search
        }
        return count;
    }
}
