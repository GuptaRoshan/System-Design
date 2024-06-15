package LD;

/**
 * Uses closed addressing to handle collisions
 * Liner probing are used to handle collisions in this implementation of HashTable
 */
public class HashTable {
    private static final String DELETED = "DELETED";
    private final String[] table;
    private final int size;


    public HashTable(int size) {
        this.size = size;
        table = new String[size];
    }

    // Simple hash function
    public int hash(String key) {
        return key.length() % size;
    }

    // insert method using linear probing
    public void insert(String key) {
        int index = hash(key);
        // Lazy Deletion
        while (table[index] != null && !table[index].equals(DELETED)) {
            index = (index + 1) % size; // move to the next index
        }
        table[index] = key;
    }

    // removal using lazy deletion
    public void remove(String key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].equals(key)) {
                table[index] = DELETED;
                return;
            }
            index = (index + 1) % size; // Move to the next index
        }
    }

    // search method
    public boolean search(String key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % size;
        }
        return false;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);

        // Insert values
        hashTable.insert("apple");
        hashTable.insert("banana");

        // Check if values exist
        System.out.println("Contains apple: " + hashTable.search("apple")); // Should print: true
        System.out.println("Contains banana: " + hashTable.search("banana")); // Should print: true
        System.out.println("Contains cherry: " + hashTable.search("cherry")); // Should print: false

        // Remove a value
        hashTable.remove("apple");

        // Check if values exist after removal
        System.out.println("Contains apple: " + hashTable.search("apple")); // Should print: false
        System.out.println("Contains banana: " + hashTable.search("banana")); // Should print: true
        System.out.println("Contains cherry: " + hashTable.search("cherry")); // Should print: false
    }

}
