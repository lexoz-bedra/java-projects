package com.algopfml.hw1.bst;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node findMinNode() {
        Node minNode = root;
        while (minNode.getLeft() != null) {
            minNode = minNode.getLeft();
        }
        return minNode;
    }

    public Node findMaxNode() {
        Node maxNode = root;
        while (maxNode.getRight() != null) {
            maxNode = maxNode.getRight();
        }
        return maxNode;
    }

    private Node search(Node n, int value) {
        if (n == null) {
            return n;
        }
        if (value == n.getValue()) {
            return n;
        }
        if (value < n.getValue()) {
            return search(n.getLeft(), value);
        }

        return search(n.getRight(), value);
    }

    public Node findByValue(int value) {
        //todo:
        Node valueNode = root;
        return search(valueNode, value);
    }

    private int maxLeftValue() {
        Node cur = this.root.getLeft();
        while (cur.getRight() != null) {
            cur = cur.getRight();
        }
        return cur.getValue();
    }

    private Node erase(Node root, int value) {
        if (root == null) {
            return root;
        }
        if (value < root.getValue()) {
            root.setLeft(erase(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(erase(root.getRight(), value));
        } else {
            if (root.getRight() == null && root.getLeft() == null) {
                root = null;
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else if (root.getRight() == null) {
                root = root.getLeft();
            } else {
                root.setValue(maxLeftValue());
            }
        }
        return root;
    }

    public Node delete(Node n) {
        return erase(this.root, n.getValue());
    }

    public Node getRoot() {
        return root;
    }

    public static List<Integer> getAllValues(BinarySearchTree bst) {
        List<Integer> values = new ArrayList<>();
        getAllValues(bst.getRoot(), values);
        return values;
    }

    private static void getAllValues(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }
        values.add(node.getValue());
        getAllValues(node.getLeft(), values);
        getAllValues(node.getRight(), values);
    }
}
