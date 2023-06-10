import java.util.ArrayList;
import java.util.Scanner;

public class printAllSubsequences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        ArrayList<String> solution = new ArrayList<String>();
        /* solution = */ printsubsequences(str, solution); // you can even store all those combinations
    }

    private static ArrayList<String> printsubsequences(String str, ArrayList<String> combinations) {
        // random fact:- there are 2^n - 1 subsequences possible in a string of length n
        // (-1 to subtract the possibility of N choose 0)
        if (str.length() == 1) {
            System.out.println(str);
            combinations.add(str);
            return combinations;
        } // base case

        ArrayList<String> newCombinations = new ArrayList<String>(combinations.size());
        // this is done in order to tackle Concurrent Modification error
        // (P.S. -> thats why Java is awesome, you get to know your mistakes)

        newCombinations.addAll(printsubsequences(str.substring(1), combinations)); // recursive call

        // printing and adding to the combinations
        combinations.add(String.valueOf(str.charAt(0))); // add this character in the combinations
        System.out.print(str.charAt(0) + " ");
        for (String i : newCombinations) {
            String newStr = str.charAt(0) + i;
            System.out.print(newStr + " ");
            combinations.add(newStr); // add combinations starting from this string
        }
        System.out.println(); // to print in a human readable format ;)

        return combinations;
    }
}
