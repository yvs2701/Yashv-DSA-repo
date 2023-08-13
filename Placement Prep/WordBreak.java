/* Leetcode 139: "Word Break"
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.
Example 1:
    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:
    Input: s = "applepenapple", wordDict = ["apple","pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
    Note that you are allowed to reuse a dictionary word.
Example 3:
    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    Output: false
*/

import java.util.HashSet;
import java.util.Set;

class WordBreak {
    public static void main(String[] args) {
        String input = "an apple a day keeps the doctor away";
        // generate a dictionary containing all the words in the input string along with some extra words
        String[] dictionary = { " ", "a", "an", "the", "day", "doctor", "orange", "away", "apples", "apple", "keeps" };

        System.out.println(new WordBreak().wordBreak(input, dictionary));
    }

    public boolean wordBreak(String s, String[] wordDict) {
        Set<String> outlierWords = new HashSet<>(); // strings not present in our dictionary

        return dfs(s, wordDict, outlierWords);
    }

    private boolean dfs(String str, String[] wordDict, Set<String> outlierWords) {
        if (outlierWords.contains(str))
            return false;

        for (String word : wordDict) {
            if (str.startsWith(word)) {
                int len = word.length();

                if (str.length() == len || dfs(str.substring(len), wordDict, outlierWords))
                    return true;
            }
        }

        outlierWords.add(str);
        return false;
    }
}
