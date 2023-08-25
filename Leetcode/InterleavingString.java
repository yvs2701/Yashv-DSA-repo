/* Leetcode 97: "Interleaving String"
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
An interleaving of two strings s and t is a configuration where s and t are divided into n and m
substrings respectively, such that:
    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b
Example 1:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    Output: true
    Explanation: One way to obtain s3 is:
        Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
        Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
        Since s3 can be obtained by interleaving s1 and s2, we return true.
Example 2:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    Output: false
    Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:
    Input: s1 = "", s2 = "", s3 = ""
    Output: true
*/

class InterleavingString {
    public static void main(String[] args) {
        String s1 = "ababa",
                s2 = "bababa",
                s3 = "bababababaa";
        System.out.println(new InterleavingString().isInterleave(s1, s2, s3));
    }

    String s1 = "", s2 = "", s3 = "";
    int n1 = 0, n2 = 0, n3 = 0;
    boolean visited[][];

    public boolean isInterleave(String s1, String s2, String s3) {
        this.n1 = s1.length(); this.n2 = s2.length(); this.n3 = s3.length();
        if (this.n3 != this.n1 + this.n2)
            return false;

        this.s1 = s1; this.s2 = s2; this.s3 = s3;
        this.visited = new boolean[n1 + 1][n2 + 1];
        return dfs(0, 0, 0);
    }

    boolean dfs(int it1, int it2, int it3) {
        if (it3 == n3) {
            return true;
        }

        if (this.visited[it1][it2]) { // already checked for this state
            return false;
        }
        visited[it1][it2] = true;

        while (it3 < n3 && it1 < n1 && it2 < n2) {
            char c1 = s1.charAt(it1), c2 = s2.charAt(it2), c3 = s3.charAt(it3);

            if (c1 == c2 && c2 == c3) {
                return !dfs(it1 + 1, it2, it3 + 1) ? dfs(it1, it2 + 1, it3 + 1) : true;
            } else if (c3 == c1) {
                it1++;
            } else if (c3 == c2) {
                it2++;
            } else {
                return false;
            }
            it3++;
        }

        while (it3 < n3 && it1 < n1) {
            char c1 = s1.charAt(it1), c3 = s3.charAt(it3);
            if (c3 == c1) {
                it1++;
                it3++;
            } else {
                return false;
            }
        }

        while (it3 < n3 && it2 < n2) {
            char c2 = s2.charAt(it2), c3 = s3.charAt(it3);
            if (c3 == c2) {
                it2++;
                it3++;
            } else {
                return false;
            }
        }

        return true;
    }
}