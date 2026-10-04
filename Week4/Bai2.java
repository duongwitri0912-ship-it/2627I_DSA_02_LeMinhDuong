import edu.princeton.cs.algs4.*;
import java.util.Arrays;

//BÀI 2: KHẢO SÁT SELECTION SORT

public class Bai2 {

    public static double measureAverageTime(Comparable[] originalArray, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            Comparable[] copy = Arrays.copyOf(originalArray, originalArray.length);

            long start = System.currentTimeMillis();
            Selection.sort(copy);
            long end = System.currentTimeMillis();

            totalTime += (end - start);
        }
        return (double) totalTime / runs;
    }

    public static Integer[] generateRandom(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(100000);
        }
        return a;
    }

    public static Integer[] generateSorted(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    public static Integer[] generateReverseSorted(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        return a;
    }

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
            In in = new In("algs4-data/" + (n / 1000) + "Kints.txt");
            int[] primitiveArray = in.readAllInts();
            Integer[] fileData = new Integer[n];
            for (int i = 0; i < n; i++) fileData[i] = primitiveArray[i];

            Integer[] randomData = generateRandom(n);
            Integer[] sortedData = generateSorted(n);
            Integer[] reverseData = generateReverseSorted(n);
            Integer[] equalData = generateAllEqual(n);

            double timeFile = measureAverageTime(fileData, 3);
            double timeRandom = measureAverageTime(randomData, 5);
            double timeSorted = measureAverageTime(sortedData, 3);
            double timeReverse = measureAverageTime(reverseData, 3);
            double timeEqual = measureAverageTime(equalData, 3);

            System.out.printf("%-10d | %-12.2f | %-12.2f | %-12.2f | %-12.2f | %-12.2f%n",
                    n, timeFile, timeRandom, timeSorted, timeReverse, timeEqual);
        }
    }
}

/*
 * KẾT QUẢ THỰC NGHIỆM (SELECTION SORT)
Size       | File TXT     | Random       | Sorted       | Reverse      | Equal
---------------------------------------------------------------------------------
1000       | 2.67         | 3.20         | 0.33         | 0.33         | 0.33
2000       | 1.33         | 1.20         | 1.00         | 1.00         | 1.33
4000       | 5.67         | 5.40         | 4.33         | 4.67         | 4.00
8000       | 21.33        | 20.00        | 17.00        | 16.67        | 15.33
16000      | 89.00        | 89.60        | 70.00        | 69.33        | 61.00
32000      | 393.00       | 357.20       | 266.67       | 265.67       | 254.67

 * NHẬN XÉT KHẢO SÁT SELECTION SORT (Điều chỉnh theo thực nghiệm)

 * 1. Sự thay đổi thời gian chạy của Selection Sort theo dạng dữ liệu:
   - Thời gian chạy KHÔNG hoàn toàn bằng nhau: Nhóm dữ liệu ngẫu nhiên
     (File TXT, Random) tốn nhiều thời gian hơn (đạt 357.20 ms đến
     393.00 ms tại N=32000), trong khi nhóm dữ liệu có tính quy luật
     (Sorted, Reverse, Equal) chạy nhanh hơn (đạt ~254 - 266 ms).

   2. Sự thay đổi thời gian chạy theo kích thước dữ liệu (N):
   - Tỷ lệ gia tăng thời gian (Doubling Ratio) luôn xấp xỉ 4 ở mọi
     loại dữ liệu khi N đủ lớn.

   - Ví dụ ở cột Random: N tăng từ 16000 lên 32000, thời gian tăng từ
     89.60 ms lên 357.20 ms (tăng gấp gần 4 lần), đây là minh chứng
     thực tế rõ ràng nhất cho độ phức tạp O(N^2).

 * SO SÁNH SELECTION SORT VÀ INSERTION SORT (Dựa trên thực nghiệm)

 * 1. Đánh giá theo dạng dữ liệu:
   - Dữ liệu đã sắp xếp (Sorted) và Bằng nhau (Equal): Insertion Sort vượt trội
     hoàn toàn với thời gian chạy xấp xỉ 0.00 ms ở mọi kích thước. Selection Sort
     tỏ ra kém hiệu quả hơn hẳn khi vẫn mất từ 254.67 ms đến 266.67 ms tại
     N=32000 do đặc tính luôn phải duyệt toàn bộ mảng chưa sắp xếp.

   - Dữ liệu ngẫu nhiên (Random / File Kint): Selection Sort cho tốc độ xử lý
     nhanh hơn gần gấp đôi, đạt mức 357.20 ms đến 393.00 ms tại N=32000, trong
     khi Insertion Sort tiêu tốn khoảng 653.67 ms đến 665.40 ms cho cùng quy mô.

   - Dữ liệu sắp xếp ngược (Reverse): Chênh lệch lớn nhất xảy ra ở trường hợp
     này khi Selection Sort hoàn thành trong 265.67 ms (N=32000). Insertion Sort
     chạm mức thời gian tệ nhất là 1213.67 ms do phải thực hiện lượng lớn các
     phép hoán vị liên tục lùi về đầu mảng.

 * 2. Đánh giá theo kích thước dữ liệu (N):
   - Ngoại trừ các trường hợp tốt nhất của Insertion Sort (Sorted, Equal),
     cả hai thuật toán đều tuân theo tỷ lệ tăng trưởng bình phương.

   - Cụ thể, khi kích thước dữ liệu tăng gấp đôi (ví dụ từ 16000 lên 32000),
     thời gian chạy của cả Selection Sort và Insertion Sort ở các dạng dữ liệu
     Random và Reverse đều tăng lên xấp xỉ 4 lần, minh chứng rõ ràng cho độ
     phức tạp O(N^2) của cả hai thuật toán.
 */
