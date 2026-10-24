import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class W7_25020094 {

    // PHÂN TÍCH ĐỘ PHỨC TẠP:
    // - Thời gian: O(n log n)
    //     + Mỗi phần tử: tối đa 2-3 thao tác add/poll trên heap, mỗi thao tác O(log n)
    //     + peek() là O(1)
    //     + n phần tử => n * O(log n) = O(n log n)
    // - Không gian: O(n)
    //     + maxPQ + minPQ chứa tổng cộng n phần tử, list result chứa n phần tử
    // - So sánh: sắp xếp lại sau mỗi lần nhập là O(n^2 log n), quá chậm với n = 10^5
    public static List<Double> runningMedian(List<Integer> a) {
        List<Double> result = new ArrayList<>();

        // Nửa nhỏ: max-heap, đỉnh là phần tử lớn nhất của nửa nhỏ
        PriorityQueue<Integer> maxPQ =
                new PriorityQueue<>(a.size() / 2 + 1, Collections.reverseOrder());
        // Nửa lớn: min-heap, đỉnh là phần tử nhỏ nhất của nửa lớn
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(a.size() / 2 + 1);

        for (int x : a) {
            // 1. Thêm vào đúng nửa: O(log n)
            if (maxPQ.isEmpty() || x <= maxPQ.peek()) {
                maxPQ.add(x);
            } else {
                minPQ.add(x);
            }

            // 2. Cân bằng kích thước (maxPQ hơn minPQ tối đa 1): O(log n)
            if (maxPQ.size() > minPQ.size() + 1) {
                minPQ.add(maxPQ.poll());
            } else if (minPQ.size() > maxPQ.size()) {
                maxPQ.add(minPQ.poll());
            }

            // 3. Tính trung vị: O(1) vì chỉ dùng peek()
            if (maxPQ.size() > minPQ.size()) {
                result.add((double) maxPQ.peek());
            } else {
                result.add(((double) maxPQ.peek() + minPQ.peek()) / 2.0);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Double> result = W7_25020094.runningMedian(a);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}