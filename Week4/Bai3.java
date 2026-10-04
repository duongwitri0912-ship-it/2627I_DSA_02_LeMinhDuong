import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

//BÀI 3: INSERTION SORT 1 - PART 1
//Được viết để chạy test case trên HackerRank

class Result {

    /*
     * Complete the 'insertionSort1' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */
    public static void printArr(List<Integer> arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    public static void insertionSort1(int n, List<Integer> arr) {
        int last = arr.get(n-1);
        for (int i = 2; i < n+1; i++) {
            if (last < arr.get(n-i)) {
                arr.set(n-i+1, arr.get(n-i));
                printArr(arr);
                System.out.println();
                if (i == n) {
                    arr.set(0, last);
                    printArr(arr);
                }
            }
            else {
                arr.set(n-i+1, last);
                printArr(arr);
                break;
            }
        }
    }

}

public class Bai3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.insertionSort1(n, arr);

        bufferedReader.close();
    }
}

