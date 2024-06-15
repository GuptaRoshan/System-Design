package LD;

import java.util.Stack;

public class BSTInorderIterator {
    private final Stack<TreeNode> stack = new Stack<>();

    public BSTInorderIterator(TreeNode root) {
        pushAll(root);
    }

    /**
     * @return whether we have the next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        // Create a binary search tree
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        // Initialize the iterator with the root of the tree
        BSTInorderIterator iterator = new BSTInorderIterator(root);

        // Repeatedly call hasNext and next methods
        while (iterator.hasNext()) {
            System.out.println(iterator.next()); //  3, 7, 9, 15, 20
        }
    }

}
