import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class W9_25020094 {

    static class Node {
        int key;
        Node firstChild;
        Node nextSibling;

        Node(int key) {
            this.key = key;
        }
    }

    static class Tree {
        Node root;

        Node find(Node r, int key) {
            if (r == null) return null;
            if (r.key == key) return r;
            Node res = find(r.firstChild, key);
            if (res != null) return res;
            return find(r.nextSibling, key);
        }

        boolean setRoot(int key) {
            if (root != null) return false;
            root = new Node(key);
            return true;
        }

        boolean addChild(int parentKey, int key) {
            if (find(root, key) != null) return false;
            Node p = find(root, parentKey);
            if (p == null) return false;
            Node n = new Node(key);
            if (p.firstChild == null) {
                p.firstChild = n;
            } else {
                Node t = p.firstChild;
                while (t.nextSibling != null) t = t.nextSibling;
                t.nextSibling = n;
            }
            return true;
        }

        void preOrder(Node r, List<Integer> out) {
            if (r == null) return;
            out.add(r.key);
            Node c = r.firstChild;
            while (c != null) {
                preOrder(c, out);
                c = c.nextSibling;
            }
        }

        void postOrder(Node r, List<Integer> out) {
            if (r == null) return;
            Node c = r.firstChild;
            while (c != null) {
                postOrder(c, out);
                c = c.nextSibling;
            }
            out.add(r.key);
        }

        void levelOrder(List<Integer> out) {
            if (root == null) return;
            List<Node> q = new ArrayList<>();
            q.add(root);
            int i = 0;
            while (i < q.size()) {
                Node cur = q.get(i++);
                out.add(cur.key);
                Node c = cur.firstChild;
                while (c != null) {
                    q.add(c);
                    c = c.nextSibling;
                }
            }
        }

        int height(Node r) {
            if (r == null) return -1;
            int h = -1;
            Node c = r.firstChild;
            while (c != null) {
                h = Math.max(h, height(c));
                c = c.nextSibling;
            }
            return h + 1;
        }

        int size(Node r) {
            if (r == null) return 0;
            int s = 1;
            Node c = r.firstChild;
            while (c != null) {
                s += size(c);
                c = c.nextSibling;
            }
            return s;
        }

        int degree(Node r) {
            int d = 0;
            Node c = r.firstChild;
            while (c != null) {
                d++;
                c = c.nextSibling;
            }
            return d;
        }

        int treeDegree(Node r) {
            if (r == null) return 0;
            int d = degree(r);
            Node c = r.firstChild;
            while (c != null) {
                d = Math.max(d, treeDegree(c));
                c = c.nextSibling;
            }
            return d;
        }

        int countLeaves(Node r) {
            if (r == null) return 0;
            if (r.firstChild == null) return 1;
            int s = 0;
            Node c = r.firstChild;
            while (c != null) {
                s += countLeaves(c);
                c = c.nextSibling;
            }
            return s;
        }

        void print(Node r, int level) {
            if (r == null) return;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) sb.append("    ");
            System.out.println(sb.toString() + r.key);
            Node c = r.firstChild;
            while (c != null) {
                print(c, level + 1);
                c = c.nextSibling;
            }
        }
    }

    static void show(String title, List<Integer> list) {
        StringBuilder sb = new StringBuilder(title);
        for (int x : list) sb.append(x).append(" ");
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tree t = new Tree();
        int choice;
        do {
            System.out.println();
            System.out.println("1. Tao goc");
            System.out.println("2. Them nut con");
            System.out.println("3. Duyet tien tu");
            System.out.println("4. Duyet hau tu");
            System.out.println("5. Duyet theo muc");
            System.out.println("6. Chieu cao cay");
            System.out.println("7. So nut");
            System.out.println("8. Bac cua cay");
            System.out.println("9. So nut la");
            System.out.println("10. In cay");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.print("Nhap khoa goc: ");
                    int k = sc.nextInt();
                    System.out.println(t.setRoot(k) ? "Thanh cong" : "Cay da co goc");
                    break;
                }
                case 2: {
                    System.out.print("Nhap khoa nut cha: ");
                    int p = sc.nextInt();
                    System.out.print("Nhap khoa nut con: ");
                    int k = sc.nextInt();
                    System.out.println(t.addChild(p, k) ? "Thanh cong" : "That bai");
                    break;
                }
                case 3: {
                    List<Integer> out = new ArrayList<>();
                    t.preOrder(t.root, out);
                    show("Tien tu: ", out);
                    break;
                }
                case 4: {
                    List<Integer> out = new ArrayList<>();
                    t.postOrder(t.root, out);
                    show("Hau tu: ", out);
                    break;
                }
                case 5: {
                    List<Integer> out = new ArrayList<>();
                    t.levelOrder(out);
                    show("Theo muc: ", out);
                    break;
                }
                case 6:
                    System.out.println("Chieu cao: " + t.height(t.root));
                    break;
                case 7:
                    System.out.println("So nut: " + t.size(t.root));
                    break;
                case 8:
                    System.out.println("Bac cua cay: " + t.treeDegree(t.root));
                    break;
                case 9:
                    System.out.println("So nut la: " + t.countLeaves(t.root));
                    break;
                case 10:
                    t.print(t.root, 0);
                    break;
                default:
                    break;
            }
        } while (choice != 0);
        sc.close();
    }
}
