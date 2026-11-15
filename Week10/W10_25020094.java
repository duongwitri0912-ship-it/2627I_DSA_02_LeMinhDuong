
import java.util.Scanner;

public class W10_25020094 {

    static class Result {
        int max;
        int min;

        Result(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    static Result findMaxMin(int[] a, int left, int right) {

        // Base case: Chi co mot phan tu
        if (left == right) {
            return new Result(a[left], a[left]);
        }

        // Base case: Co hai phan tu
        if (right == left + 1) {
            return new Result(
                    Math.max(a[left], a[right]),
                    Math.min(a[left], a[right])
            );
        }

        // Divide: Chia day thanh hai nua
        int mid = (left + right) / 2;

        // Conquer: Tim max, min cua tung nua
        Result L = findMaxMin(a, left, mid);
        Result R = findMaxMin(a, mid + 1, right);

        // Combine: Ket hop ket qua
        int max = Math.max(L.max, R.max);
        int min = Math.min(L.min, R.min);

        return new Result(max, min);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap n: ");
        int n = sc.nextInt();

        int[] a = new int[n];

        System.out.println("Nhap day A:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        Result ans = findMaxMin(a, 0, n - 1);

        System.out.println("Max = " + ans.max);
        System.out.println("Min = " + ans.min);

        sc.close();
    }
}

