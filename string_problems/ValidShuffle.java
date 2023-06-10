import java.util.Scanner;

/* Given 2 strings and a third one. Tell if the 3rd string is a valid shuffle of the 2 given strings or not.
Example:
s1 = XY
s2 = 12
then the valid shuffles of s1 and s2 will be: X12Y, 1XY2, Y1X2, X1Y2,1YX2, 1Y2X, 1X2Y... and so on
however X11Y2 or X3Y1 is not a valid shuffle */

public class ValidShuffle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String shuffle = sc.nextLine();
        System.out.println(isValidShuffle(s1, s2, shuffle));
        sc.close();
    }

    private static boolean isValidShuffle(String s1, String s2, String shuffle) {
        if (shuffle.length() != s1.length() + s2.length())
            return false;
        // char ranges from [-128, +128) or -128 to 127 therefore 256 is the length
        int freq[] = new int[256]; // stores the freq of each character in s1 and s2 then compares to frequency in shuffle
        int l1 = s1.length();
        int l2 = s2.length();
        int lShuf = shuffle.length();
        for (int i = 0; i < l1; i++)
            freq[s1.charAt(i)]++;
        for (int i = 0; i < l2; i++)
            freq[s2.charAt(i)]++;
            
        for (int i = 0; i < lShuf; i++) {
            freq[shuffle.charAt(i)]--;
            if (freq[shuffle.charAt(i)] < 0) // if any frequency goes below 0 then shuffle has an extra character
                return false;
        }
        /* this code is not needed since IF the length of shuffle = s1 + s2 and shuffle decreases frequency of no elements < 0
        then we don't need to check anything else (i.e. > 0), since if the length condition was satisfied and shuffle does NOT has any
        extra characters then it HAS TO HAVE some less (which we have already checked in the above loop)
        for (int i = 0; i < lShuf; i++)
            if (shuffle.charAt(i) != 0) // if any character has frequency greater than 0 then shuffle lacked that character
                return false; */
        return true;
    }
}
