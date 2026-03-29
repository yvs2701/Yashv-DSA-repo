import java.util.HashMap;
import java.util.Map;

/**
 * <h3>Boyer-Moore Algorithm</h3>
 * <p>
 * <b>Core Idea</b> <br>
 * Boyer-Moore searches for a pattern P in text T by scanning the pattern right-to-left while sliding it left-to-right
 * across the text. The power comes from two heuristics that tell you how far to skip forward when a mismatch occurs,
 * you apply whichever gives the larger skip.
 * </p>
 * <p>
 * <b>Heuristic 1 - Bad Character (BC)</b>
 * <ul>
 * <li> When a mismatch occurs at pattern position i, look at the mismatched text character c. </li>
 * <li> If c appears somewhere in the pattern (to the left of i), align the rightmost such occurrence with c in
 * the text. </li>
 * <li> If c doesn't appear in the pattern at all, skip the entire pattern past c. </li>
 * </ul>
 * Shift = i - last_occurrence(c) (minimum 1)
 * </p>
 * <br><br>
 * <p>
 * <b>Heuristic 2 - Good Suffix (GS)</b> <br>
 * When a mismatch occurs after matching a suffix t of the pattern:
 * <ul>
 * <li> Case 1: If t appears again elsewhere in the pattern (preceded by a different character), align that occurrence
 * with the matched suffix in the text. </li>
 * <li> Case 2: If no full re-occurrence exists, find the longest prefix of P that matches a suffix of t,
 * and align that. </li>
 * <li> Case 3: If neither, skip the entire pattern length. </li>
 * </p>
 */
public class BoyerMooreAlgo {

    /**
     * Bad Character table: last occurrence of each char in pattern
     */
    private static Map<Character, Integer> buildBadCharTable(String pattern) {
        Map<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            table.put(pattern.charAt(i), i);
        }
        return table;
    }

    /**
     * Good Suffix table <br>
     * shift[i] = how far to shift when mismatch occurs at pattern[i-1]
     * (i.e., pattern[i...m-1] matched successfully)
     */
    private static int[] buildGoodSuffixTable(String pattern) {
        int m = pattern.length();
        int[] shift = new int[m + 1];
        int[] border = new int[m + 1]; // border[i] = length of longest proper border of pattern[i..m-1]

        // Phase 1: compute border array from right
        int i = m, j = m + 1;
        border[i] = j;
        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j] == 0) {
                    shift[j] = j - i;
                }
                j = border[j];
            }
            i--;
            j--;
            border[i] = j;
        }

        // Phase 2: handle the case where a prefix of pattern matches a suffix of the matched portion
        j = border[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == 0) {
                shift[i] = j;
            }
            if (i == j) {
                j = border[j];
            }
        }

        return shift;
    }

    public static int[] boyerMooreSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n)
            return new int[0];

        Map<Character, Integer> badChar = buildBadCharTable(pattern);
        int[] goodSuffix = buildGoodSuffixTable(pattern);

        java.util.List<Integer> results = new java.util.ArrayList<>();
        int s = 0; // current shift of pattern w.r.t text

        while (s <= n - m) {
            int i = m - 1;

            // Scan right-to-left
            while (i >= 0 && pattern.charAt(i) == text.charAt(s + i)) {
                i--;
            }

            if (i < 0) {
                // Full match found
                results.add(s);
                s += goodSuffix[0];
            } else {
                char mismatchChar = text.charAt(s + i);
                int bcShift = i - badChar.getOrDefault(mismatchChar, -1);
                int gsShift = goodSuffix[i + 1];
                s += Math.max(bcShift, gsShift);
            }
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        String text = "THIS IS A TEST STRING";
        String pattern = "STRING";

        int[] matches = boyerMooreSearch(text, pattern);
        if (matches.length == 0) {
            System.out.println("Pattern not found.");
        } else {
            for (int idx : matches) {
                System.out.println("Pattern found at index: " + idx);
            }
        }
    }
}