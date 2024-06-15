package LD;


/**
 * Uses open addressing to handle collisions; Chaining is used to handle collisions
 * LinkedList is used to handle collisions
 */
public class HashMap {

    static class ListNode {
        int key, val;
        ListNode next;
        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    static final int size = 10000000;
    ListNode[] hashTable;

    public HashMap() {
        this.hashTable = new ListNode[size];
    }

    private int hash(int key) {
        return key % size;
    }


    public void put(int key, int val) {
        remove(key);
        int h = hash(key);
        ListNode node = new ListNode(key, val, hashTable[h]);
        hashTable[h] = node;
    }

    public int get(int key) {
        int h = hash(key);
        ListNode node = hashTable[h];

        while (node != null) {
            if (node.key == key) return node.val;
            node = node.next;
        }

        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        ListNode node = hashTable[h];

        // When node does not exist
        if (node == null) return;

        // When the first node is the target node, assign the next node in the array
        if (node.key == key) hashTable[h] = node.next;

        while (node.next != null) {
            if (node.next.key == key) {
                node.next = node.next.next;
                break;
            }
        }
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));  // return 1
        System.out.println(hashMap.get(3));  // return -1
        hashMap.put(2, 1);          // update the existing value
        System.out.println(hashMap.get(2));  // return 1
        hashMap.remove(2);              // remove the mapping for 2
        System.out.println(hashMap.get(2));  // return -1
    }

}
