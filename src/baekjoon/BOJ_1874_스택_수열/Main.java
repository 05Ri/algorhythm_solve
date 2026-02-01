package baekjoon.BOJ_1874_스택_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        
        Deque<Integer> stack = new ArrayDeque<>();
        int pushNum = 1;

        for (int tc = 0; tc < n; tc++) {
            int targetNum = Integer.parseInt(br.readLine());

            while (pushNum <= targetNum) {
                stack.push(pushNum++);
                sb.append('+').append('\n');
            }
        
            if (!stack.isEmpty() && stack.peek() == targetNum) {
                stack.pop();
                sb.append('-').append('\n');
            }
            else {
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb);
    }
}
