import java.util.Scanner;

class wordWrapOptimized {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n; // size of array
        n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        int k = sc.nextInt(); // character limit for each line
        sc.close();

        solveWordWrap(nums, n, k);
    }

    // TIME COMPLEXITY = O(n^2), SPACE COMPLEXITY = O(n)

    static void solveWordWrap(int arr[], int n, int k) {
        int i, j; 
        int currlen; // Variable to store number of characters in given line.
        
        int cost; // Variable to store possible minimum cost of line.

        int dp[] = new int[n]; // DP table in which dp[i] represents cost of line starting with word arr[i].

        // Array in which ans[i] store index of last word in line starting with word arr[i].
        int ans[] = new int[n];

        // If only one word is present then only one line is required. Cost of last line is zero. 
        // Hence cost of this line is zero. Ending point is also n-1 as single word is present.
        dp[n - 1] = 0;
        ans[n - 1] = n - 1;

        // Make each word first word of line by iterating over each index in arr.
        for (i = n - 2; i >= 0; i--) {
            currlen = -1;
            dp[i] = Integer.MAX_VALUE;

            // Keep on adding words in current line by iterating from starting word upto last word in arr.
            for (j = i; j < n; j++) {

                // Update number of characters in current line. arr[j] is number of characters in current word 
                // and 1 represents space character between two words.
                currlen += (arr[j] + 1);

                // If limit of characters is violated then no more words can be added to current line.
                if (currlen > k)
                    break;

                // If current word that is added to line is last word of arr then current line is last line. Cost of last line is 0.
                // Else cost is square of extra spaces plus cost of putting line breaks in rest of words from j+1 to n-1.
                if (j == n - 1)
                    cost = 0;
                else
                    cost = (k - currlen) * (k - currlen) + dp[j + 1];

                // Check if this arrangement gives minimum cost for line starting with word arr[i].
                if (cost < dp[i]) {
                    dp[i] = cost;
                    ans[i] = j;
                }
            }
        }

        // Print starting index and ending index of words present in each line.
        i = 0;
        int lineIndex = 1;
        while (i < n) {
            System.out.println("Line no. " + lineIndex++ + ": " + "Word " + (i + 1) + " to " + (ans[i] + 1));
            i = ans[i] + 1;
        }
    }
}