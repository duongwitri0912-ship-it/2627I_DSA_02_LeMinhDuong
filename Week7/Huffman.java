import java.util.*;

// Class to represent Huffman tree node
class Node {
    int data;     // frequency
    int index;    // smallest original index in subtree
    Node left, right;

    // Leaf node
    Node(int d, int i) {
        data = d;
        index = i;
        left = right = null;
    }

    // Internal node
    Node(Node l, Node r) {
        data = l.data + r.data;
        index = Math.min(l.index, r.index); // important for tie-break
        left = l;
        right = r;
    }
}

// Custom min heap for Node class
class Compare implements Comparator<Node> {
    public int compare(Node a, Node b) {
        if (a.data != b.data)
            return a.data - b.data;
        return a.index - b.index;
    }
}

class HuffmanPair {
    int index;
    String code;
    HuffmanPair(int index, String code) {
        this.index = index;
        this.code = code;
    }
}

public class Huffman {

    // Function to traverse tree in preorder manner
    static void preOrder(Node root, ArrayList<HuffmanPair> ans, String curr) {
        if (root == null) return;

        // Leaf node represents a character.
        if (root.left == null && root.right == null) {
            if (curr.equals("")) curr = "0";
            ans.add(new HuffmanPair(root.index, curr));
            return;
        }

        preOrder(root.left, ans, curr + '0');
        preOrder(root.right, ans, curr + '1');
    }

    static ArrayList<HuffmanPair> huffmanCodes(String s, int[] freq) {
        int n = s.length();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Compare());
        for (int i = 0; i < n; i++) {
            Node tmp = new Node(freq[i], i);
            pq.add(tmp);
        }

        if (n == 1) {
            ArrayList<HuffmanPair> res = new ArrayList<>();
            res.add(new HuffmanPair(0, "0"));
            return res;
        }

        while (pq.size() >= 2) {
            Node l = pq.poll();
            Node r = pq.poll();
            Node newNode = new Node(l, r);
            pq.add(newNode);
        }

        Node root = pq.peek();
        ArrayList<HuffmanPair> ans = new ArrayList<>();
        preOrder(root, ans, "");

        // Sắp xếp lại theo đúng thứ tự index ban đầu
        ans.sort(Comparator.comparingInt(p -> p.index));
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcde";
        int[] freq = {15, 8, 102, 24, 6}; // Tương ứng với a=15, b=8, c=10, d=24, e=6

        ArrayList<HuffmanPair> ans = huffmanCodes(s, freq);

        System.out.println("Bảng mã Huffman cho input:");
        int totalBits = 0; // Biến lưu tổng số bit

        for (int i = 0; i < ans.size(); i++) {
            int originalIndex = ans.get(i).index;
            char ch = s.charAt(originalIndex);
            int f = freq[originalIndex];
            String code = ans.get(i).code;

            System.out.println(ch + " (" + f + "): " + code);

            // Tính tổng số bit = tần số * độ dài mã Huffman
            totalBits += f * code.length();
        }

        System.out.println("-----------------------------------");
        System.out.println("Tong so bit sau khi ma hoa: " + totalBits);
    }
}