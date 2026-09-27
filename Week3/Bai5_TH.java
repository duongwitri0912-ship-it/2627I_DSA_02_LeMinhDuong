import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//EQUAL STACKS

public class Bai5_TH {
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        int sum1 = 0, sum2 = 0, sum3= 0;

        for(int height : h1) sum1+= height;
        for(int height : h2) sum2+= height;
        for(int height : h3) sum3+= height;

        int i = 0, j = 0, k = 0;

        while (true) {
            if (i == h1.size() || j == h2.size() || k == h3.size()) {
                if (sum1 == sum2 && sum2 == sum3) return sum1;
            }

            if (sum1 == sum2 && sum2 == sum3) {
                return sum1;
            }

            if (sum1 >= sum2 && sum1 >= sum3) {
                sum1 -= h1.get(i++);
            } else if (sum2 >= sum1 && sum2 >= sum3) {
                sum2 -= h2.get(j++);
            } else if (sum3 >= sum1 && sum3 >= sum2) {
                sum3 -= h3.get(k++);
            }
        }
    }
    /* =========================================================================
     * HƯỚNG DẪN NHẬP TESTCASE KHI CHẠY CHƯƠNG TRÌNH (INPUT INSTRUCTIONS)
     * =========================================================================
     *
     * Dòng 1: Nhập 3 số n1, n2, n3 (Số lượng đĩa của cọc 1, cọc 2, cọc 3).
     * Dòng 2: Nhập n1 số nguyên -> Độ dày các đĩa cọc 1 (từ ĐỈNH xuống ĐÁY).
     * Dòng 3: Nhập n2 số nguyên -> Độ dày các đĩa cọc 2 (từ ĐỈNH xuống ĐÁY).
     * Dòng 4: Nhập n3 số nguyên -> Độ dày các đĩa cọc 3 (từ ĐỈNH xuống ĐÁY).
     *
     * -------------------------------------------------------------------------
     * VÍ DỤ MẪU :
     *
     * 5 3 4        <- n1 = 5, n2 = 3, n3 = 4
     * 3 2 1 1 1    <- Cọc 1 (tổng = 8): đỉnh là 3, đáy là 1
     * 4 3 2        <- Cọc 2 (tổng = 9): đỉnh là 4, đáy là 2
     * 1 1 4 1      <- Cọc 3 (tổng = 7): đỉnh là 1, đáy là 1
     *
     * GIẢI THÍCH VÍ DỤ:
     * - Bỏ đĩa 4 ở cọc 2        -> Cọc 2 còn 3 + 2 = 5
     * - Bỏ đĩa 3 ở cọc 1        -> Cọc 1 còn 2 + 1 + 1 + 1 = 5
     * - Cọc 3 giữ nguyên 1 + 1 + 4... nhầm, tổng cọc 3 ban đầu: 1 + 1 + 4 + 1 = 7.
     *   Bỏ đĩa 1, đĩa 1 ở cọc 3 -> Cọc 3 còn 4 + 1 = 5.
     * Cả 3 cọc đều đạt chiều cao = 5.
     *
     * KẾT QUẢ KỲ VỌNG IN RA MÀN HÌNH:
     * 5
     * =========================================================================
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;

        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();

        List<Integer> h1 = new ArrayList<>();
        for (int i = 0; i < n1; i++) h1.add(scanner.nextInt());

        List<Integer> h2 = new ArrayList<>();
        for (int i = 0; i < n2; i++) h2.add(scanner.nextInt());

        List<Integer> h3 = new ArrayList<>();
        for (int i = 0; i < n3; i++) h3.add(scanner.nextInt());

        System.out.println(equalStacks(h1, h2, h3));

        scanner.close();
    }
}
