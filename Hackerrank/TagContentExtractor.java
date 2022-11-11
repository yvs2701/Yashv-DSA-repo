import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* In a tag-based language like XML or HTML, contents are enclosed between a start tag and an end tag like <tag>contents</tag>. Note that the corresponding end tag starts with a "/".
Given a string of text in a tag-based language, parse this text and retrieve the contents enclosed within sequences of well-organized tags meeting the following criterion:
    - The name of the start and end tags must be same. <h1>Hello World</h2> is not valid, because the text starts with an h1 tag and ends with a non-matching h2 tag.
    - Tags can be nested, but content between nested tags is considered not valid. For example, in <h1><a>contents</a>invalid</h1>, "contents" is valid but "invalid" is not valid.
    - Tags can consist of any printable characters.

INPUT:
    <h1>had<h1>public</h1></h1>
    <>hello</><h>dim</h>>>>>
    <h1>had<h1>public</h1515></h1>
    <h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>
    <Amee>safat codes like a ninja</amee>
    <SA premium>Imtiaz has a secret crush</SA premium>
OUTPUT:
    public
    dim
    None
    Sanjay has no watch
    None
    Imtias has a secret crush */

public class TagContentExtractor {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        // Pattern p = Pattern.compile("(?<=<(.+?>))\\b.+?(?=</\\1)");
        // since java doesn't support variable length look behind hence placed a range {0, 10000} (10^4 is the max length of the input string)
        // Pattern p = Pattern.compile("(?<=<(.(0,10000)?>))\\b.+?(?=</\\1)"); // this method failed for a few input cases

        Pattern p = Pattern.compile("<([^<>]+)>([^<>]+)</\\1>");
        Matcher m;
        int count = 0;

        while (testCases--> 0) {
            String line = in.nextLine();

            //Write your code here
            count = 0;
            m = p.matcher(line);
            while (m.find()) {
                count++;
                System.out.println(m.group(2));
            }
            if (count == 0)
                System.out.println("None");
        }
        in.close();
    }
}
