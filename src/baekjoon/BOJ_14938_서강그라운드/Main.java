package baekjoon.BOJ_14938_서강그라운드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<int[]>[] map;
    static int[] region;
    static int N;
    static int M;
    static int maxItems;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        region = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int start = 1; start <= N; start++) {
            region[start] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList[N + 1];
        for (int start = 1; start <= N; start++) {
            map[start] = new ArrayList<>();
        }

        for (int start = 0; start < R; start++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            map[a].add(new int[] {b, l});
            map[b].add(new int[] {a, l});
        }

        maxItems = 0;

        for (int start = 1; start <= N; start++) {
            dijkstra(start);
        }

        System.out.println(maxItems);
    }

    static void dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        q.offer(new int[] {start, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currRegion = curr[0];
            int currDist = curr[1];

            if (currDist > dist[currRegion]) continue;

            for (int[] next : map[currRegion]) {
                int nextRegion = next[0];
                int nextDist = next[1];

                if (dist[nextRegion] > currDist + nextDist) {
                    dist[nextRegion] = currDist + nextDist;
                    q.offer(new int[] {nextRegion, dist[nextRegion]});
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= M) {
                sum += region[i];
            }
        }

        maxItems = Math.max(maxItems, sum);
    }
}
