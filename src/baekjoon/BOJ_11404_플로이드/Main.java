package baekjoon.BOJ_11404_플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    // 도시의 개수
    public static int n;
    // 버스 정보를 저장할 배열
    public static List<int[]>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 도시의 개수
        n = Integer.parseInt(br.readLine());
        // 버스의 개수
        int m = Integer.parseInt(br.readLine());

        map = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        // 버스 정보 입력받기
        boolean flag;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int departure = Integer.parseInt(st.nextToken());
            int arrival = Integer.parseInt(st.nextToken());
            int fee = Integer.parseInt(st.nextToken());

            flag = false;
            // 노선이 겹칠 수 있으므로 확인 후 싼 값으로 바꾸기
            for (int[] transport : map[departure]) {
                if (transport[0] != arrival) continue;

                if (transport[1] < fee) continue;

                transport[1] = fee;
                flag = true;
            }

            if (flag) continue;

            map[departure].add(new int[] {arrival, fee});
        }

        // 탐색
        for (int i = 1; i <= n; i++) {
            int[] result = dijkstra(i);

            for (int j = 1; j <= n; j++) {
                sb.append(result[j] == INF ? "0" : result[j]).append(" ");
            }
            // 끝의 공백 지우기
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        // 끝의 엔터 지우기
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }

    private static int[] dijkstra(int start) {
        int[] cheapestFee = new int[n + 1];
        Arrays.fill(cheapestFee, INF);
        cheapestFee[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCity = curr[0];
            int currFee = curr[1];

            if (cheapestFee[currCity] < currFee) continue;

            for (int[] next : map[currCity]) {
                int nextCity = next[0];
                int nextFee = next[1];

                int cost = nextFee + currFee;
                if (cheapestFee[nextCity] > cost) {
                    cheapestFee[nextCity] = cost;
                    pq.offer(new int[] {nextCity, cost});
                }
            }
        }
        return cheapestFee;
    }
}
