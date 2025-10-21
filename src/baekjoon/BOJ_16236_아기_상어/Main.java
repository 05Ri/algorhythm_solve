package baekjoon.BOJ_16236_아기_상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 보드의 크기
    public static int N;
    // 보드
    public static int[][] board;
    // 아기 상어 크기
    public static int babySharkGrowth;
    // 아기 상어의 위치
    public static int[] loc = new int[2];
    // 물고기를 먹은 횟수
    public static int fishCnt;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 보드 크기
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        // 보드 상태 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 아기 상어의 위치일 경우 좌표 저장 후 빈 공간으로 만들기
                if (board[i][j] == 9) {
                    loc[0] = i;
                    loc[1] = j;
                    board[i][j] = 0;
                }
            }
        }

        // 초기 조건
        babySharkGrowth = 2;
        fishCnt = 0;
        int time = 0;

        // 아기 상어 먹이 탐지
        while (true) {
            int moving = bfs();
            if (moving < 0) break;
            time += moving;
        }

        System.out.println(time);
    }

    /**
     * 먹이 탐지
     * @return 먹이까지 걸리는 시간. 없으면 -1
     */
    private static int bfs() {
        // 상, 좌, 우, 하
        int[] dx = { 0, -1, 1, 0 };
        int[] dy = { -1, 0, 0, 1 };

        // 탐색 예비구역
        Queue<int[]> queue = new LinkedList<>();
        // 찾은 먹이 리스트
        List<int[]> feedList = new ArrayList<>();
        // 방문 처리
        boolean[][] visited = new boolean[N][N];

        // 아기 상어 위치와 bfs 탐색 깊이를 배열로 큐에 넣기
        visited[loc[0]][loc[1]] = true;
        queue.offer(new int[] {loc[0], loc[1], 0});

        while (!queue.isEmpty()) {
            // 같은 거리(깊이)에서 큐 쭉 돌리기
            int size = queue.size();
            for (int dist = 0; dist < size ; dist++) {
                int[] axis = queue.poll();
    
                // 4방향 확인
                for (int i = 0; i < 4; i++) {
                    int nx = axis[0] + dy[i];
                    int ny = axis[1] + dx[i];
                    // 탐색 깊이 +1
                    int depth = axis[2] + 1;
    
                    // 보드 바깥으로 나갔는지, 해당 좌표를 이미 방문했는지 확인
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
    
                    // 해당 좌표에 아기 상어 크기보다 더 큰 물고기가 있다면 건너뛰기
                    if (babySharkGrowth < board[nx][ny]) continue;
    
                    // 해당 좌표에 아기 상어의 먹이가 있다면
                    if (babySharkGrowth > board[nx][ny] && board[nx][ny] > 0) {
                        // 먹이 리스트에 추가
                        feedList.add(new int[] {nx, ny, depth});
                    }
    
                    // 해당 좌표에 아기 상어 크기와 똑같은 물고기가 있다면 이동 가능
                    // 큐에 추가 후 방문 처리
                    queue.offer(new int[] {nx, ny, depth});
                    visited[nx][ny] = true;
                }
            }

            // 리스트 확인 후 먹이 리스트가 있다면 우선순위를 찾기
            if (!feedList.isEmpty()) {
                feedList.sort((o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                    return Integer.compare(o1[1], o2[1]);
                });

                // 먹이 먹기
                int[] minDist = feedList.get(0);

                // 아기 상어 이동, 먹이 횟수 추가, 보드 바꾸기
                loc[0] = minDist[0];
                loc[1] = minDist[1];
                fishCnt++;
                board[minDist[0]][minDist[1]] = 0;

                // 물고기를 먹은 횟수가 아기 상어 크기와 같으면 성장, 먹은 횟수 초기화
                if (fishCnt == babySharkGrowth) {
                    babySharkGrowth++;
                    fishCnt = 0;
                }

                // 깊이를 리턴
                return minDist[2];
            }
        }
        
        // 아무 먹이가 없다면 -1 리턴
        return -1;
    }
}
