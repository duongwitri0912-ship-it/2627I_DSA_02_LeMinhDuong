import edu.princeton.cs.algs4.*;
import java.util.Arrays;

// BÀI 3: KHẢO SÁT QUICK SORT (CÓ VÀ KHÔNG SHUFFLE)

public class Bai3_Khao_sat_QuickSort {

    // 1. Hàm đo thời gian cho QuickSort CÓ shuffle (dùng thư viện gốc)
    public static double measureQuickShuffle(Comparable[] originalArray, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            Comparable[] copy = Arrays.copyOf(originalArray, originalArray.length);

            long start = System.currentTimeMillis();
            Quick.sort(copy);
            long end = System.currentTimeMillis();

            totalTime += (end - start);
        }
        return (double) totalTime / runs;
    }

    // 2. Hàm đo thời gian cho QuickSort KHÔNG shuffle (dùng class tự định nghĩa bên dưới)
    public static double measureQuickWithoutShuffle(Comparable[] originalArray, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            Comparable[] copy = Arrays.copyOf(originalArray, originalArray.length);

            long start = System.currentTimeMillis();
            QuickWithoutShuffle.sort(copy);
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

        // --- BẢNG 1: QUICK SORT CÓ SHUFFLE ---
        System.out.println("=================================================================================");
        System.out.println("                    QUICK SORT (CO SHUFFLE - CHUAN ALGS4)                        ");
        System.out.println("=================================================================================");
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

            double timeFile = measureQuickShuffle(fileData, 3);
            double timeRandom = measureQuickShuffle(randomData, 5);
            double timeSorted = measureQuickShuffle(sortedData, 3);
            double timeReverse = measureQuickShuffle(reverseData, 3);
            double timeEqual = measureQuickShuffle(equalData, 3);

            System.out.printf("%-10d | %-12.2f | %-12.2f | %-12.2f | %-12.2f | %-12.2f%n",
                    n, timeFile, timeRandom, timeSorted, timeReverse, timeEqual);
        }

        System.out.println("\n");

        // --- BẢNG 2: QUICK SORT KHÔNG SHUFFLE ---
        System.out.println("=================================================================================");
        System.out.println("                    QUICK SORT (KHONG SHUFFLE)                                   ");
        System.out.println("=================================================================================");
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

            double timeFile = measureQuickWithoutShuffle(fileData, 3);
            double timeRandom = measureQuickWithoutShuffle(randomData, 5);
            double timeSorted = measureQuickWithoutShuffle(sortedData, 3);
            double timeReverse = measureQuickWithoutShuffle(reverseData, 3);
            double timeEqual = measureQuickWithoutShuffle(equalData, 3);

            System.out.printf("%-10d | %-12.2f | %-12.2f | %-12.2f | %-12.2f | %-12.2f%n",
                    n, timeFile, timeRandom, timeSorted, timeReverse, timeEqual);
        }
    }

    // =========================================================================
    // LỚP QUICK SORT KHÔNG CÓ SHUFFLE (ĐẶT LÀM INNER CLASS TĨNH)
    // =========================================================================
    public static class QuickWithoutShuffle {

        public static void sort(Comparable[] a) {
            sort(a, 0, a.length - 1);
        }

        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int j = partition(a, lo, hi);
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }

        private static int partition(Comparable[] a, int lo, int hi) {
            int i = lo, j = hi + 1;
            while (true) {
                while (less(a[++i], a[lo])) if (i == hi) break;
                while (less(a[lo], a[--j])) if (j == lo) break;
                if (i >= j) break;
                exch(a, i, j);
            }
            exch(a, lo, j);
            return j;
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static void exch(Object[] a, int i, int j) {
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }
}

/*
 * =========================================================================
 * TỔNG KẾT KHẢO SÁT VÀ SO SÁNH HIỆN NĂNG CÁC THUẬT TOÁN SẮP XẾP
 * =========================================================================
 *
 * 1. PHÂN TÍCH HIỆN TƯỢNG QUICK SORT KHÔNG SHUFFLE:
 *    - Nguyên nhân: Đối với các mảng đã có sẵn thứ tự (Sorted) hoặc nghịch đảo (Reverse),
 *      việc không xáo trộn ngẫu nhiên (shuffle) khiến thuật toán luôn chọn phần tử đầu tiên
 *      làm chốt (pivot). Điều này biến cây đệ quy thành dạng tuyến tính lệch lạc.
 *    - Hậu quả: Độ phức tạp thời gian tồi tệ nhất rơi xuống mức O(N^2), chiều sâu đệ quy
 *      đạt tối đa bằng kích thước mảng, dẫn đến tràn bộ nhớ Stack (StackOverflowError)
 *      khi kích thước dữ liệu lớn (N = 32000).
 *    - Giải pháp: Bước xáo trộn ngẫu nhiên (shuffle) ở phiên bản chuẩn algs4 giúp phá vỡ
 *      trật tự ban đầu, đảm bảo xác suất chọn pivot đều đặn và duy trì hiệu năng O(N log N).
 *
 * 2. SO SÁNH VỚI MERGE SORT VÀ INSERTION SORT:
 *    - Merge Sort:
 *      * Là thuật toán chia để trị luôn phân đôi mảng một cách cân bằng tuyệt đối bất kể
 *        trạng thái dữ liệu đầu vào, đảm bảo độ phức tạp ổn định O(N log N) cho mọi trường hợp.
 *      * Chạy mượt mà, không gặp lỗi tràn stack, thời gian ở N = 32000 duy trì từ 3.33 đến 9.00 ms.
 *    - Insertion Sort:
 *      * Sở hữu tính chất thích ứng (adaptive) cực tốt. Với mảng đã sắp xếp (Sorted), thuật toán
 *        chạy với thời gian gần như tuyệt đối 0.00 ms vì không cần dịch chuyển phần tử nào.
 *      * Ngược lại, hiệu năng cực kỳ kém với mảng nghịch đảo (Reverse) hoặc ngẫu nhiên ở kích
 *        thước lớn (lên tới 1213.67 ms ở N = 32000) do phải thực hiện số lượng phép dịch chuyển O(N^2).
 */
