import java.io.*;
import java.util.*;

public class Bai6_FindTheRunningMedian {

    static class Node implements Comparable<Node> {
        int value;
        int order;

        Node(int value, int order) {
            this.value = value;
            this.order = order;
        }

        @Override
        public int compareTo(Node o) {
            if (this.value != o.value) return Integer.compare(this.value, o.value);
            return Integer.compare(this.order, o.order);
        }
    }

    static double[] runningMedian(int[] a) {
        int n = a.length;
        double[] result = new double[n];
        TreeSet<Node> set = new TreeSet<>();
        Node mid = null;

        for (int i = 0; i < n; i++) {
            Node x = new Node(a[i], i);
            int size = i;
            set.add(x);

            if (size == 0) {
                mid = x;
            } else if (size % 2 == 1) {
                if (x.compareTo(mid) < 0) mid = set.lower(mid);
            } else {
                if (x.compareTo(mid) > 0) mid = set.higher(mid);
            }

            if (set.size() % 2 == 1) {
                result[i] = mid.value;
            } else {
                result[i] = (mid.value + set.higher(mid).value) / 2.0;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine().trim());
        }

        double[] res = runningMedian(a);
        StringBuilder sb = new StringBuilder();
        for (double v : res) {
            sb.append(String.format(Locale.US, "%.1f", v)).append("\n");
        }
        System.out.print(sb);
    }
}
