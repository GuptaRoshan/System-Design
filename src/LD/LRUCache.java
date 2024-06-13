package LD;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {

    private final Map<Integer, DoublyLinkedNode> map;
    private final int capacity;
    private final DoublyLinkedNode head;
    private final DoublyLinkedNode tail;


    public LRUCache(int capacity) {
        map = new ConcurrentHashMap<>();
        this.capacity = capacity;

        // Dummy head node to mark the boundary
        head = new DoublyLinkedNode(0, 0);
        head.prev = null;

        // Dummy tail node to mark the boundary
        tail = new DoublyLinkedNode(0, 0);
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    // Structure of Doubly Linked List
    private static class DoublyLinkedNode {
        int key;
        int value;
        DoublyLinkedNode prev;
        DoublyLinkedNode next;

        DoublyLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Adding Node right after head
    private void addNode(DoublyLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /**
     * Remove an existing node from the linked list.
     * One interesting property about a double linked list is that the node can remove itself without another reference
     */
    private void removeNode(DoublyLinkedNode node) {
        DoublyLinkedNode prev = node.prev;
        DoublyLinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }


    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        DoublyLinkedNode node = map.get(key);

        // Remove the node from the tail of linked list
        this.removeNode(node);
        // Move the node right after the head
        this.addNode(node);

        return node.value;
    }


    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoublyLinkedNode node = map.get(key);
            node.value = value;
            // Remove the node from the tail of linked list
            this.removeNode(node);
            // Move the node right after the head
            this.addNode(node);
        } else {
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);

            // Remove the node from a map and linked list
            if (map.size() == capacity) {
                this.map.remove(tail.prev.key);
                this.removeNode(tail.prev);
            }

            this.map.put(key, newNode);
            this.addNode(newNode);
        }
    }

    public static void main(String[] args){
        LRUCache lRUCache146 = new LRUCache(2);
        lRUCache146.put(1, 1); // cache is {1=1}
        lRUCache146.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache146.get(1));    // return 1
        lRUCache146.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache146.get(2));    // returns -1 (not found)
        lRUCache146.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache146.get(1));    // return -1
        System.out.println(lRUCache146.get(3));    // return 3
        System.out.println(lRUCache146.get(4));    // return 4
    }

}
