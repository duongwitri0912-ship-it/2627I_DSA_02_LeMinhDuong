import java.util.Stack;

public class w3_tailop_25020094 {

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c).append(" ");
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "Lỗi: Biểu thức thiếu dấu ngoặc mở '('";
                }
            }
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Lỗi: Biểu thức thừa dấu ngoặc mở '('";
            }
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "A + B * ( C - D )",
                "A + B - ( C - D )",
                "( A + B ) * C - D / E",
                "A * B + C / D",
                "20 - ( 5 + 2 ) * 1 * 3 - 2 * ( 3 + 1 )"
        };

        for (String infix : testCases) {
            System.out.println("Trung tố : " + infix);
            System.out.println("Hậu tố   : " + convert(infix));
            System.out.println("----------------------------------------");
        }
    }
}