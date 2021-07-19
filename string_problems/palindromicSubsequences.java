package string_problems;

import java.util.ArrayList;
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

    
    // Time Complexity = O(2^n.n), Space Complexity = O(2^n)
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
    }
}