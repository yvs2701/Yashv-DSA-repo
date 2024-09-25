import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean insert(String word) {
        TrieNode current = root;
        boolean created = false;
        for (char ch : word.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
                created = true;
            }
            current = node;
        }
        if (!current.isEndOfWord) {
            current.isEndOfWord = true;
            created = true;
        }
        return created;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int i) {
        if (i == word.length()) {
            current.isEndOfWord = false;
            return true;
        }

        char ch = word.charAt(i);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }

        boolean result = delete(node, word, i + 1);
        if (node.children.isEmpty()) {
            current.children.remove(ch);
        }
        return result;
    }
    
    private List<String> wordsStartingWith(String prefix) {
        List<String> words = new ArrayList<>();
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return words;
            }
            current = node;
        }
        StringBuilder sb = new StringBuilder(prefix);
        dfs(current, sb, words);
        return words;
    }

    private List<String> wordsInTrie() {
        return wordsStartingWith("");
    }

    private void dfs(TrieNode current, StringBuilder sb, List<String> words) {
        if (current.isEndOfWord) {
            words.add(sb.toString());
        }
        for (char ch : current.children.keySet()) {
            sb.append(ch);
            dfs(current.children.get(ch), sb, words);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println("Inserting 'apple': " + trie.insert("apple"));
        System.out.println("Inserting 'apple': " + trie.insert("apple"));
        System.out.println("Searching for 'apple': " + trie.search("apple")); // true
        System.out.println("Searching for 'app': " + trie.search("app")); // false
        System.out.println("Any word starts with 'app': " + trie.startsWith("app")); // true
        
        System.out.println("Inserting 'app': " + trie.insert("app"));
        System.out.println("Searching for 'app': " + trie.search("app")); // true
        System.out.println("Deleting 'app': " + trie.delete("app"));
        System.out.println("Searching for 'app': " + trie.search("app")); // false

        System.out.println("Inserting 'Maria', 'Mario', 'Marry', 'Mark', 'Martha', 'Marvin'");
        trie.insert("Maria");
        trie.insert("Mario");
        trie.insert("Marry");
        trie.insert("Mark");
        trie.insert("Martha");
        trie.insert("Marvin");

        // print all the words in the trie
        System.out.println("Words in trie:\n" + trie.wordsInTrie());
    }
}