import edu.princeton.cs.algs4.*;
import java.util.Arrays;

//BÀI 1: KHẢO SÁT INSERTION SORT

public class Bai1 {

    public static double measureAverageTime(Comparable[] originalArray, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            Comparable[] copy = Arrays.copyOf(originalArray, originalArray.length);

            long start = System.currentTimeMillis();
            Insertion.sort(copy);
            long end = System.currentTimeMillis();

            totalTime += (end - start);
        }
        return (double) totalTime / runs;
    }

    // Hàm tạo mảng Integer ngẫu nhiên
    public static Integer[] generateRandom(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(100000);
        }
        return a;
    }

    // Hàm tạo mảng Integer đã sắp xếp
    public static Integer[] generateSorted(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    // Hàm tạo mảng Integer sắp xếp ngược
    public static Integer[] generateReverseSorted(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        return a;
    }

    // Hàm tạo mảng Integer có giá trị bằng nhau
    public static Integer[] generateAllEqual(int n) {
        Integer[] a = new Integer[n];
        Arrays.fill(a, 42);
        return a;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 4000, 8000, 16000, 32000};

        System.out.printf("%-10s | %-12s | %-12s | %-12s | %-12s | %-12s%n",
                "Size", "File Kint", "Random", "Sorted", "Reverse", "Equal");
        System.out.println("---------------------------------------------------------------------------------");

        for (int n : sizes) {
            // 1. Dữ liệu đọc từ file TXT
            In in = new In("algs4-data/" + (n / 1000) + "Kints.txt");
            int[] primitiveArray = in.readAllInts();
            Integer[] fileData = new Integer[n];
            for (int i = 0; i < n; i++) fileData[i] = primitiveArray[i];

            // 2. Sinh các loại dữ liệu còn lại
            Integer[] randomData = generateRandom(n);
            Integer[] sortedData = generateSorted(n);
            Integer[] reverseData = generateReverseSorted(n);
            Integer[] equalData = generateAllEqual(n);

            // 3. Đo thời gian
            double timeFile = measureAverageTime(fileData, 3);
            double timeRandom = measureAverageTime(randomData, 5);
            double timeSorted = measureAverageTime(sortedData, 3);
            double timeReverse = measureAverageTime(reverseData, 3);
            double timeEqual = measureAverageTime(equalData, 3);

            // 4. In kết quả
            System.out.printf("%-10d | %-12.2f | %-12.2f | %-12.2f | %-12.2f | %-12.2f%n",
                    n, timeFile, timeRandom, timeSorted, timeReverse, timeEqual);
        }
    }
}

//THỐNG KÊ THỜI GIAN CHẠY ĐỐI VỚI TỪNG LOẠI DỮ LIỆU VÀ TỪNG LOẠI KÍCH THƯỚC DỮ LIỆU
/*
Size       | File Kint    | Random       | Sorted       | Reverse      | Equal
---------------------------------------------------------------------------------
1000       | 2.33         | 1.20         | 0.00         | 1.00         | 0.00
2000       | 2.67         | 2.60         | 0.00         | 5.00         | 0.00
4000       | 10.67        | 10.40        | 0.00         | 21.00        | 0.00
8000       | 43.67        | 42.40        | 0.00         | 83.33        | 0.00
16000      | 159.33       | 159.20       | 0.00         | 320.67       | 0.00
32000      | 653.67       | 665.40       | 0.00         | 1213.67      | 0.00

 * NHẬN XÉT KHẢO SÁT THUẬT TOÁN INSERTION SORT

 * 1. Sự thay đổi thời gian chạy theo dạng dữ liệu:
   - Trường hợp tốt nhất (Best Case - Sorted và Equal): Thời gian thực thi luôn ở mức 0.00 ms
     bất kể kích thước mảng. Do dữ liệu đã xếp xuôi hoặc bằng nhau, thuật toán chỉ cần
     thực hiện một phép so sánh cho mỗi phần tử và bỏ qua toàn bộ các bước hoán vị.
     Độ phức tạp đạt mức tuyến tính O(N).
   - Trường hợp xấu nhất (Worst Case - Reverse): Tiêu tốn nhiều thời gian nhất (chạm mức
     1213.67 ms ở quy mô 32000). Mảng bị ngược hoàn toàn buộc mỗi phần tử phải so sánh
     và đổi chỗ qua toàn bộ các phần tử đứng trước nó, đẩy số phép toán lên mức tối đa O(N^2).
   - Trường hợp trung bình (Average Case - Random và File Kint): Thời gian chạy diễn biến
     tương đương nhau (đạt ~653 - 665 ms ở N=32000). Thời gian xấp xỉ bằng một nửa so với
     trường hợp xấu nhất, phản ánh đúng đặc tính lý thuyết của Insertion Sort.

 * 2. Sự thay đổi thời gian chạy theo kích thước dữ liệu (N):
   - Đối với các dạng dữ liệu ngẫu nhiên (Random/File) và sắp xếp ngược (Reverse),
     mỗi khi kích thước dữ liệu (N) tăng gấp đôi, thời gian chạy tăng lên xấp xỉ 4 lần.
   - Quan sát thực tế ở cột Reverse: Thời gian tăng từ 83.33 ms (N=8000) lên 320.67 ms
     (N=16000), sau đó tiếp tục nhân 4 lên thành 1213.67 ms (N=32000).
   - Quan sát thực tế ở cột Random: Thời gian bám sát tỷ lệ nhân 4 khi nhảy từ 42.40 ms
     (N=8000) lên 159.20 ms (N=16000) và chạm mức 665.40 ms (N=32000).
   - Kết luận: Tỷ lệ gia tăng thực nghiệm này là minh chứng thực tế khẳng định thời gian
     thực thi của thuật toán Insertion Sort tỷ lệ thuận với bình phương kích thước dữ liệu
     đầu vào (T tỷ lệ thuận với N^2).
 */