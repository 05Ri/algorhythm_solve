package baekjoon.BOJ_16234_인구_이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_시간초과 {
    static int N;   // 국가 배열의 크기
    static int L;   // 국가 인구 수 차이 최소값
    static int R;   // 국가 인수 수 차이 최대값
    static int[][] nations; // 국가들의 배열
    static int[][] movingPeople;    // 국경을 연 후 이동한 사람들을 담을 배열
    static boolean[][] visited; // 방문처리할 배열
    static int cntPerson;  // 사람 수 카운트
    static int cntNation;   // 국가 수 카운트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nations = new int[N][N];

        // 인구 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nations[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int movingDays = 0; // 인구 이동이 며칠 동안 발생하는지
        
        while (true) {
            visited = new boolean[N][N];
            movingPeople = new int[N][N];

            // printNations(movingDays);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    cntPerson = nations[i][j];
                    cntNation = 1;
                    visited[i][j] = true;
                    dfs(i, j);

                    // 만약 국가 개수가 여전히 1이라면 주위에 인구를 비교할 대상이 없는 것이다.
                    if (cntNation == 1) {
                        visited[i][j] = false;
                    }

                    // 계산 값 새로운 배열에 넣기
                    afterJoining(cntPerson / cntNation);
                }
            }

            // 연합이 있었는지 검사 및 이동한 사람이 있다면 복사하기
            boolean flag = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (movingPeople[i][j] > 0) {
                        if (flag) flag = false;
                        nations[i][j] = movingPeople[i][j];
                    }
                }
            }
            if (flag) break;
            
            movingDays++;
        }

        System.out.println(movingDays);
    }

    /**
     * 평균 인구 수를 새로운 배열에 복사
     * @param avg
     */
    static void afterJoining(int avg) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문 처리가 안되어있거나 평균 인구수의 데이터가 이미 있다면 건너뛰기
                if (!visited[i][j] || movingPeople[i][j] > 0) continue;
                movingPeople[i][j] = avg;
            }
        }
    }
    
    /**
     * dfs
     * @param i 행 좌표
     * @param j 열 좌표
     */
    static void dfs(int i, int j) {
        // 상하좌우
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };
        
        for (int dir = 0; dir < 4; dir++) {
            int ni = i + di[dir];
            int nj = j + dj[dir];

            // 범위 및 방문 확인
            if (ni < 0 || nj < 0 || ni >= N || nj >= N || visited[ni][nj]) continue;

            // 현재 좌표와 탐색할 좌표가 범위 내에 있는지 확인
            int n = Math.abs(nations[i][j] - nations[ni][nj]);
            if (L <= n && n <= R) {
                cntPerson += nations[ni][nj];
                cntNation++;
                visited[ni][nj] = true;
                dfs(ni, nj);
            }
        }
    }

    static void printNations(int movingDays) {
        System.out.printf("-------------상태: %d일차------------------\n", movingDays);
        for (int[] arr : nations) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("----------------------------------------");
    }
}
