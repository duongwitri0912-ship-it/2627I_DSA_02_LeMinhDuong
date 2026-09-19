/*
  >Xét cặp (0,4):
    - Trước khi union: Cây chứa 0 có size = Cây chứa 4 -> 4 sẽ trỏ tới 0
    - Nhưng trên hình không như vậy
    -> Loại
  >Xét cặp (2,3):
    - Để 3 trỏ tới 2 -> Cây chứa 3 có size = Cây chứa 2
    - Ta thấy 2 trỏ tới 0 -> Vậy có nghĩa là thứ tự 3 sẽ union với 2 trước -> Không phải union gần nhất
    -> Loại
  >Xét cặp (4,0):
    - Trước khi union: Cây chứa 4 có size = Cây chứa 0 -> 0 sẽ trỏ tới 4
    - Trên hình như vậy
    -> Đúng
   >Xét cặp (4,5):
     - Vì cây chứa 5 có size nhỏ hơn cây chứa 4 nên 5 trỏ đến 4 là hợp lý
     -> Đúng
   >Xét cặp (5,0):
     - Vì cây chứa 5 có size nhỏ hơn cây chứa 0 nên 5 sẽ trỏ đến gốc cây của 0 là 4 là hợp lý
     -> Đúng
   >Xét xặp (5,7):
     - Nếu 7 trỏ đến 5 là thuật toán gần nhất thì vô lý vì nó sẽ phải trỏ đến 4 là gốc của 5 do cây chứa 7 bé hơn cây chứa 5
     -> Loại
   >Xét xặp (6,4):
     - Cây chứa 6 có size nhỏ hơn cây chứa 4 nên 6 sẽ được trỏ vào 4 là hợp lý
     -> Đúng
   >Xét xặp (8,9):
     - Trước khi union: Cây chứa 8 = size Cây chứa 9
     - Sau union: 9 trỏ đến 8
     -> Đúng
   >Vậy những cặp có thể là tham số của lệnh union(p, q) gần nhất là: (4,0), (4,5), (5,0), (6,4), (8,9).
 */
