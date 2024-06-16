package LD;

import java.util.Stack;

public class QueueUsingStacks {
    private final Stack<Integer> input;
    private final Stack<Integer> output;

    public QueueUsingStacks() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.empty()) {
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }


    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        // Push values
        queue.push(1);
        queue.push(2);
        // Peek at the front
        System.out.println("Front: " + queue.peek()); // Should print: 1
        // Pop a value
        queue.pop();
        // Check if the queue is empty
        System.out.println("Is Empty: " + queue.empty()); // Should print: false

    }
}
