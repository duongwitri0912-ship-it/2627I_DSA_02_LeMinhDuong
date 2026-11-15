import java.util.*;

public class Bai5_IsthisaBST {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Hàm kiểm tra cây tìm kiếm nhị phân (BST)
    public static boolean checkBST(Node root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValid(Node node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data < min || node.data > max) {
            return false;
        }

        return isValid(node.left, min, node.data) && isValid(node.right, node.data, max);
    }

    // Hàm main để chạy thử nghiệm (test)
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        System.out.println("Ket qua kiem tra BST: " + checkBST(root));
    }
}