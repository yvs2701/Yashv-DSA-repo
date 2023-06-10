import java.util.Scanner;

public class romanNumberToDecimal {
    /* Given a string in roman no format (s)  your task is to convert it to an integer . Various symbols and their values are given below. 
    I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
    Input: s = VI. Output: 6
    Input: s = XIII. Output: 13
    Expected Time Complexity: O(|S|), |S| = length of string S.
    Expected Auxiliary Space: O(1)
    Constraints:
    1<=roman no range<=3999 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String romanNo = sc.nextLine(); // Enter capital letters only !
        sc.close();
        
        int l = romanNo.length(), decimalNo = 0;
        // traverse the string
        for (int i = 0; i < l; i++) {
            switch (romanNo.charAt(i)) {
                case 'I':
                    decimalNo += 1;
                    break;
                case 'V':
                    if (i > 0 && romanNo.charAt(i-1) == 'I')
                        decimalNo += 3 ; // decimalNo = 5 - 1 + (decimalNo - 1);
                    else
                        decimalNo += 5;
                    break;
                case 'X':
                    // if (i > 0 && romanNo.charAt(i-1) == 'V')
                    //     decimalNo += 0 ; // decimalNo = 10 - 5 + (decimalNo - 5) => decimalNo += 10 - 2*5 = 0;
                    // else
                    if (i > 0 && romanNo.charAt(i-1) == 'I')
                        decimalNo += 8 ; // decimalNo = 10 - 1 + (decimalNo - 1) => decimalNo += 10 - 2*1 = 8;
                    else
                        decimalNo += 10;
                    break;
                case 'L':
                    if (i > 0 && romanNo.charAt(i-1) == 'X')
                        decimalNo += 30 ; // decimalNo = 50 - 30 + (decimalNo - 30) => decimalNo += 50 - 2*10 = 30;
                    else
                        decimalNo += 50;
                    break;
                case 'C':
                    // if (i > 0 && romanNo.charAt(i-1) == 'V')
                    //     decimalNo += 0 ; // decimalNo = 100 - 50 + (decimalNo - 50) => decimalNo += 100 - 2*50 = 0;
                    // else
                    if (i > 0 && romanNo.charAt(i-1) == 'X')
                        decimalNo += 80 ; // decimalNo = 100 - 10 + (decimalNo - 10) => decimalNo += 100 - 2*10 = 80;
                    else
                        decimalNo += 100;
                    break;
                case 'D':
                    if (i > 0 && romanNo.charAt(i-1) == 'C')
                        decimalNo += 300 ; // decimalNo = 500 - 100 + (decimalNo - 100) => decimalNo += 50 - 2*10 = 300;
                    else
                        decimalNo += 500;
                    break;
                case 'M':
                // if (i > 0 && romanNo.charAt(i-1) == 'V')
                //     decimalNo += 0 ; // decimalNo = 1000 - 500 + (decimalNo - 500) => decimalNo += 1000 - 2*500 = 0;
                // else
                if (i > 0 && romanNo.charAt(i-1) == 'C')
                    decimalNo += 800 ; // decimalNo = 1000 - 50 + (decimalNo - 50) => decimalNo += 1000 - 2*50 = 900;
                else
                    decimalNo += 1000;
                    break;
                default:
                    System.out.println("Enter capital letters only !");
                    break;
            }
        }
        System.out.println(decimalNo);
    }
}
