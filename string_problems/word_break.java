import java.util.Scanner;

public class word_break {
    /* Given a string A and a dictionary of n words B, find out if A can be segmented into a space-separated sequence of dictionary words. 
    Input:
    n = 12
    B = { "i", "like", "sam", "sung", "samsung", "mobile",
    "ice","cream", "icecream", "man", "go", "mango" }
    A = "ilike"
        Output: 1. Explanation:The string can be segmented as "i like".
    Input:
    n = 12
    B = { "i", "like", "sam", "sung", "samsung", "mobile",
    "ice","cream", "icecream", "man", "go", "mango" }
    A = "ilikesamsung"
        Output: 1. Explanation: The string can be segmented as "i like samsung" or "i like sam sung".
    Expected time complexity: O(s^2)
    Expected auxiliary space: O(s), where s = length of string A

    Constraints:
    1 <= N <= 12
    1 <= s <=1000, where s = length of string A. The length of each word is less than 15. */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a, b[];
        int n; // length of dictionary

        n = sc.nextInt();
        b = new String[n];
        // input dictionary
        for (int i = 0; i < n; i++) {
            b[i] = sc.next(); // won't read the whole line. Reads only space separated words.
        }
        // enter the string
        a = sc.next();
        sc.close();
        System.out.println(wordBreak(a,b));
        /* JUST A RANDOM FACT: THIS PROBLEM LOOKS QUITE SIMILAR TO THE ONE WHICH ATTEMPTS TO CRACK PASSWORD USING 'Dictionary Attacks'
        according to me */
    }

    private static boolean wordBreak(String wrd, String[] dict) {
        int n = wrd.length();
        TrieNode root = new TrieNode();
        // Construct trie
        for(int i = 0; i < n; i++)
            insert(root, dict[i]);

        return _wordBreak(wrd, root);
    }

    // Returns true if string can be segmented into space separated words, otherwise  returns false
    static boolean _wordBreak(String str, TrieNode root) {
        int size = str.length();
    
        // Base case
        if (size == 0)
            return true;
    
        // Try all prefixes of lengths from 1 to size
        for(int i = 1; i <= size; i++) {
            
            // The parameter for search is str.substring(0, i) str.substring(0, i) which is prefix (of input string) of 
            // length 'i'. We first check whether current prefix is in dictionary. 
            // Then we recursively check for remaining string str.substring(i, size) which is suffix of length size-i.
            if (search(root, str.substring(0, i)) && 
                _wordBreak(str.substring(i, size), root))
                return true;
        }
    
        // If we have tried all prefixes and none of them worked
        return false;
    }

    // trie node
    static class TrieNode {
        TrieNode children[];
    
        // isEndOfWord is true if the node represents end of a word
        boolean isEndOfWord;
    
        // Constructor of TrieNode
        TrieNode() {
            children = new TrieNode[26]; // for 26 alphabets
            for(int i = 0; i < 26; i++)
                children[i] = null;
                
            isEndOfWord = false;
        }
    }
    
    // If not present, inserts key into trie. If the key is prefix of trie node, just marks leaf node
    static void insert(TrieNode root, String key) {
        TrieNode node = root;
    
        for(int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
    
            node = node.children[index];
        }
    
        // Mark last node as leaf
        node.isEndOfWord = true;
    }
    
    // Returns true if key presents in trie, else false
    static boolean search(TrieNode root, String key) {
        TrieNode node = root;
    
        for(int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (node.children[index] == null)
                return false;
    
            node = node.children[index];
        }
        return (node != null && node.isEndOfWord);
    }
}