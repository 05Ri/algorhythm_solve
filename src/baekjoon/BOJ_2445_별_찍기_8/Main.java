package baekjoon.BOJ_2445_별_찍기_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 2 * N - 1; i++) {
            int dist = Math.abs(N - 1 - i);

            for (int j = 0; j < N - dist; j++) {
                sb.append("*");
            }

            for (int j = 0; j < 2 * dist; j++) {
                sb.append(' ');
            }

            for (int j = 0; j < N - dist; j++) {
                sb.append('*');
            }

            sb.append('\n');
        }

        System.out.println(sb.toString());
    }
}
