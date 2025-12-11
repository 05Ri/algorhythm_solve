package baekjoon.BOJ_1918_후위_표기식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Character> ops = new Stack<>();

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 괄호 앞부분이면 바로 스택에 넣어주기
            if (c == '(') {
                ops.push(c);
                continue;
            }

            // 괄호 뒷부분이면 넣었던 연산자들을 '('가 나올때까지 출력 준비 해주기
            if (c == ')') {
                while (ops.peek() != '(') {
                    sb.append(ops.pop());
                }
                // '(' 처리
                ops.pop();
                continue;
            }

            // 연산자면
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                // 우선순위에 따라 출력 준비 해주기
                while (!ops.isEmpty() && ops.peek() != '('
                && precedence(ops.peek()) >= precedence(c)) {
                    sb.append(ops.pop());
                }
                ops.push(c);
                continue;
            }

            // 그 외(문자열)이면
            sb.append(c);
        }

        // 입력받은 문자열을 다 읽고 후처리
        while (!ops.isEmpty()) {
            sb.append(ops.pop());
        }

        System.out.println(sb.toString());
    }

    static int precedence(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }
}
