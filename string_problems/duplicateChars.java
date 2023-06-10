import java.util.Scanner;

public class duplicateChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int freq[] = new int[256]; // stores frequency for each character from -128 to +128
        // default boolean vlaue is 'false'
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i)]++;

        for (int i = 0; i < 256; i++) // linear time complexity
            if (freq[i] > 1)
                System.out.print((char) i + " ");

        // this algorithm finds duplicate chars in O(n) time and O(1) extra space (of
        // 256 characters); n = str.length()
        System.out.println();
        sc.close();
    }
}
