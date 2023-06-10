import java.util.Scanner;

class reverseString {
    // set the function to static, since in java a static method cant call
    // non-static one without creating its object
    private static char[] reverse_string(char[] str) {
        int l = str.length;
        char temp = ' ';
        for (int i = 0; i < l / 2; i++) {
            temp = str[l - i - 1];
            str[l - i - 1] = str[i];
            str[i] = temp;
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char charArr[] = str.toCharArray();
        charArr = reverse_string(charArr);
        for (char i : charArr)
            System.out.print(i);
        System.out.println();
        sc.close();
    }
}
/*
 * Write a function that reverses a string. The input string is given as an
 * array of characters s. Input: s = ["h","e","l","l","o"] Output:
 * ["o","l","l","e","h"]
 * 
 * Input: s = ["H","a","n","n","a","h"] Output: ["h","a","n","n","a","H"]
 * 
 * Constraints: 1 <= s.length <= 105 s[i] is a printable ascii character. Follow
 * up: Do not allocate extra space for another array. You must do this by
 * modifying the input array in-place with O(1) extra memory.
 */