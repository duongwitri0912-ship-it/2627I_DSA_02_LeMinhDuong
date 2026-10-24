import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Bai1_3_9 {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if      (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                String right = vals.pop();
                String left = vals.pop();
                String expr = "( " + left + " " + op + " " + right + " )";
                vals.push(expr);
            }
            else vals.push(s);
        }
        StdOut.println(vals.peek());
    }
}