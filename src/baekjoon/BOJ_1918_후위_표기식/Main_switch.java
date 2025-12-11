package baekjoon.BOJ_1918_후위_표기식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_switch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Character> opStack = new Stack<>();

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (c) {
                case '+', '-', '*', '/':
                    // 연산자 우선순위 비교
                    while (!opStack.isEmpty() && opStack.peek() != '('
                    && precedence(opStack.peek()) >= precedence(c)) {
                        sb.append(opStack.pop());
                    }
                    opStack.push(c);
                    break;

                case '(':
                    opStack.push(c);
                    break;

                case ')':
                    while (opStack.peek() != '(') {
                        sb.append(opStack.pop());
                    }
                    // '(' 제거
                    opStack.pop();
                    break;

                default:
                    sb.append(c);
                    break;
            }
        }

        // 입력받은 문자열을 다 읽고 후처리
        while (!opStack.isEmpty()) {
            sb.append(opStack.pop());
        }

        System.out.println(sb.toString());
    }

    static int precedence(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }
}
