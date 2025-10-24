package baekjoon.BOJ_2206_벽_부수고_이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 가로, 세로 크기
    static int N, M;
    // 맵
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로, 세로 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 맵 입력받기
        map = new int[N ][M];
        for (int i = 0; i < N; i++) {
            String[] numCh = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(numCh[j]);
            }
        }

        // 출력하기
        System.out.println(bfs());
    }

    /**
     * 최단 거리 찾기
     */
    static int bfs() {
        // 상하좌우
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };

        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];

        // i, j, 거리, 벽을 부쉈는지
        queue.offer(new int[] {0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int i = info[0];
            int j = info[1];
            int dist = info[2];
            int isBreak = info[3];

            // 도착지인지 확인하고 가장 작은 값에 넣기
            if (i == N - 1 && j == M - 1) {
                return dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ni = i + di[dir];
                int nj = j + dj[dir];

                // 범위 확인
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;

                // 빈 칸일 경우
                if (map[ni][nj] == 0 && !visited[ni][nj][isBreak]) {
                    visited[ni][nj][isBreak] = true;
                    queue.offer(new int[] {ni, nj, dist + 1, isBreak});
                }
                // 벽일 경우
                else if (map[ni][nj] == 1 && isBreak == 0 && !visited[ni][nj][1]) {
                    visited[ni][nj][1] = true;
                    queue.offer(new int[] {ni, nj, dist + 1, 1});
                }
            }
        }

        return -1;
    }
}
