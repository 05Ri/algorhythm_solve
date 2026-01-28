package baekjoon.BOJ_4949_균형잡힌_세상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Character> dq = new ArrayDeque<>();

        while (true) {
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            }

            dq.clear();
            int len = str.length();
            boolean isBalance = true;

            for (int i = 0; i < len; i++) {
                char c = str.charAt(i);

                if (c == '(' || c == '[') {
                    dq.addLast(c);
                }
                else if (c == ')') {
                    if (!dq.isEmpty() && dq.peekLast().equals('(')) {
                        dq.pollLast();
                    }
                    else {
                        isBalance = false;
                        break;
                    }
                }
                else if (c == ']') {
                    if (!dq.isEmpty() && dq.peekLast().equals('[')) {
                        dq.pollLast();
                    }
                    else {
                        isBalance = false;
                        break;
                    }
                }
            }

            if (!dq.isEmpty()) {
                isBalance = false;
            }

            sb.append(isBalance ? "yes" : "no").append('\n');
        }

        System.out.println(sb);
    }
}
