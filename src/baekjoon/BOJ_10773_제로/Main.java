package baekjoon.BOJ_10773_제로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        while (K-- > 0) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                stack.pop();
                continue;
            }

            stack.push(n);
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println(sum);
    }
}