package baekjoon.BOJ_7568_덩치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] people = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int cnt = 1;

            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }

                if (people[i][0] < people[j][0] && people[i][1] < people[j][1]) {
                    cnt++;
                }
            }

            sb.append(cnt).append(' ');
        }

        System.out.println(sb);
    }
}
