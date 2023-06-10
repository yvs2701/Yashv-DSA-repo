import java.util.Scanner;

class palindromicSubsequences {
    /* Given a string str of length N, you have to find number of palindromic subsequence (need not necessarily be distinct) which could be formed from the string str.
    Note: You have to return the answer module 10^9+7;
    Input:
    Str = "abcd"
        Output: 4. Explanation: palindromic subsequence are : "a" ,"b", "c" ,"d"
    Input: 
    Str = "aab"
        Output: 4. Explanation: palindromic subsequence are :"a", "a", "b", "aa"
    Expected Time Complexity: O(N*N)
    Expected Auxiliary Space: O(N*N) */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        System.out.println(countPalindromicSubsequences(input));
    }

    private static long countPalindromicSubsequences(String str) {
        int n = str.length();
 
        // create a 2D array to store the 'C'ount of 'P'alindromic 'S'ubsequence
        int[][] cps = new int[n][n];

        // palindromic subsequence of length 1 (string of one character itself)
        for (int i = 0; i < n; i++)
            cps[i][i] = 1;
 
        // check subsequence of length L is palindrome or not
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                int k = L + i - 1;
                if (str.charAt(i) == str.charAt(k)) {
                    cps[i][k] = cps[i][k - 1] + cps[i + 1][k] + 1;
                }
                else {
                    cps[i][k] = cps[i][k - 1] + cps[i + 1][k] - cps[i + 1][k - 1];
                }
            }
        }
 
        // return total palindromic subsequence
        return cps[0][n - 1];
    }
    /* Time Complexity = O(2^n.n), Space Complexity = O(2^n)
    private static long countPalindromicSubsequences(String str) {
        final long mod = 1000000007;
        long count = 0;

        ArrayList<String> subsequences = new ArrayList<String>();
        subsequences = getsubsequences(str, subsequences);
        for (String s : subsequences) {
            int l = s.length();
            boolean check = true;
            for (int i = 0; i < l; i++)
            if (s.charAt(i) != s.charAt(l-i-1)) {
                check = false;
                break;
            }
            if (check) {
                count++;
                // count = count % mod;
            }
        }
        return count % mod;
    }

    private static ArrayList<String> getsubsequences(String str, ArrayList<String> combinations) {
        if (str.length() == 1) {
            combinations.add(str);
            return combinations;
        } // base case

        ArrayList<String> newCombinations = new ArrayList<String>(combinations.size());
        newCombinations.addAll(getsubsequences(str.substring(1), combinations)); // recursive call

        combinations.add(String.valueOf(str.charAt(0))); // add this character in the combinations

        for (String i : newCombinations)
            combinations.add(str.charAt(0) + i); // add combinations starting from this string

        return combinations;
    } */
}