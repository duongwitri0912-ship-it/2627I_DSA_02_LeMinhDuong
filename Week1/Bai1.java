// Trong cài đặt hàm union() không chính xác mà bài đã cho, vấn đề hiện tại đó là việc leader[p] được gán = leader[q]
// Điều này sẽ làm ta mất thông tin về nhóm đại diện cho p khi i > p và dẫn đến việc bỏ qua tất cả các thành phần thuộc nhóm đại diện của p
// Bởi lẽ nhóm đại diện của p đã được cập nhật thành leader[q] và ở những lần duyệt kế tiếp sẽ chỉ là những phép gán không có giá trị
// Từ đó việc hợp thành p và q sẽ không thành công


//Đề xuất chỉnh sửa đầy đủ QuickFindUF bằng cách đặt biến pid, qid để giữ cố định tránh bị ghi đè làm mất thông tin
class QuickFindUF {
    private int[] leader;
    private int count;

    public QuickFindUF(int n) {                // Tạo mảng
        count = n;
        leader = new int[n];
        for (int i = 0; i < n; i++) {
            leader[i] = i;
        }
    }

    public int find(int p) {            //Tìm nhóm đại diện của p
        return leader[p];
    }

    public boolean connected(int p, int q) {         //Kiểm tra xem p và q có cùng nhóm đại diện hay không
        return leader[p] == leader[q];
    }

    public void union(int p, int q) {         //Hợp thành p và q
        int pid = leader[p];
        int qid = leader[q];

        if (pid == qid) return;

        for (int i = 0; i < leader.length; i++) {
            if (leader[i] == pid) {
                leader[i] = qid;
            }
        }

        count--;
    }

    public int count() {
        return count;
    }
}

//Test case mà cài đặt ban đầu sẽ chạy sai là: mảng leader[0 1 2 3], n=4
//union(1, 2) -> leader[0 2 2 3]
//union(1, 0) -> leader[0 0 2 3] (khi duyệt đến leader[2] thì thông tin về nhóm đại diện của 1 đã bị mất nên không thành công trong việc hợp 1 và 0)
//find(1) = 0, find(2) = 2 -> find(1) != find(2) => connected(1, 2) = false (sai) (Trong khi đã có union(1, 2) trước đó)