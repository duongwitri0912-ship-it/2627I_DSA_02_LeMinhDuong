import java.util.*;

public class Bai4_BSTLowestCommonAncestor {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node lca(Node root, int v1, int v2) {
        while (root != null) {
            if (v1 < root.data && v2 < root.data) {
                root = root.left;
            } else if (v1 > root.data && v2 > root.data) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        Node root = null;
        while (n-- > 0) {
            int data = scanner.nextInt();
            root = insert(root, data);
        }
        if (scanner.hasNextInt()) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            Node ans = lca(root, v1, v2);
            if (ans != null) {
                System.out.println(ans.data);
            }
        }
        scanner.close();
    }
}