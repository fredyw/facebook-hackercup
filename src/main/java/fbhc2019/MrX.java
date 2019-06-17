package fbhc2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.facebook.com/hackercup/problem/589264531559040/
 */
public class MrX {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2019/mr_x/mr_x_sample_input.txt");
        File file = new File("src/main/resources/2019/mr_x/mr_x.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                String str = scanner.next();
                IntRef ref = new IntRef();
                generate(str.toCharArray(), 0, 0, ref);
                System.out.println(String.format("Case #%d: %d", t, ref.value));
            }
        }
    }

    private static class IntRef {
        private int value = Integer.MAX_VALUE;
    }

    private static void generate(char[] expr, int index, int count, IntRef min) {
        if (index == expr.length) {
            String newExpr = new String(expr);
            if (evaluate(newExpr, 0) == evaluate(newExpr, 1)) {
                min.value = Math.min(min.value, count);
            }
            return;
        }
        if (expr[index] == '&' || expr[index] == '|' || expr[index] == '^') {
            char original = expr[index];
            for (char op : new char[]{'&', '|', '^'}) {
                expr[index] = op;
                generate(expr, index + 1, expr[index] == original ? count : count + 1, min);
            }
            expr[index] = original;
        } else if (expr[index] == '0' || expr[index] == '1' || expr[index] == 'x' || expr[index] == 'X') {
            char original = expr[index];
            for (char op : new char[]{'0', '1', 'x', 'X'}) {
                expr[index] = op;
                generate(expr, index + 1, expr[index] == original ? count : count + 1, min);
            }
            expr[index] = original;
        } else {
            generate(expr, index + 1, count, min);
        }
    }

    private static char evaluate(String str, int x) {
        Stack<Character> operators = new Stack<>();
        Stack<Character> operands = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == ')') {
                char a = operands.pop();
                char b = operands.pop();
                char op = operators.pop();
                int n1;
                if (a == '0' || a == '1') {
                    n1 = a - '0';
                } else if (a == 'x') {
                    n1 = x;
                } else { // a == 'X'
                    n1 = x == 0 ? 1 : 0;
                }
                int n2;
                if (b == '0' || b == '1') {
                    n2 = b - '0';
                } else if (b == 'x') {
                    n2 = x;
                } else { // b == 'X'
                    n2 = x == 0 ? 1 : 0;
                }
                operands.push((char) (evaluate(n1, n2, op) + '0'));
            } else if (c == '&' || c == '|' || c == '^') {
                operators.push(c);
            } else if (c == 'x' || c == 'X' || c == '0' || c == '1') {
                operands.push(c);
            }
        }
        char c = operands.pop();
        if (c == 'x') {
            return (char) (x + '0');
        }
        if (c == 'X') {
            return (char) ((x == 0 ? 1 : 0) + '0');
        }
        return c;
    }

    private static int evaluate(int a, int b, int op) {
        if (op == '&') {
            return a & b;
        } else if (op == '|') {
            return a | b;
        }
        return a ^ b;
    }
}
