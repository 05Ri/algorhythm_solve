package baekjoon.BOJ_14502_연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 연구소 세로 크기
    public static int N;
    // 연구소 가로 크기
    public static int M;

    // 연구소 크기
    public static int[][] lab;
    // 안전 영역의 최대 크기
    public static int maximumSafe;
    // 세울 벽
    public static int walls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 연구소 세로 크기
        N = Integer.parseInt(st.nextToken());
        // 연구소 가로 크기
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];

        // 연구소 정보 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 벽 세우기
        buildWalls(0);

        System.out.println(maximumSafe);
    }

    /**
     * 벽 세우기
     * @param count 벽 개수
     */
    private static void buildWalls(int count) {
        if (count == 3) {
            // 바이러스를 퍼트리기 전 깊은 복사
            int[][] originLab = new int[N][M];
            deepCopyLab(lab, originLab);
            spreadVirus();
            deepCopyLab(originLab, lab);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 벽을 세울 곳이 아니면 건너뛰기
                if (lab[i][j] != 0) continue;

                lab[i][j] = 1;
                buildWalls(count + 1);
                lab[i][j] = 0;
            }
        }
    }

    /**
     * 실험실 깊은 복사
     * @param lab1 복사할 연구소
     * @param lab2 붙여넣을 연구소
     */
    private static void deepCopyLab(int[][] lab1, int[][] lab2) {
        for (int i = 0; i < N; i++) {
            lab2[i] = lab1[i].clone();
        }
    }

    /**
     * 바이러스 시뮬레이션
     */
    private static void spreadVirus() {
        // 상, 하, 좌, 우
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, 1, -1 };

        Queue<int[]> viruses = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        // 바이러스 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 큐에 바이러스 넣기
                if (lab[i][j] == 2) {
                    viruses.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 바이러스 퍼트리기
        while (!viruses.isEmpty()) {
            int[] viruse = viruses.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ni = viruse[0] + di[dir];
                int nj = viruse[1] + dj[dir];

                // 바깥으로 나가는지, 방문했는지 확인 후 건너뛰기
                if (ni < 0 || nj < 0 || ni >= N || nj >= M || visited[ni][nj]) continue;

                // 벽이라면 방문처리 후 건너뛰기
                if (lab[ni][nj] == 1) {
                    visited[ni][nj] = true;
                    continue;
                }

                // 아무것도 없다면 빈칸(0)이므로, 퍼트리고 방문처리 후 후보에 넣는다.
                lab[ni][nj] = 2;
                visited[ni][nj] = true;
                viruses.offer(new int[] {ni, nj});
            }
        }

        // 퍼트리는 것이 끝났다면 빈칸 세기
        countSafeZone();
    }

    /**
     * 안전 영역을 세고 가장 많은 안전 영역 수 갱신하기
     */
    private static void countSafeZone() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    cnt++;
                }
            }
        }

        // 갱신
        if (maximumSafe < cnt) {
            maximumSafe = cnt;
        }
    }
}