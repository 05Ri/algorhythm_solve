package baekjoon.BOJ_7569_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // BFS 탐색할 큐
    static Queue<int[]> q;
    // 방문배열
    static boolean[][][] visited;
    // 가로
    static int M;
    // 세로
    static int N;
    // 높이
    static int H;
    // 창고
    static int[][][] storage;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 창고 정보 입력받기
        storage = new int[H][N][M];
        q = new LinkedList<>();
        visited = new boolean[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    storage[k][i][j] = Integer.parseInt(st.nextToken());
                    // 안익은 토마토면 건너뛰기
                    if (storage[k][i][j] == 0) continue;
                    // 익은 토마토면 큐에 넣기
                    if (storage[k][i][j] == 1) {
                        q.offer(new int[] {k, i, j, 0});
                    }
                    // 익은 토마토와 비어있는 공간은 방문처리하기
                    visited[k][i][j] = true;
                }
            }
        }

        // 숙성
       System.out.println(aging());
    }

    private static int aging() {
        // 리턴할 날
        int ans = 0;

        // 방향 - 상하좌우앞뒤
        int[] dk = { -1, 1, 0, 0, 0, 0 };
        int[] di = { 0, 0, -1, 1, 0, 0 };
        int[] dj = { 0, 0, 0, 0, -1, 1 };

        // 시뮬레이션
        while (!q.isEmpty()) {
            int[] tomatoInfo = q.poll();
            int height = tomatoInfo[0];
            int length = tomatoInfo[1];
            int width = tomatoInfo[2];
            int day = tomatoInfo[3];

            // 지금 나온 날이 더 크면 갱신
            ans = ans > day ? ans : day;

            for (int dir = 0; dir < 6; dir++) {
                int nk = height + dk[dir];
                int ni = length + di[dir];
                int nj = width + dj[dir];

                // 범위 및 방문 확인
                if (nk < 0 || ni < 0 || nj < 0 || nk >= H || ni >= N || nj >= M || visited[nk][ni][nj]) continue;
                
                q.offer(new int[] {nk, ni, nj, day + 1});
                storage[nk][ni][nj] = 1;
                visited[nk][ni][nj] = true;
            }
        }
        
        // 안 익은 토마토가 있는지 확인
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (storage[k][i][j] == 0) {
                        return -1;
                    }
                }
            }
        }

        return ans;
    }

    static void printStorage() {
        for (int k = 0; k < H; k++) {
            for (int[] arr : storage[k]) {
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println();
    }
}
