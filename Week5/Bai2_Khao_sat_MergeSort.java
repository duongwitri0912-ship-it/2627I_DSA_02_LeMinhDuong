import edu.princeton.cs.algs4.*;
import java.util.Arrays;

//BÀI 2: KHẢO SÁT MERGE SORT

public class Bai2_Khao_sat_MergeSort {

    public static double measureAverageTime(Comparable[] originalArray, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            Comparable[] copy = Arrays.copyOf(originalArray, originalArray.length);

            long start = System.currentTimeMillis();
            Merge.sort(copy);
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

/*

 * KẾT QUẢ THỰC NGHIỆM (MERGE SORT)
Size       | File Kint    | Random       | Sorted       | Reverse      | Equal
---------------------------------------------------------------------------------
1000       | 1.00         | 0.20         | 0.33         | 0.33         | 0.33
2000       | 3.00         | 3.60         | 2.33         | 2.00         | 1.67
4000       | 7.67         | 5.60         | 5.67         | 4.67         | 4.33
8000       | 8.67         | 7.40         | 5.00         | 1.67         | 1.00
16000      | 4.33         | 4.40         | 2.00         | 1.67         | 2.33
32000      | 9.00         | 7.60         | 3.67         | 3.67         | 3.33

 * SO SÁNH HIỆU NĂNG: MERGE SORT VS INSERTION SORT
 * 1. Dữ liệu ngẫu nhiên / Nghịch đảo (Random, Reverse, File Kint):
 *    - Merge Sort: Chạy nhanh và ổn định, n=32000 chỉ mất ~9ms.
 *    - Insertion Sort: Chậm rõ rệt do O(n^2), n=32000 tăng vọt tới hơn 1200ms ở bản Reverse.
 *
 * 2. Dữ liệu đã sắp xếp / Bằng nhau (Sorted, Equal):
 *    - Insertion Sort: Tối ưu tuyệt đối (0.00ms) nhờ nhận diện được mảng đã có thứ tự.
 *    - Merge Sort: Vẫn thực hiện chia-trị bình thường (~3-4ms ở n=32000).
 *
 * Kết luận: Insertion Sort tối ưu hơn với mảng nhỏ hoặc đã sắp xếp sẵn;
 *           Merge Sort vượt trội hoàn toàn với dữ liệu lớn, ngẫu nhiên hoặc nghịch đảo.
*/

