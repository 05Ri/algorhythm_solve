package baekjoon.BOJ_7576_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[][] box;
    static Deque<int[]> dq;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        dq = new ArrayDeque<>();
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] != 0) {
                    visited[i][j] = true;

                    if (box[i][j] == 1) {
                        dq.offer(new int[] {i, j});
                    }
                }
            }
        }

        System.out.println(bfs(N, M));
    }

    static int bfs(int n, int m) {
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };
        
        int day = -1;

        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int s = 0; s < size; s++) {
                int[] curr = dq.poll();
                
                for (int k = 0; k < 4; k++) {
                    int ni = curr[0] + di[k];
                    int nj = curr[1] + dj[k];

                    if (ni < 0 || nj < 0 || ni >= n || nj >= m || visited[ni][nj]) {
                        continue;
                    }

                    box[ni][nj] = 1;
                    dq.offer(new int[] {ni, nj});
                    visited[ni][nj] = true;
                }
            }

            day += 1;
        }

        return checkAllTomatoRipens(n, m) ? day : -1;
    }

    static boolean checkAllTomatoRipens(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
