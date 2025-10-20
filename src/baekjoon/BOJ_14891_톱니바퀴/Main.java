package baekjoon.BOJ_14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main{
    // 톱니바퀴 4개의 큐
    public static Deque<Integer>[] gears;
    // 톱니바퀴 방문 배열
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 톱니바퀴 초기화
        gears = new Deque[4];
        for (int i = 0; i < 4; i++) {
            gears[i] = new LinkedList<>();
            int gearState = Integer.parseInt(br.readLine());
            // 큐에 톱니바퀴 상태 넣기
            for (int j = 10000000; j > 0; j /= 10) {
                gears[i].offerLast(gearState / j);
                gearState %= j;
            }
        }

        // 회전 입력 수
        int K = Integer.parseInt(br.readLine());
        int[][] rotates = new int[K][2];
        // 회전 입력받기
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            // 회전 수
            int rotateGearNum = Integer.parseInt(st.nextToken());
            // 회전 방향
            int rotateDirection = Integer.parseInt(st.nextToken());
            rotates[k] = new int[] {rotateGearNum, rotateDirection};
        }

        // 톱니 돌리기
        for (int i = 0; i < K; i++) {
            visited = new boolean[4];
            int num = rotates[i][0];
            int dir = rotates[i][1];
            simulation(num - 1, dir);
        }
        
        // 톱니 상태에 따라 점수 계산하기
        int score = 0;
        for (int n = 0; n < 4; n++) {
            int top = gears[n].peekFirst();
            score += top * pow(2, n);
        }

        System.out.println(score);
    }

    /**
     * 톱니바퀴 돌리기
     * @param num 톱니바퀴 번호
     * @param dir 방향
     */
    private static void simulation(int num, int dir) {
        // 방문했다면 건너뛰기
        if (visited[num]) return;
        // 방문처리하기
        visited[num] = true;

        // 양 옆 톱니바퀴 확인 후 돌릴지 판단
        boolean left = false;
        boolean right = false;
        if (num - 1 >= 0) {
            left = gears[num - 1].toArray()[2] != gears[num].toArray()[6];
        }
        if (num + 1 < 4) {
            right = gears[num].toArray()[2] != gears[num + 1].toArray()[6];
        }
        
        // 방향 확인 후 돌리기
        // 시계방향
        if (dir == 1) {
            int end = gears[num].removeLast();
            gears[num].offerFirst(end);
        }
        // 반시계 방향
        else if (dir == -1) {
            int first = gears[num].removeFirst();
            gears[num].offerLast(first);
        }
        
        // 양 옆의 톱니바퀴 범위 확인 후 돌리기
        // 왼쪽 톱니바퀴
        if (num - 1 >= 0 && left) {
            simulation(num - 1, -dir);
        }
        // 오른쪽 톱니바퀴
        if (num + 1 < 4 && right) {
            simulation(num + 1, -dir);
        }
    }

    /**
     * 제곱수 구하기
     * @param bottom 밑
     * @param exp 지수
     * @return 밑 ^ 지수
     */
    private static int pow(int bottom, int exp) {
        int result = 1;

        if (exp == 0) return result;

        for (int n = 0; n < exp; n++) {
            result *= bottom;
        }
        
        return result;
    }
}
