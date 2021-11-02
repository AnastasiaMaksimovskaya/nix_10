package ua.com.alevel.layer2;

import java.util.*;

class Node {
    private int value;
    private Node left;
    private Node right;

    public Node() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

public class BinaryTree {

    private static Node treeNode;

    public static void insertNode(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (treeNode == null) {
            treeNode = newNode;
        } else {
            Node currentNode = treeNode;
            Node parentNode;
            while (true) {
                parentNode = currentNode;
                if (value == currentNode.getValue()) {
                    System.out.println("element already exists");
                    return;
                } else if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null) {
                        parentNode.setLeft(newNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    private static BinaryTree generate() {
        BinaryTree tree = new BinaryTree();
        Random random = new Random();
        int testArray[] = {1, 10, 4, 2, 9, 6, 11, 12, 8, 10};
        for (int i = 0; i < 10; i++) {
            tree.insertNode(testArray[i]);
        }
        return tree;
    }

    private static int getDepth(Node node) {
        if (node != null) {
            int depthRight = getDepth(node.getRight());
            int depthLeft = getDepth(node.getLeft());
            if (depthLeft > depthRight) {
                return depthLeft + 1;
            } else return depthRight + 1;
        }
        return 0;
    }

    private static int getMaxDepth(Node node) {
        return getDepth(treeNode);
    }

    private static void printTree(Node node) {
        Queue<Node> current = new LinkedList<Node>();
        Queue<Node> next = new LinkedList<Node>();
        current.add(node);
        while (!current.isEmpty()) {
            Iterator<Node> iterator = current.iterator();
            while (iterator.hasNext()) {
                Node currentNode = iterator.next();
                if (currentNode.getLeft() != null) {
                    next.add(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    next.add(currentNode.getRight());
                }
                System.out.print(currentNode.getValue() + " ");
            }
            System.out.println();
            current = next;
            next = new LinkedList<Node>();
        }
    }

    public static void run(Scanner scanner) {
        System.out.println("please enter a value to insert in tree, if you want to stop, press S");
        while (true) {
            String val = scanner.nextLine();
            if (val.equals("S")) {
                break;
            }
            try {
                insertNode(Integer.parseInt(val));
            } catch (Exception e) {
                System.out.println("invalid data format");
            }
            printTree(treeNode);
        }
        System.out.println("Max depth is " + getMaxDepth(treeNode));
    }
}

