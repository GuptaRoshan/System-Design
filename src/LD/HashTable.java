package LD;

/**
 * Uses closed addressing to handle collisions;
 * Different probing techniques are used to handle collisions in this implementation of HashTable
 */
public class HashTable {
    private static final String DELETED = "DELETED";
    private final String[] table;
    private final int size;
    private final String probingType;

    public HashTable(int size, String probingType) {
        this.size = size;
        this.table = new String[size];
        this.probingType = probingType;
    }

    // Simple hash function
    public int hash(String key) {
        return key.length() % size;
    }

    // Secondary hash function for double hashing
    private int secondaryHash(String key) {
        return 7 - (key.length() % 7);
    }

    // Common insert method
    public void insert(String key) {
        switch (probingType) {
            case "linear":
                insertLinearProbing(key);
                break;
            case "quadratic":
                insertQuadraticProbing(key);
                break;
            case "double":
                insertDoubleHashing(key);
                break;
            default:
                throw new IllegalArgumentException("Invalid probing type");
        }
    }

    // Linear probing insert method
    private void insertLinearProbing(String key) {
        int index = hash(key);
        // Hash Function: (index + 1) % size
        while (table[index] != null && !table[index].equals(DELETED)) {
            index = (index + 1) % size; // move to the next index
        }
        table[index] = key;
    }

    // Quadratic probing insert method
    private void insertQuadraticProbing(String key) {
        int index = hash(key);
        int i = 0;
        // Hash Function: (index + i * i) % size
        while (table[(index + i * i) % size] != null && !table[(index + i * i) % size].equals(DELETED)) {
            i++;
        }
        table[(index + i * i) % size] = key;
    }

    // Double hashing insert method
    private void insertDoubleHashing(String key) {
        int index = hash(key);
        int stepSize = secondaryHash(key);
        int i = 0;

        // Hash Function: (index + i * stepSize) % size
        while (table[(index + i * stepSize) % size] != null && !table[(index + i * stepSize) % size].equals(DELETED)) {
            i++;
        }
        table[(index + i * stepSize) % size] = key;
    }

    // Common remove method
    public void remove(String key) {
        switch (probingType) {
            case "linear":
                removeLinearProbing(key);
                break;
            case "quadratic":
                removeQuadraticProbing(key);
                break;
            case "double":
                removeDoubleHashing(key);
                break;
            default:
                throw new IllegalArgumentException("Invalid probing type");
        }
    }

    // Linear probing remove method
    private void removeLinearProbing(String key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].equals(key)) {
                table[index] = DELETED;
                return;
            }
            index = (index + 1) % size; // Move to the next index
        }
    }

    // Quadratic probing remove method
    private void removeQuadraticProbing(String key) {
        int index = hash(key);
        int i = 0;
        while (table[(index + i * i) % size] != null) {
            if (table[(index + i * i) % size].equals(key)) {
                table[(index + i * i) % size] = DELETED;
                return;
            }
            i++;
        }
    }

    // Double hashing remove method
    private void removeDoubleHashing(String key) {
        int index = hash(key);
        int stepSize = secondaryHash(key);
        int i = 0;

        while (table[(index + i * stepSize) % size]  != null && !table[(index + i * stepSize) % size].equals(key)) {
            i++;
        }

        table[(index + i * stepSize) % size] = DELETED;
    }

    // Common search method
    public boolean search(String key) {
        return switch (probingType) {
            case "linear" -> searchLinearProbing(key);
            case "quadratic" -> searchQuadraticProbing(key);
            case "double" -> searchDoubleHashing(key);
            default -> throw new IllegalArgumentException("Invalid probing type");
        };
    }

    // Linear probing search method
    private boolean searchLinearProbing(String key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % size;
        }
        return false;
    }

    // Quadratic probing search method
    private boolean searchQuadraticProbing(String key) {
        int index = hash(key);
        int i = 0;
        while (table[(index + i * i) % size] != null) {
            if (table[(index + i * i) % size].equals(key)) {
                return true;
            }
            i++;
        }
        return false;
    }

    // Double hashing search method
    private boolean searchDoubleHashing(String key) {
        int index = hash(key);
        int stepSize = secondaryHash(key);
        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + stepSize) % size;
        }
        return false;
    }

    public static void main(String[] args) {
        HashTable linearHashTable = new HashTable(10, "linear");
        HashTable quadraticHashTable = new HashTable(10, "quadratic");
        HashTable doubleHashTable = new HashTable(10, "double");

        // Insert values using linear probing
        linearHashTable.insert("apple");
        linearHashTable.insert("banana");

        // Check if values exist using linear probing
        System.out.println("Linear - Contains apple: " + linearHashTable.search("apple")); // Should print: true
        System.out.println("Linear - Contains banana: " + linearHashTable.search("banana")); // Should print: true
        System.out.println("Linear - Contains cherry: " + linearHashTable.search("cherry")); // Should print: false

        // Remove a value using linear probing
        linearHashTable.remove("apple");

        // Check if values exist after removal using linear probing
        System.out.println("Linear - Contains apple: " + linearHashTable.search("apple")); // Should print: false
        System.out.println("Linear - Contains banana: " + linearHashTable.search("banana")); // Should print: true

        // Insert values using quadratic probing
        quadraticHashTable.insert("apple");
        quadraticHashTable.insert("banana");

        // Check if values exist using quadratic probing
        System.out.println("Quadratic - Contains apple: " + quadraticHashTable.search("apple")); // Should print: true
        System.out.println("Quadratic - Contains banana: " + quadraticHashTable.search("banana")); // Should print: true
        System.out.println("Quadratic - Contains cherry: " + quadraticHashTable.search("cherry")); // Should print: false

        // Remove a value using quadratic probing
        quadraticHashTable.remove("apple");

        // Check if values exist after removal using quadratic probing
        System.out.println("Quadratic - Contains apple: " + quadraticHashTable.search("apple")); // Should print: false
        System.out.println("Quadratic - Contains banana: " + quadraticHashTable.search("banana")); // Should print: true

        // Insert values using double hashing
        doubleHashTable.insert("apple");
        doubleHashTable.insert("banana");

        // Check if values exist using double hashing
        System.out.println("Double - Contains apple: " + doubleHashTable.search("apple")); // Should print: true
        System.out.println("Double - Contains banana: " + doubleHashTable.search("banana")); // Should print: true
        System.out.println("Double - Contains cherry: " + doubleHashTable.search("cherry")); // Should print: false

        // Remove a value using double hashing
        doubleHashTable.remove("apple");

        // Check if values exist after removal using double hashing
        System.out.println("Double - Contains apple: " + doubleHashTable.search("apple")); // Should print: false
        System.out.println("Double - Contains banana: " + doubleHashTable.search("banana")); // Should print: true
    }
}
