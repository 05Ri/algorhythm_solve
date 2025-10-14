package baekjoon.BOJ_2096_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 내려갈 줄 개수
        int N = Integer.parseInt(br.readLine());
        
        // 양 옆에 빈칸을 만들기 위해 5개로 생성
        int[][] gameTable = new int[N + 1][5];
        // 점수를 계산하여 저장할 배열
        int[][] maxValArr = new int[N + 1][5];
        int[][] minValArr = new int[N + 1][5];

        // 최소 점수를 계산하기 위해 첫째줄을 제외한 다른 칸에 최대값을 채워넣음
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                minValArr[i][j] = Integer.MAX_VALUE;
            }
        }

        // 표 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int idx = 1;
            while(st.hasMoreTokens()) {
                gameTable[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        // 최대 점수 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 3; j++) {
                int maxValue = 0;
                for (int k = -1; k <= 1; k++) {
                    maxValue = maxValue > maxValArr[i - 1][j + k] ? maxValue : maxValArr[i - 1][j + k];
                }
                maxValArr[i][j] = gameTable[i][j] + maxValue;
            }
        }
        
        // 최소 점수 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 3; j++) {
                int minValue = Integer.MAX_VALUE;
                for (int k = -1; k <= 1; k++) {
                    minValue = minValue < minValArr[i - 1][j + k] ? minValue : minValArr[i - 1][j + k];
                }
                minValArr[i][j] = gameTable[i][j] + minValue;
            }
        }

        // 가장 큰 점수와 가장 작은 점수 추출 후 출력
        int maxScore = 0;
        int minScore = Integer.MAX_VALUE;
        for (int i = 1; i <= 3; i++) {
            maxScore = maxScore > maxValArr[N][i] ? maxScore : maxValArr[N][i];
            minScore = minScore < minValArr[N][i] ? minScore : minValArr[N][i];
        }
        System.out.printf("%d %d", maxScore, minScore);
    }
}
