package baekjoon.BOJ_1865_웜홀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<int[]>[] worldMap; // 월드나라의 정보
    static int[] spendTime;  // 각 시간을 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());   // 테스트케이스의 개수 입력받기

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 지점 수
            int M = Integer.parseInt(st.nextToken());   // 도로의 개수
            int W = Integer.parseInt(st.nextToken());   // 웜홀의 개수

            // 월드나라 초기화
            worldMap = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                worldMap[i] = new ArrayList<>();
            }

            // 도로 정보 입력받기
            for (int road = 0; road < M; road++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());   // 지점1
                int E = Integer.parseInt(st.nextToken());   // 지점2
                int T = Integer.parseInt(st.nextToken());   // 이동 시간

                worldMap[S].add(new int[] {E, T});
                worldMap[E].add(new int[] {S, T});
            }

            // 웜홀 정보 입력받기
            for (int wormHole = 0; wormHole < W; wormHole++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());   // 시작 지점
                int E = Integer.parseInt(st.nextToken());   // 끝 지점
                int T = Integer.parseInt(st.nextToken());   // 줄어드는 시간

                worldMap[S].add(new int[] {E, -T});
            }
            
            // 벨만-포드 및 출력
            System.out.println(bellmanFord(N) ? "YES" : "NO");
        }
    }

    static boolean bellmanFord(int N) {
        // 드는 시간 0으로 초기화
        spendTime = new int[N + 1];
        
        // N - 1번 전체 노드를 돌며 최소비용 갱신하기
        for (int i = 0; i <= N; i++) {
            // 노드 전체 돌기
            for (int node = 1; node <= N; node++) {
                for (int[] next : worldMap[node]) {
                    int nextNode = next[0];
                    int nextTime = next[1];
        
                    if (spendTime[nextNode] > spendTime[node] + nextTime) {
                        spendTime[nextNode] = spendTime[node] + nextTime;
                    }
                }
            }
        }

        // N번째 반복하여 시간을 갱신 시에 줄어든다면 웜홀로 시간이 줄일 수 있는 경우이다.
        for (int node = 1; node <= N; node++) {
            for (int[] next : worldMap[node]) {
                int nextNode = next[0];
                int nextTime = next[1];
    
                if (spendTime[nextNode] > spendTime[node] + nextTime) {
                    return true;
                }
            }
        }

        return false;
    }
}
