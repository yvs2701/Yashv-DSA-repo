package string_problems;

import java.util.HashMap;
import java.util.Scanner;

public class allPermutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        HashMap<String, Integer> permutations = new HashMap<String, Integer>();
        permutations = permute(input, 0, input.length(), permutations);
        System.out.println(permutations.keySet());
        // keySet returns set of all keys in Java HashMap
    }

    /**
     * Function to recursively (& depthwise) swap letters
     * 
     * @param str string to calculate permutation for
     * @param l   starting index
     * @param r   end index
     */
    private static HashMap<String, Integer> permute(String str, int l, int r, HashMap<String, Integer> permutations) {
        if (l == r) {// base case
            if (permutations.get(str) == null) {
                permutations.put(str, 1);
            }
            else {
                permutations.put(str, permutations.get(str) + 1);
            }
        }
        else {
            for (int i = l; i < r; i++) {
                str = swap(str, l, i);
                permutations = permute(str, l + 1, r, permutations);
                // now we have finished one full depth of the starting branch
                // (swap first 2 letters)
                str = swap(str, l, i);
                // now swap back the letters to the original string at the
                // start of this iteration, then swap first and third letter and so on...
            }
        }

        return permutations;
    }

    private static String swap(String s, int i, int j) {
        char temp;
        char[] charArray = s.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
