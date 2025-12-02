package baekjoon.BOJ_1238_파티;

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
    static final int INF = Integer.MAX_VALUE;   // 최대값 정의

    static int N;   // 도시 개수
    static int X;   // 목적지

    static List<int[]>[] map;   // 도시 정보
    static List<int[]>[] reverseMap;    // 역방향 도시 정보

    static int[] goDest;   // 가는길의 최소 비용 저장배열
    static int[] backHome;   // 오는길의 최소 비용 저장배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());   // 도시 개수
        int M = Integer.parseInt(st.nextToken());   // 경로 개수
        X = Integer.parseInt(st.nextToken());   // 목적지

        map = new ArrayList[N + 1];   // 도시 정보
        reverseMap = new ArrayList[N + 1];    // 역방향 도시 정보
        for (int i = 0; i <= N; i++) {
            map[i] = new ArrayList<int[]>();
            reverseMap[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            map[start].add(new int[] {end, time});
            reverseMap[end].add(new int[] {start, time});
        }

        goDest = new int[N + 1];
        backHome = new int[N + 1];
        Arrays.fill(goDest, INF);
        Arrays.fill(backHome, INF);
        goDest[X] = 0;
        backHome[X] = 0;

        // 다익스트라
        goDijkstra();
        backDijkstra();

        // 출력부
        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, goDest[i] + backHome[i]);
        }
        
        System.out.println(maxTime);
    }

    /**
     * 목적지로 가는 길 다익스트라
     */
    static void goDijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[N + 1];

        pq.offer(new int[] {X, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCity = curr[0];
            int currTime = curr[1];

            if (visited[currCity]) continue;
            visited[currCity] = true;

            for (int[] next : reverseMap[currCity]) {
                int nextCity = next[0];
                int nextTime = next[1];

                if (goDest[nextCity] > currTime + nextTime) {
                    goDest[nextCity] = currTime + nextTime;
                    pq.offer(new int[] {nextCity, goDest[nextCity]});
                }
            }
        }
    }

    /**
     * 집으로 돌아오는 길 다익스트라
     */
    static void backDijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[N + 1];

        pq.offer(new int[] {X, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCity = curr[0];
            int currTime = curr[1];

            if (visited[currCity]) continue;
            visited[currCity] = true;

            for (int[] next : map[currCity]) {
                int nextCity = next[0];
                int nextTime = next[1];

                if (backHome[nextCity] > currTime + nextTime) {
                    backHome[nextCity] = currTime + nextTime;
                    pq.offer(new int[] {nextCity, backHome[nextCity]});
                }
            }
        }
    }
}
