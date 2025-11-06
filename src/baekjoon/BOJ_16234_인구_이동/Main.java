package baekjoon.BOJ_16234_인구_이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;   // 국가 배열의 크기
    static int L;   // 국가 인구 수 차이 최소값
    static int R;   // 국가 인수 수 차이 최대값
    static int[][] nations; // 국가들의 배열
    static boolean[][] visited; // 방문처리할 배열
    static List<int[]> axises;  // 연합 국가들의 좌표
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
            
            // printNations(movingDays);
            
            // 연합 찾기
            boolean moving = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;

                    axises = new ArrayList<>();
                    axises.add(new int[] {i, j});
                    visited[i][j] = true;
                    cntPerson = nations[i][j];
                    cntNation = 1;
                    dfs(i, j);

                    // 연합이 있었는지 검사
                    if (axises.size() > 1) {
                        moving = true;

                        // 연합 후 인구 수 반영
                        int avg = cntPerson / cntNation;
                        for (int[] axis : axises) {
                            nations[axis[0]][axis[1]] = avg;
                        }
                    }
                }
            }
            if (!moving) break;

            movingDays++;
        }

        System.out.println(movingDays);
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
                axises.add(new int[] {ni, nj});
                dfs(ni, nj);
            }
        }
    }

    static void printNations(int movingDays) {
        System.out.printf("-------------상태: %d일차---------------\n", movingDays);
        for (int[] arr : nations) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("--------------------------------------");
    }
}
