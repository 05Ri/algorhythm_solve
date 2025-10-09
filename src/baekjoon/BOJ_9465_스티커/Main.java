package baekjoon.BOJ_9465_스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());

        // 테스트케이스 돌기
        for (int testCase = 0; testCase < T; testCase++) {
            // 스티커 N개
            int N = Integer.parseInt(br.readLine());
            
            // 각 줄의 점수 입력받기
            int[][] stickers = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 계산
            for (int i = 2; i <= N; i++) {
                stickers[0][i] += stickers[1][i - 2] > stickers[1][i - 1] ? stickers[1][i - 2] : stickers[1][i - 1];
                stickers[1][i] += stickers[0][i - 2] > stickers[0][i - 1] ? stickers[0][i - 2] : stickers[0][i - 1];
            }
            
            // 출력
            System.out.println(stickers[0][N] > stickers[1][N] ? stickers[0][N] : stickers[1][N]);
        }
    }
}
