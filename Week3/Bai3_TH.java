import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//QUEUE USING TWO STACKS

public class Bai3_TH {
    static class MyQueue<T> {
        private final Deque<T> stackIn = new ArrayDeque<>();
        private final Deque<T> stackOut = new ArrayDeque<>();

        public void enqueue(T value) {
            stackIn.push(value);
        }

        private void shiftStacks() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
        }

        public void dequeue() {
            shiftStacks();
            if (!stackOut.isEmpty()) {
                stackOut.pop();
            }
        }

        public T printFront() {
            shiftStacks();
            return stackOut.peek();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();

        MyQueue<Integer> queue = new MyQueue<>();

        for (int i = 0; i < q; i++) {
            int type = scanner.nextInt();
            if (type == 1) {
                int x = scanner.nextInt();
                queue.enqueue(x);
            } else if (type == 2) {
                queue.dequeue();
            } else if (type == 3) {
                System.out.println(queue.printFront());
            }
        }
        scanner.close();
    }
}
/* =========================================================================
 * HƯỚNG DẪN NHẬP TESTCASE KHI CHẠY CHƯƠNG TRÌNH (INPUT INSTRUCTIONS)
 * =========================================================================
 *
 * Dữ liệu nhập vào bao gồm các dòng nguyên theo quy ước:
 *
 * DÒNG 1: Nhập số nguyên 'q' (Tổng số lệnh / truy vấn bạn muốn thực hiện).
 *
 * 'q' DÒNG TIẾP THEO: Mỗi dòng nhập một trong 3 loại lệnh sau:
 *
 *   - Lệnh loại 1: "1 x"  -> Thêm số 'x' vào cuối Queue (Enqueue).
 *                            Ví dụ: 1 42  (thêm số 42 vào queue)
 *
 *   - Lệnh loại 2: "2"    -> Xóa phần tử ở đầu Queue (Dequeue).
 *                            Ví dụ: 2     (xóa phần tử đầu tiên)
 *
 *   - Lệnh loại 3: "3"    -> In phần tử đang ở đầu Queue (Print Front).
 *                            Ví dụ: 3     (chương trình sẽ in ra số ở đầu)
 *
 * -------------------------------------------------------------------------
 * VÍ DỤ MẪU :
 *
 * 10       <- Thực hiện 10 truy vấn
 * 1 42     <- [Lệnh 1] Thêm 42 vào queue
 * 2        <- [Lệnh 2] Xóa phần tử đầu (xóa 42)
 * 1 14     <- [Lệnh 1] Thêm 14 vào queue
 * 1 28     <- [Lệnh 1] Thêm 28 vào queue
 * 3        <- [Lệnh 3] In phần tử đầu hiện tại (Kết quả in ra: 14)
 * 1 60     <- [Lệnh 1] Thêm 60 vào queue
 * 2        <- [Lệnh 2] Xóa phần tử đầu (xóa 14)
 * 3        <- [Lệnh 3] In phần tử đầu hiện tại (Kết quả in ra: 28)
 * 2        <- [Lệnh 2] Xóa phần tử đầu (xóa 28)
 *
 * KẾT QUẢ KỲ VỌNG IN RA MÀN HÌNH:
 * 14
 * 28
 * =========================================================================
 */
