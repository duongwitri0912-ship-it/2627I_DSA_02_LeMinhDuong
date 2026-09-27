import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//BALANCED BRACKETS

public class Bai2_TH {
    public static boolean isValidBrackets(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        Map<Character, Character> matchingMap = new HashMap<>();
        matchingMap.put(')', '(');
        matchingMap.put('}', '{');
        matchingMap.put(']', '[');

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (matchingMap.containsKey(ch)) {
                if (stack.isEmpty() || matchingMap.get(ch) != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "{[()]}",
                "{[(])}",
                "((())",
                ")()(",
                "[](){}"
        };
        System.out.println("=== KIỂM TRA TÍNH CÂN BẰNG DẤU NGOẶC ===");
        for (String test : testCases) {
            boolean result = isValidBrackets(test);
            System.out.printf("Chuỗi: %-10s -> Kết quả: %s\n", "\"" + test + "\"", result ? "HỢP LỆ (True)" : "KHÔNG HỢP LỆ (False)");
        }
    }
}
