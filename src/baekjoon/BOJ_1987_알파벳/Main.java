package baekjoon.BOJ_1987_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R;   // 보드의 행
    static int C;   // 보드의 열
    static char[][] board; // 알파벳 보드
    static boolean[][] boardVisited; // 보드 방문배열
    static int maxDist;  // 최대 거리
    static boolean[] alphaVisited; // 알파벳의 방문배열

    // 상하좌우
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        // 보드 채우기
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            board[i] = str.toCharArray();
        }

        maxDist = 0;
        alphaVisited = new boolean[26];
        boardVisited = new boolean[R][C];
        alphaVisited[board[0][0] - 'A'] = true;
        boardVisited[0][0] = true;
        dfs(0, 0, 1);

        System.out.println(maxDist);
    }

    static void dfs(int i, int j, int dist) {
        
        for (int dir = 0; dir < 4; dir++) {
            int ni = i + di[dir];
            int nj = j + dj[dir];

            if (ni < 0 || nj < 0 || ni >= R || nj >= C || boardVisited[ni][nj] || alphaVisited[board[ni][nj] - 'A']) {
                // System.out.printf("탈출: (%d %d) / 현재 위치: (%d %d) / 거리: %d\n", ni, nj, i, j, dist);
                maxDist = Math.max(maxDist, dist);
                continue;
            }

            boardVisited[ni][nj] = true;
            alphaVisited[board[ni][nj] - 'A'] = true;
            dfs(ni, nj, dist + 1);
            alphaVisited[board[ni][nj] - 'A'] = false;
            boardVisited[ni][nj] = false;
        }
    }
}