package string_problems;

import java.util.Scanner;

/* Split the given binary string in two substrings such that both of them have equal number of 0s and 1s */
class splitBinaryString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String binary = sc.nextLine();
        sc.close();

        int rcount0 = 0, rcount1 = 0; // stores count of 0 and 1 in right substring
        int l = binary.length();
        // count number of zeroes in the string
        for (int i = 0; i < l; i++) {
            if (binary.charAt(i) == '0')
                rcount0++;
            else
                rcount1++;
        }
        // now we will assume our left substring ranges from 0 to index
        // and right ranges from index to string's length
        int index = 0, lcount0 = 0, lcount1 = 0;
        // since index = 0 right now right substring has 'count0' number of 0s
        // and 'count1' number of 1s, and left substring has both of them = 0

        if (/* lcount0 == rcount0 && lcount1 == rcount1 can only occur if -> */ binary.equals("")) {
            System.out.println("both substrings are \"\"");
        } 
        else {
            for (; index < l; index++) {
                if (binary.charAt(index) == '0') {
                    // since we will move our index by one so respective rcount shall decrease
                    // and lcount shall increase
                    rcount0--;
                    lcount0++;
                } 
                else {
                    rcount1--;
                    lcount1++;
                }

                // check if the counts are now equal
                if (lcount0 == rcount0 && lcount1 == rcount1)
                    break;
            }
            if (lcount0 == rcount0 && lcount1 == rcount1)
                System.out.println(binary.substring(0, index + 1) + "  " + binary.substring(index + 1));
            else 
            System.out.println("No such subtrings possible");
        }
    }
}