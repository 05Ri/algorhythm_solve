package baekjoon.BOJ_17144_미세먼지_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R;   // 가로
    static int C;   // 세로
    static int[][] house;   // 집
    static int cleanerUpper = 0;    // 공기청정기 윗부분 행 위치
    static Queue<int[]> q = new LinkedList<>(); // 먼지 확산시킬 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());   // 시간

        // 집 정보 입력받기
        house = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
                // 공기청정기면 값 저장
                if (house[i][j] == -1 && cleanerUpper == 0) {
                    cleanerUpper = i;
                }
                // 미세먼지(5 이상)면 큐에 좌표 넣기
                if (house[i][j] >= 5) {
                    q.offer(new int[] {i, j});
                }
            }
        }

        // 시뮬레이션
        for (int time = 0; time < T; time++) {
            dustSpread();
            machineOn();
        }

        // 정답 구하기
        int amountOfFineDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                amountOfFineDust += house[i][j];
            }
        }
        System.out.println(amountOfFineDust + 2);
    }
    
    /**
     * 먼지 확산
     */
    static void dustSpread() {
        // 먼지가 얼마나 퍼졌는지 저장할 배열
        int[][] temp = new int[R][C];

        // 상하좌우
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };

        int size = q.size();
        for (int s = 0; s < size; s++) {

            int[] info = q.poll();
            int axisI = info[0];
            int axisJ = info[1];
            int dustAmount = house[axisI][axisJ];

            // 해당 칸의 먼지가 5 미만이면 퍼지지 않으니 건너뛰기
            if (dustAmount < 5) continue;

            for (int dir = 0; dir < 4; dir++) {
                int ni = axisI + di[dir];
                int nj = axisJ + dj[dir];

                // 범위 밖인지, 공기청정기인지 확인
                if (ni < 0 || nj < 0 || ni >= R || nj >= C || house[ni][nj] == -1) continue;

                int spreadAmount = dustAmount / 5;

                // 다음 칸에 있는 먼지의 양과 합치기
                temp[ni][nj] += spreadAmount;
                // 현재 칸에 있는 먼지의 양에서 빼기
                temp[axisI][axisJ] -= spreadAmount;
            }
        }

        // temp에 저장되어있는 값 합치기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                house[i][j] += temp[i][j];
            }
        }
    }

    /**
     * 기계 가동
     */
    static void machineOn() {

        int cleanerBottom = cleanerUpper + 1;

        // 공기청정기 위 맨 왼쪽열 => 아래로
        for (int i = cleanerUpper - 1; i > 0; i--) {
            house[i][0] = house[i - 1][0];
        }
        // 공기청정기 아래 맨 왼쪽열 => 위로
        for (int i = cleanerBottom + 1; i < R - 1; i++) {
            house[i][0] = house[i + 1][0];
        }
        // 맨 윗 · 아랫 행 => 왼쪽으로
        for (int j = 0; j < C - 1; j++) {
            house[0][j] = house[0][j + 1];
            house[R - 1][j] = house[R - 1][j + 1];
        }
        // 공기청정기 위 맨 오른쪽열 => 위로
        for (int i = 0; i < cleanerUpper; i++) {
            house[i][C - 1] = house[i + 1][C - 1];
        }
        // 공기청정기 아래 맨 오른쪽열 => 아래로
        for (int i = R - 1; i > cleanerBottom; i--) {
            house[i][C - 1] = house[i - 1][C - 1];
        }
        // 공기 청정기 오른쪽 열 => 오른쪽으로
        for (int j = C - 1; j > 1; j--) {
            house[cleanerUpper][j] = house[cleanerUpper][j - 1];
            house[cleanerBottom][j] = house[cleanerBottom][j - 1];
        }
        // 공기 청정기 바로 다음 열 = 0
        house[cleanerUpper][1] = 0;
        house[cleanerBottom][1] = 0;

        // 후에 미세먼지 값이 5 이상인 경우만 좌표들을 넣어주기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (house[i][j] >= 5) {
                    q.offer(new int[] {i, j});
                }
            }
        }
    }
}
