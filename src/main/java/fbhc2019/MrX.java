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
        File file = new File("src/main/resources/2019/mr_x/mr_x_sample_input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                String str = scanner.next();
                int rewrites = rewrite(str);
                System.out.println(String.format("Case #%d: %d", t, rewrites));
            }
        }
    }

    private static int rewrite(String str) {
        // ? --> can be anything
        // 0 & ? = 0
        // 1 | ? = 1
        // x & X = 0
        // x | X = 1
        // x ^ X = 1
        if (str.length() == 1) {
            if (isNumber(str.charAt(0))) {
                return 0;
            }
            return 1;
        }
        Stack<Character> operators = new Stack<>();
        Stack<Character> operands = new Stack<>();
        int answer = 0;
        for (char c : str.toCharArray()) {
            if (c == ')') {
                char a = operands.pop();
                char b = operands.pop();
                char op = operators.pop();
                CharCount cc = evaluate(a, b, op);
                operands.push(cc.ch);
                answer += cc.count;
            } else if (c == '&' || c == '|' || c == '^') {
                operators.push(c);
            } else if (c == 'x' || c == 'X' || c == '0' || c == '1') {
                operands.push(c);
            }
        }
        return answer;
    }

    private static class CharCount {
        private final char ch;
        private final int count;

        public CharCount(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    private static CharCount evaluate(char a, char b, char op) {
        if (op == '&') {
            if (a == '0' || b == '0' || (a == 'x' && b == 'X') || (a == 'X' && b == 'x')) {
                return new CharCount('0', 0);
            }
            if (isNumber(a) && isNumber(b)) {
                int i = a - '0';
                int j = b - '0';
                return new CharCount((char) ((i & j) + '0'), 0);
            }
        } else if (op == '|') {
            if (a == '1' || b == '1' || (a == 'x' && b == 'X') || (a == 'X' && b == 'x')) {
                return new CharCount('1', 0);
            }
            if (isNumber(a) && isNumber(b)) {
                int i = a - '0';
                int j = b - '0';
                return new CharCount((char) ((i | j) + '0'), 0);
            }
        } else { // (op == '^')
            if ((a == 'x' && b == 'X') || (a == 'X' && b == 'x')) {
                return new CharCount('1', 0);
            }
            if (isNumber(a) && isNumber(b)) {
                int i = a - '0';
                int j = b - '0';
                return new CharCount((char) ((i ^ j) + '0'), 0);
            }
        }
        // Modify 1 character. TODO: this is wrong!
        return new CharCount('0', 1);
    }

    private static boolean isNumber(char c) {
        return c == '0' || c == '1';
    }
}
