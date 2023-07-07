/* Leetcode 2024: "Maximize the Confusion of an Exam"
A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:
    Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.

Example 1:
    Input: answerKey = "TTFF", k = 2
    Output: 4
Explanation: Replace both the 'F's with 'T's to get = "TTTT".
There are four consecutive 'T's.

Example 2:
    Input: answerKey = "TFFT", k = 1
    Output: 3
Explanation: Replace the first 'T' with an 'F' to get = "FFFT" or replace the second 'T' with an 'F' to get = "TFFF".
In both cases, there are three consecutive 'F's.

Example 3:
    Input: answerKey = "TTFTTFTT", k = 1
    Output: 5
Explanation: Replace the first 'F' to get = "TTTTTFTT" or replace the second 'F' to get = "TTFTTTTT".
In both cases, there are five consecutive 'T's.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MaxConfusion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answerKey = sc.next();
        int k = sc.nextInt();
        sc.close();
        System.out.println(maxConsecutive(answerKey, k));
    }

    static int maxConsecutive(String answerKey, int k) {
        int max = 0;
        int start = 0;
        Map<Character, Integer> freq = new HashMap<>();

        int len = answerKey.length();
        for (int end = 0; end < len; ++end) {
            char curr = answerKey.charAt(end);
            int currFreq = freq.compute(curr, (key, val) -> val == null ? 1 : val + 1);

            max = Math.max(max, currFreq);

            // slide the window towards right/end of string and exclude starting character
            // if the number of elements in the current window >
            // max consecutive elements + k (changeable characters)
            if (end - start + 1 > max + k) {
                freq.put( // discount the excluded character
                        answerKey.charAt(start),
                        freq.get(answerKey.charAt(start)) - 1);
                start++;
            }
        }

        return len - start;
    }
}
