package baekjoon.BOJ_2206_벽_부수고_이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_DFS_설계오류 {
    // 최대값 정의
    static final int INF = Integer.MAX_VALUE;
    // 가로, 세로 크기
    static int N, M;
    // 맵
    static int[][] map;
    // 맵의 방문 배열
    static boolean[][] visited;
    // 벽들의 좌표
    static List<int[]> walls;
    // 가장 짧은 거리
    static int minDist = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로, 세로 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 맵 입력받기
        map = new int[N + 1][M + 1];
        walls = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            String[] numCh = br.readLine().split("");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(numCh[j - 1]);
                if (map[i][j] == 1) {
                    walls.add(new int[] {i, j});
                }
            }
        }
        

        // 시뮬레이션 하기
        visited = new boolean[N + 1][M + 1];
        visited[1][1] = true;
        dfs(1, 1, 0);
        while (!walls.isEmpty()) {
            int[] axis = walls.remove(0);
            map[axis[0]][axis[1]] = 0;
            dfs(1, 1, 1);
            map[axis[0]][axis[1]] = 1;
        }

        // 출력하기
        System.out.println(minDist == INF ? -1 : minDist);
        
    }

    /**
     * 최단 거리 찾기
     * @param locI 좌표 i
     * @param locJ 좌표 j
     * @param dist 찾은 거리
     */
    static void dfs(int locI, int locJ, int dist) {
        // 도착지에 도착하면
        if (locI == N && locJ == M) {
            // 최단 거리 갱신
            if (minDist > dist) {
                minDist = dist;
            }
            return;
        }
        
        // 상하좌우
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };

        for (int dir = 0; dir < 4; dir++) {
            int ni = locI + di[dir];
            int nj = locJ + dj[dir];
            
            // 범위 확인 및 방문 확인
            if (ni < 1 || nj < 1 || ni > N || nj > M || visited[ni][nj]) continue;

            // 벽인지 확인
            if (map[ni][nj] == 1) continue;

            visited[ni][nj] = true;
            dfs(ni, nj, dist + 1);
            visited[ni][nj] = false;
        }
    }

    static void printArrays() {
        for (int[] arr : map) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }

}
