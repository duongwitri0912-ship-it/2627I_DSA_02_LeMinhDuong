import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Bai4_TH {

    /* =========================================================================
     * HƯỚNG DẪN NHẬP TESTCASE KHI CHẠY CHƯƠNG TRÌNH (INPUT INSTRUCTIONS)
     * =========================================================================
     *
     * Dữ liệu nhập vào bao gồm các dòng theo quy ước:
     *
     * DÒNG 1: Nhập số nguyên 'Q' (Tổng số lượng thao tác cần thực hiện).
     *
     * 'Q' DÒNG TIẾP THEO: Mỗi dòng nhập một trong 4 loại lệnh:
     *
     *   - Lệnh loại 1: "1 W" -> Thêm chuỗi 'W' vào cuối văn bản.
     *                           Ví dụ: 1 abc  (Văn bản hiện tại: "abc")
     *
     *   - Lệnh loại 2: "2 k" -> Xóa 'k' ký tự cuối cùng khỏi văn bản.
     *                           Ví dụ: 2 2    (Nếu văn bản là "abc" -> còn "a")
     *
     *   - Lệnh loại 3: "3 k" -> In ra ký tự thứ 'k' (tính từ 1).
     *                           Ví dụ: 3 1    (In ký tự đầu tiên)
     *
     *   - Lệnh loại 4: "4"   -> Hoàn tác (Undo) thao tác 1 hoặc 2 gần nhất.
     *                           Ví dụ: 4
     *
     * -------------------------------------------------------------------------
     * VÍ DỤ MẪU :
     *
     * 8        <- 8 thao tác
     * 1 abc    <- [Lệnh 1] Append "abc" -> S = "abc"
     * 3 3      <- [Lệnh 3] In ký tự thứ 3 (In ra: c)
     * 2 3      <- [Lệnh 2] Delete 3 ký tự -> S = ""
     * 1 xy     <- [Lệnh 1] Append "xy" -> S = "xy"
     * 3 2      <- [Lệnh 3] In ký tự thứ 2 (In ra: y)
     * 4        <- [Lệnh 4] Undo (Hủy lệnh "1 xy") -> S = ""
     * 4        <- [Lệnh 4] Undo (Hủy lệnh "2 3")  -> S = "abc"
     * 3 1      <- [Lệnh 3] In ký tự thứ 1 (In ra: a)
     *
     * KẾT QUẢ KỲ VỌNG IN RA MÀN HÌNH:
     * c
     * y
     * a
     * =========================================================================
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int q = scanner.nextInt();

        StringBuilder s = new StringBuilder();

        Deque<String> historyStack = new ArrayDeque<>();

        for (int i = 0; i < q; i++) {
            int type = scanner.nextInt();

            switch (type) {
                case 1:
                    String w = scanner.next();
                    historyStack.push(s.toString());
                    s.append(w);
                    break;

                case 2:
                    int k = scanner.nextInt();
                    historyStack.push(s.toString());
                    s.delete(s.length() - k, s.length());
                    break;

                case 3:
                    int index = scanner.nextInt();
                    System.out.println(s.charAt(index - 1));
                    break;

                case 4:
                    if (!historyStack.isEmpty()) {
                        s = new StringBuilder(historyStack.pop());
                    }
                    break;
            }
        }
        scanner.close();
    }
}