package LD;

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Trie Node Structure
    private static class TrieNode {
        public boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    public void insert(String word) {
        TrieNode dictionary = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (dictionary.children[ch - 'a'] == null) {
                dictionary.children[ch - 'a'] = new TrieNode();
            }
            dictionary = dictionary.children[ch - 'a'];
        }
        dictionary.isWord = true;
    }

    public boolean search(String word) {
        TrieNode dictionary = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (dictionary.children[ch - 'a'] == null) {
                return false;
            }
            dictionary = dictionary.children[ch - 'a'];
        }
        return dictionary.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode dictionary = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (dictionary.children[ch - 'a'] == null) {
                return false;
            }
            dictionary = dictionary.children[ch - 'a'];
        }
        return true;
    }

    public static void main(String[] args){
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("mango");
        trie.insert("banana");
        System.out.println("Search apple : " + trie.search("apple"));
        System.out.println("Search apple : " + trie.search("lemon"));
        System.out.println("Starts With app :" + trie.startsWith("app"));
    }

}

