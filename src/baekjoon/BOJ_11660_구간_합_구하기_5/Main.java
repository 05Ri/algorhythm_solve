package baekjoon.BOJ_11660_구간_합_구하기_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 표의 크기
        int N = Integer.parseInt(st.nextToken());
        // 합을 구해야하는 횟수
        int M = Integer.parseInt(st.nextToken());

        // 누적합을 저장할 배열
        int[][] sumArr = new int[N + 1][N + 1];

        // 표에 채워지는 숫자들의 누적합 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int currNum = Integer.parseInt(st.nextToken());
                sumArr[i][j] = currNum + sumArr[i - 1][j] + sumArr[i][j - 1] - sumArr[i - 1][j - 1];
            }
        }
        
        // 어디서부터 어디까지 합을 구하는지 입력받고 누적합을 이용하여 바로 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 누적합을 구할 좌표 조정
            x1 -= 1;
            y1 -= 1;
            
            int answer = sumArr[x2][y2] - sumArr[x1][y2] - sumArr[x2][y1] + sumArr[x1][y1];
            System.out.println(answer);
        }
    }
}
