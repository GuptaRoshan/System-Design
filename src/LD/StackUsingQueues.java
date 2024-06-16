package LD;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {

    private final Queue<Integer> queue;

    public StackUsingQueues() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++)
            queue.add(queue.poll());
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingQueues stack = new StackUsingQueues();
        // Push values
        stack.push(1);
        stack.push(2);
        // Peek at the top
        System.out.println("Top: " + stack.top()); // Should print: 2
        // Pop a value
        stack.pop();
        // Check if the stack is empty
        System.out.println("Is Empty: " + stack.empty()); // Should print: false
    }
}
