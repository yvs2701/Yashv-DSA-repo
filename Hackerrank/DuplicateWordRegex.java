import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/* Use regular expressions (RegEx) to remove instances of words that are repeated more than once, but retain the first occurrence of any case-insensitive repeated word.
INPUT:
    6
    Goodbye bye bye world world world
    Sam went went to to to his business
    Reya is is the the best player in eye eye game
    in inthe
    Hello hello Ab aB
    I love Love to To tO code
OUTPUT:
    Goodbye bye world
    Sam went to his business
    Reya is the best player in eye game
    in inthe
    Hello Ab
    I love to code */

public class DuplicateWordRegex {
    public static void main(String[] args) {

        String regex = "\\b(\\w+)(?:\\W+\\1\\b)+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
        int numSentences = Integer.parseInt(in.nextLine());
        
        while (numSentences--> 0) {
            String input = in.nextLine();
            
            Matcher m = p.matcher(input);
            
            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
                input = input.replaceAll(m.group(), m.group(1));
            }
            
            // Prints the modified sentence.
            System.out.println(input);
        }
        
        in.close();
    }
}
