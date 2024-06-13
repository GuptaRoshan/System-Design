package LD;

public class MinStack {

    private Node head;

    private static class Node {
        int value;
        int min;
        Node next;

        Node(int value, int min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val, null);
        } else {
            head = new Node(val, Math.min(val, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }


    public static void main(String[] args){
        MinStack minStack155 = new MinStack();
        minStack155.push(4);
        minStack155.push(1);
        minStack155.push(-4);
        minStack155.push(-7);
        minStack155.push(8);
        minStack155.pop();
        System.out.println(minStack155.top());
        System.out.println(minStack155.getMin());
    }

}
