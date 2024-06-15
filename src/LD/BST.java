package LD;

class BST {

    private Node root;

    private class Node {
        public int key;
        private Node left;
        private Node right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private Node insertHelper(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insertHelper(root.left, key);
        } else if (key > root.key) {
            root.right = insertHelper(root.right, key);
        }
        return root;
    }

    private boolean containsHelper(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (key == root.key) {
            return true;
        }
        return key < root.key ? containsHelper(root.left, key) : containsHelper(root.right, key);
    }

    private Node deleteHelper(Node root, int key) {
        // Base Case: If the tree is empty
        if (root == null) return null;

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = deleteHelper(root.left, key);
        else if (key > root.key)
            root.right = deleteHelper(root.right, key);
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: get the inorder successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // delete the inorder successor
            root.right = deleteHelper(root.right, root.key);
        }

        return root;
    }

    private int minValue(Node root) {
        int min = root.key;
        while (root.left != null) {
            min = root.left.key;
            root = root.left;
        }
        return min;
    }

    // Inserts and return the root
    public void insert(int key) {
        root = insertHelper(root, key);
    }

    // Check if the key is in the tree
    public boolean contains(int key) {
        return containsHelper(root, key);
    }

    // Deletes the key from the tree
    public void delete(int key) {
        root = deleteHelper(root, key);
    }

    public static void main(String[] args) {
        BST bst = new BST();

        // Insert values
        bst.insert(5);
        bst.insert(3);

        // Check if values exist
        System.out.println("Contains 5: " + bst.contains(5)); // Should print: true
        System.out.println("Contains 3: " + bst.contains(3)); // Should print: true
        System.out.println("Contains 7: " + bst.contains(7)); // Should print: false

        // Delete a value
        bst.delete(5);

        // Check if values exist after deletion
        System.out.println("Contains 5: " + bst.contains(5)); // Should print: false
        System.out.println("Contains 3: " + bst.contains(3)); // Should print: true
        System.out.println("Contains 7: " + bst.contains(7)); // Should print: false

    }

}
