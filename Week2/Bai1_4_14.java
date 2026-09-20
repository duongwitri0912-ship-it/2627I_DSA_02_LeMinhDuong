//Thuật toán cho bài 4-sum
//Đầu tiên chúng ta sẽ nghĩ đến vét cạn(brute force) với 4 vòng lặp lồng nhau, nhưng độ phức tạp sẽ là O(n^4) và không khả thi với n lớn.
/*-Ta có thể cải thiện thuật toán bằng 2 giải pháp nhằm giảm bớt vòng for lồng nhau đi, đó là:
    1.Hash Map
    2.Sắp xếp + Two Pointers
  - Tuy vậy Hash Map không có ưu thế về bộ nhớ và khà năng xử lí nhóm 4 trùng lặp hiệu quả bằng phương pháp sắp xếp + Two Pointers, nên ta sẽ chọn phương pháp thứ 2 này.
 */
//Thuật toán lựa chọn cho 4-sum: Sắp xếp + Two Pointers
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bai1_4_14 {
    public List<List<Integer>> fourSum (int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for (int i=0; i<n; i++) {
            if (i>0 && nums[i] == nums[i-1]) continue;

            for (int j=i+1; j<n; j++) {
                if (j != i+1 && nums[j] == nums[j-1]) continue;

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                    if (sum == target) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        ans.add(temp);

                        k++;
                        l--;

                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    } else if (sum < target) {
                        k ++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return ans;
    }
}

