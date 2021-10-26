package Resources;

import java.util.Scanner;

public class kmp_visualizer {
    // preprocess the pattern
    private static int[] lps(String pattern) {
        int l = pattern.length();

        // array containing length of 'proper prefix which is also a suffix' found
        int[] prefix = new int[l];
        prefix[0] = 0; // by definition

        // for the rest of the array
        int i = 1, j = 0;
        while (i < l) {
            if (pattern.charAt(j) == pattern.charAt(i))
                prefix[i++] = j++ + 1;
            else if (j != 0)
                j = prefix[j - 1];
            else
                prefix[i++] = 0;
        }
        return prefix;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt, pattern;
        // enter the text
        txt = sc.nextLine();
        // enter the pattern
        pattern = sc.nextLine();
        sc.close();

        int[] prefix = lps(pattern);

        // kmp searching
        int i = 0, j = 0, l = txt.length(), patlen = pattern.length();

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        System.out.println(ANSI_YELLOW + txt.charAt(i) + ANSI_RESET + txt.substring(i + 1));

        while (i < l) {
            // PRINTING THE STEPS OF STRING MATCHING
            printKMPstep(txt, pattern, i, j, l, patlen, ANSI_RESET, ANSI_YELLOW);

            if (pattern.charAt(j) == txt.charAt(i)) {
                i++; j++;
            }
            else if (j != 0)
                j = prefix[j - 1];
            else
                i++;

            if (j == patlen) {
                System.out.println(ANSI_YELLOW + "Pattern found at index " + (i - j) + ANSI_RESET);
                j = prefix[j - 1];
            }
        }
    }

    private static void printKMPstep(String txt, String pattern, int i, int j, int l, int patlen,
            final String ANSI_RESET, final String ANSI_YELLOW) {
        if (i + 1 < l && i != 0)
            System.out.println(txt.substring(0, i) + ANSI_YELLOW + txt.charAt(i) + ANSI_RESET + txt.substring(i + 1));
        else if (i != 0)
            System.out.println(txt.substring(0, i) + ANSI_YELLOW + txt.charAt(i) + ANSI_RESET);
        for (int k = 0; k < i - j; k++)
            System.out.print(" ");
        if (j + 1 < patlen && j != 0)
            System.out.println(
                    pattern.substring(0, j) + ANSI_YELLOW + pattern.charAt(j) + ANSI_RESET + pattern.substring(j + 1));
        else if (j == 0)
            System.out.println(ANSI_YELLOW + pattern.charAt(j) + ANSI_RESET + pattern.substring(j + 1));
        else
            System.out.println(pattern.substring(0, j) + ANSI_YELLOW + pattern.charAt(j) + ANSI_RESET);
    }
}