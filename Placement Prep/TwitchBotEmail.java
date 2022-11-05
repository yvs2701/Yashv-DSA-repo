import java.util.HashSet;
import java.util.Scanner;

/* A user is trying to make a new Twitch account with their email. But Twitch has a problem with “bots”: users that make spam accounts on Twitch under fake emails. In this problem, given a user who tries to make an account with a certain number of emails, we want to determine how many of them are unique.
Valid emails consist of a username and a domain. They are separated by the ‘@’ sign. The emails abide by certain rules:
    - Emails are case insensitive. This means that ninja@twitch.tv is the same email as Ninja@twitch.tv and ninja@Twitch.tv.
    - In addition to letters and numbers, the email may contain one or more ‘.’ or ‘+’.
    - If you add periods ‘.’ between some characters in the username, this is equivalent to the same address without dots in the username. Note that this rule does not apply to domain names. Example: ninja@twitch.tv is the same email as nin.ja@twitch.tv, but ninja@twitch.tv is not the same as ninja@twi.tch.tv.
    - If you add a plus ‘+’ in the username, every character following it in the username will be ignored. Note that this rule again does not apply to domain names. Example: ninja+1@twitch.tv is the same email as ninja+2@twitch.tv, but ninja@twitch.tv is not the same as ninja@tw+tch.tv.
All of these rules can be used at the same time. Given an array of strings possible_emails, how many unique emails does it contain? You can assume that all strings you will receive will be a valid email, as defined above. */

public class TwitchBotEmail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.close();

        System.out.println(solution(new String[]{"alexleet@code.com","alex@leetcode.com","a.lex.leet@code.com","alex+leet@leetcode.com"}));
    }

    public static int solution(String[] input) {
        HashSet<String> emails = new HashSet<String>();

        for (String email : input) {
            email = email.toLowerCase();

            if (email.indexOf("+") != -1)
                email = email.substring(0, email.indexOf("+")) + email.substring(email.indexOf("@"));
            email = email.substring(0, email.indexOf("@")).replaceAll("[.]", "") + email.substring(email.indexOf("@"));
            // we cant pass "." as first argument (regex) in String.replaceAll function as /./ regex matches all the character except \n
            // hence if dot (".") needs to be recognized as a character only then use regex character set [] inside which characters hold no special meanings
            // therefore a dot will treated as a dot

            emails.add(email);
        }

        return emails.size();
    }
}
