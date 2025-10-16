package baekjoon.BOJ_1916_최소비용_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 최댓값 지정
    public static final int INF = Integer.MAX_VALUE;
    // 버스가 가는 도시와 요금 정보를 저장할 배열
    public static List<int[]>[] fareTable;
    // 가장 싼 값을 저장할 배열
    public static int[] cheapestFee;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 개수
        int N = Integer.parseInt(br.readLine());
        // 버스의 개수
        int M = Integer.parseInt(br.readLine());

        // 버스요금표 및 가장 싼 값 배열 초기화
        fareTable = new ArrayList[N + 1];
        cheapestFee = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            fareTable[i] = new ArrayList<>();
            cheapestFee[i] = INF;
        }

        // 도시 간 비용 입력받기
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            // 출발 도시
            int departure = Integer.parseInt(st.nextToken());
            // 도착 도시
            int arrival = Integer.parseInt(st.nextToken());
            // 버스 비용
            int price = Integer.parseInt(st.nextToken());

            fareTable[departure].add(new int[] {arrival, price});
        }

        // 출발지와 도착지
        st = new StringTokenizer(br.readLine());
        int starting = Integer.parseInt(st.nextToken());
        int ending = Integer.parseInt(st.nextToken());

        // 다익스트라
        dijkstra(starting);

        // 답 출력
        System.out.println(cheapestFee[ending]);
    }

    private static void dijkstra(int starting) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));
        cheapestFee[starting] = 0;
        queue.offer(new int[] {starting, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currCity = curr[0];
            int currCost = curr[1];

            if (cheapestFee[currCity] < currCost) continue;
            
            for (int[] next : fareTable[currCity]) {
                int nextCity = next[0];
                int nextCost = next[1];

                int cost = currCost + nextCost;
                if (cheapestFee[nextCity] > cost) {
                    cheapestFee[nextCity] = cost;
                    queue.offer(new int[] {nextCity, cost});
                }
            }
        }
    }
}
