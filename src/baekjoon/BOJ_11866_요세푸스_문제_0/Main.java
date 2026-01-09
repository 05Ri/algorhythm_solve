package baekjoon.BOJ_11866_요세푸스_문제_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        sb.append('<');

        Deque<Integer> table = new ArrayDeque<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        for (int i = 1; i <= N; i++) {
            table.offer(i);
        }

        while (!table.isEmpty()) {
            for (int i = 1; i < K; i++) {
                table.offer(table.poll());
            }
            sb.append(table.poll()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append('>');
        System.out.println(sb.toString());
    }
}
