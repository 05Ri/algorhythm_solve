package baekjoon.BOJ_3190_뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    // 상수 정의
    public static final int SNAKE = 1;
    public static final int APPLE = 4;
    // 보드
    public static int[][] board;
    // 보드의 크기
    public static int N;
    // 방향 및 시간을 담을 배열
    public static String[][] moving;
    // 뱀의 위치를 담을 큐
    public static Deque<int[]> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];

        // 사과의 개수
        int K = Integer.parseInt(br.readLine());
        
        // 사과 위치 입력받기
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            board[i][j] = APPLE;
        }

        // 뱀의 방향 전환
        int L = Integer.parseInt(br.readLine());
        moving = new String[L][2];
        // 뱀의 이동 입력받기
        for (int k = 0; k < L; k++) {
            st = new StringTokenizer(br.readLine());
            // 이동시간
            moving[k][0] = st.nextToken();
            // 방향 바꾸기
            moving[k][1] = st.nextToken();
        }

        System.out.println(gameStart());
    }

    public static int gameStart() {
        // 우, 하, 좌, 상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        // 방향 인덱스
        int dirIdx = 0;
        
        board[1][1] = SNAKE;
        snake.offerFirst(new int[] {1, 1});

        // 진행 시간
        int gameTime = 0;
        // 이동 인덱스
        int movingIdx = 0;

        // 게임 시작
        while (true) {
            gameTime++;
            
            // 머리 이동
            int[] head = snake.peekFirst();
            int nx = head[0] + dx[dirIdx];
            int ny = head[1] + dy[dirIdx];
            
            // 벽 바깥으로 나갔는지, 몸통인지 확인
            if (nx < 1 || nx > N || ny < 1 || ny > N || board[nx][ny] == SNAKE) {
                return gameTime;
            }

            // 사과 확인 - 없으면 꼬리 없애기
            if (board[nx][ny] != APPLE) {
                int[] tail = snake.removeLast();
                board[tail[0]][tail[1]] = 0;
            }

            // 머리 추가
            snake.offerFirst(new int[] {nx, ny});
            board[nx][ny] = SNAKE;
            
            // 뱀의 진행방향 결정
            if (movingIdx < moving.length && Integer.parseInt(moving[movingIdx][0]) == gameTime) {
                String dir = moving[movingIdx][1];

                if (dir.equals("D")) {
                dirIdx = (dirIdx + 1) % 4;
                }
                else {
                    dirIdx = (dirIdx + 3) % 4;
                }

                movingIdx++;
            }
        }
    }
}
