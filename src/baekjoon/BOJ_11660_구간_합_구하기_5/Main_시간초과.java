package baekjoon.BOJ_11660_구간_합_구하기_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_시간초과 {
    private static int[][] numArr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 표의 크기
        int N = Integer.parseInt(st.nextToken());
        // 합을 구해야하는 횟수
        int M = Integer.parseInt(st.nextToken());

        
        // 표에 채워지는 숫자들 입력받기
        numArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                numArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 답을 담을 배열
        int[] answer = new int[M];
        // 어디서부터 어디까지 합을 구하는지 입력받고 바로 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            answer[i] = sumByAxis(x1, y1, x2, y2);
        }


        // 답 출력
        for (int i = 0; i < M; i++) {
            System.out.println(answer[i]);
        }
    }

    /**
     * 좌표 값을 입력받고 그 범위 안의 숫자들의 합을 출력해주는 함수
     * @param x1 작은 x좌표
     * @param y1 작은 y좌표
     * @param x2 큰 x좌표
     * @param y2 큰 y좌표
     * @return 각 좌표에 대한 합계
     */
    public static int sumByAxis(int x1, int y1, int x2, int y2) {
        // 리턴할 값
        int sumValue = 0;

        for (int i = x1 - 1; i < x2; i++) {
            for (int j = y1 - 1; j < y2; j++) {
                sumValue += numArr[i][j];
            }
        }

        return sumValue;
    }
}
