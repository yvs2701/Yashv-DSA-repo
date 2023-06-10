import java.util.Scanner;

class numericKeypad {
    /* Given a sentence in the form of a string, convert it into its equivalent mobile numeric keypad sequence.
    KEYPAD -
        ABC DEF
     1   2   3
    GHI JKL MNO
     4   5   6
    PQRS TUV WXYZ
     7   8   9
         _
     *   0   #
    _ (is space)
    Input : GEEKSFORGEEKS
        Output : 4333355777733366677743333557777
        For obtaining a number, we need to press a number corresponding to that character for number of times
        equal to position of the character. For example, for character C, we press number 2 three times and accordingly.
    Input : HELLO WORLD
        Output : 4433555555666096667775553
    EXPECTED TIME COMPLEXITY = O(|s|); where s is the input string */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toUpperCase();
        sc.close();

        // store the keypad value for each letter:
        String[] keypad =  {"2", "22", "222",
                            "3", "33", "333",
                            "4", "4", "4",
                            "5", "55", "555",
                            "6", "66", "666",
                            "7", "77", "777", "7777",
                            "8", "88", "888",
                            "9", "99", "999", "9999"
                        };

        int l = input.length();
        String output = "";
        for (int i = 0; i < l; i++) {
            char ch = input.charAt(i);
            if (ch == ' ') // space
                output += "0";
            else if (ch <= 'Z' && ch >= 'A')
                output += keypad[ch - 'A']; // char is implicitly converted to int and our logic works fine !
            output += ".";
        }
        // prints as output
        System.out.println(output);
    }
}