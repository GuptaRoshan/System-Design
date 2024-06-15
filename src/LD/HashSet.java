package LD;


/**
 * Uses open addressing to handle collisions; Chaining is used to handle collisions
 * Binary Search Tree is used to handle collisions
 */
public class HashSet {
    final static int INITIAL_SIZE = 1000;
    BST[] set;

    public HashSet() {
        set = new BST[INITIAL_SIZE];
    }

    public void add(int key) {
        int h = hash(key);
        if (set[h] == null) {
            set[h] = new BST();
        }
        set[h].insert(key);
    }

    public void remove(int key) {
        int h = hash(key);
        if (set[h] != null) set[h].delete(key);
    }

    public boolean contains(int key) {
        int h = hash(key);
        if (set[h] != null) return set[h].contains(key);
        return false;
    }

    private int hash(int key) {
        return key % INITIAL_SIZE;
    }


    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        // Add values
        hashSet.add(5);
        hashSet.add(3);
        hashSet.add(7);

        // Check if values exist
        System.out.println("Contains 5: " + hashSet.contains(5)); // Should print: true
        System.out.println("Contains 3: " + hashSet.contains(3)); // Should print: true
        System.out.println("Contains 7: " + hashSet.contains(7)); // Should print: true
        System.out.println("Contains 1: " + hashSet.contains(1)); // Should print: false

        // Remove some values
        hashSet.remove(5);
        hashSet.remove(3);

        // Check if values exist after removal
        System.out.println("\nContains 5: " + hashSet.contains(5)); // Should print: false
        System.out.println("Contains 3: " + hashSet.contains(3)); // Should print: false
        System.out.println("Contains 7: " + hashSet.contains(7)); // Should print: true
        System.out.println("Contains 1: " + hashSet.contains(1)); // Should print: false
    }

}
