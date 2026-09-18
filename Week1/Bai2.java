// a, Cấu trúc dữ liệu thu được tổng cộng chứa 1 thành phần liên thông, chuỗi thao tác union sẽ dần hợp thành 1 thành phần liên thông chứa 0.
// b, Lần duyệt đầu là 1 lần, lần duyệt thứ 2 là 2 lần,....Tổng cộng khi kết thúc sẽ là: 1 + 2 + ... + n-1 = n*(n-1)/2 xấp xỉ n^2/2 (quick - find).
// c, Thao tác find(0) sẽ thực hiện n-1 lần truy cập mảng, tương ứng với O(n) (do quick-union).
// d, Thao tác find(0) sẽ thực hiện số lần truy cập mảng O(1), độ cao của cây là 1 (Cây không sâu, do weighted union).