/*
 * This source file was generated by the Gradle 'init' task
 */
package pa4;

/** 
 *  A binary search tree (BST) is a binary tree where each node has 
 * a comparable key (and an associated value) and satisfies the restriction that 
 * the key in any node is larger than the keys in all nodes in that node's left subtree 
 * and smaller than the keys in all nodes in that node's right subtree.
 */
public class BST {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public BST() {
        this.root = null;
    }

    /** 
     * Insert a node with a given value into the BST.
     * @param value the value of the node to insert
     */
    public void insert(int value) {
        this.root = insert(this.root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) 
            return new Node(value);
        
        if (value < node.value) 
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        return node;
    }

    /** 
     * Delete a node with a given value from the BST.
     * @param value the value of the node to delete
     */
    public void delete(int value) {
        this.root = delete(this.root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null) 
            return null;
        
        if (value < node.value) 
            node.left = delete(node.left, value);
        else if (value > node.value)
            node.right = delete(node.right, value);
        else {
            if (node.left == null) 
                return node.right;
            else if (node.right == null) 
                return node.left;
            else {
                node.value = findMin(node.right).value;
                node.right = delete(node.right, node.value);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) 
            node = node.left;
        return node;
    }

    /** 
     * Search for a node with a given value in the BST and return true if found.
     * @param value the value to search for
     */
    public boolean search(int value) {
        return search(this.root, value);
    }

    private boolean search(Node node, int value) {
        if (node == null) 
            return false;
        
        if (value < node.value) 
            return search(node.left, value);
        else if (value > node.value)
            return search(node.right, value);
        return true;
    }

    /** 
     * Update a node with a given old value to a new value in the BST.
     * @param oldValue the old value of the node to update
     * @param newValue the new value of the node to update
     */
    public void update(int oldValue, int newValue) {
        delete(oldValue);
        insert(newValue);
    }

    /** 
     * Traverse the BST in inorder and return the values as a string. 
     * @return the inorder traversal of the BST
     */
    public String inOrder() {
        return inorder(this.root);
    }

    private String inorder(Node node) {
        if (node == null) 
            return "";
        
        return inorder(node.left) + node.value + " " + inorder(node.right);
    }

    /** 
     * Convert a sorted array to a balanced BST.
     */
    public static void sortedArrayToBST(int[] arr) {
        sortedArrayToBST(arr, 0, arr.length - 1);
    }

    private static Node sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) 
            return null;
        
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.left = sortedArrayToBST(arr, start, mid - 1);
        node.right = sortedArrayToBST(arr, mid + 1, end);
        return node;
    }

    /** 
     * Find the lowest common ancestor of two nodes with given values in the BST.
     */
    public Node lowestCommonAncestor(int value1, int value2) {
        return lowestCommonAncestor(this.root, value1, value2);
    }

    private Node lowestCommonAncestor(Node node, int value1, int value2) {
        if (node == null) 
            return null;
        
        if (value1 < node.value && value2 < node.value) 
            return lowestCommonAncestor(node.left, value1, value2);
        else if (value1 > node.value && value2 > node.value)
            return lowestCommonAncestor(node.right, value1, value2);
        return node;
    }

    public static void main(String[] args) {

        BST bst = new BST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        System.out.println(bst.inOrder());
        bst.delete(3);
        System.out.println(bst.inOrder());
        System.out.println(bst.search(3));
        System.out.println(bst.search(4));
        bst.update(4, 9);
        System.out.println(bst.inOrder());

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node root = sortedArrayToBST(arr, 0, arr.length - 1);
        System.out.println(root.value);

        System.out.println(bst.lowestCommonAncestor(2, 4).value);
        System.out.println(bst.lowestCommonAncestor(2, 6).value);

    }
}