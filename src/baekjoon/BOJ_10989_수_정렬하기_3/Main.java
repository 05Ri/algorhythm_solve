package baekjoon.BOJ_10989_수_정렬하기_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] cnt = new int[10001];

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int n = Integer.parseInt(br.readLine());
            cnt[n] += 1;
        }

        for (int i = 1; i < 10001; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb);
    }
}
