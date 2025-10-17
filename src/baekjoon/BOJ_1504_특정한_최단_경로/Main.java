package baekjoon.BOJ_1504_특정한_최단_경로;

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
    // 최대값 정의
    public static final int INF = Integer.MAX_VALUE;
    // 그래프
    public static List<int[]>[] graph;
    // 정점의 개수
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 개수 N
        N = Integer.parseInt(st.nextToken());
        // 간선 개수 E
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1 ; i <= N; i++) {
            graph[i] = new ArrayList<int[]>();
        }

        // 노드 사이의 간선 정보 받아오기
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());

            // a 정점
            int a = Integer.parseInt(st.nextToken());
            // b 정점
            int b = Integer.parseInt(st.nextToken());
            // a와 b 사이의 거리
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }

        // 포함되어야 하는 정점 2개
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 탐색 및 최소거리 저장
        int[] dist1 = dijkstra(1);
        int[] distV1 = dijkstra(v1);
        int[] distV2 = dijkstra(v2);

        long path1 = (long) dist1[v1] + distV1[v2] + distV2[N];
        long path2 = (long) dist1[v2] + distV2[v1] + distV1[N];

        long answer = path1 < path2 ? path1 : path2;

        // 정답 출력
        System.out.println(answer >= INF ? -1 : answer);
    }

    /**
     * 다익스트라
     * @param start 시작 정점
     */
    private static int[] dijkstra(int start) {
        // 최소거리 저장할 배열
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));
        queue.offer(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int thisNode = curr[0];
            int thisDist = curr[1];
            
            if (thisDist > dist[thisNode]) continue;

            for (int[] next : graph[thisNode]) {
                int nextNode = next[0];
                int nextDist = next[1];
                int calculateDist = thisDist + nextDist;

                if (dist[nextNode] > calculateDist) {
                    dist[nextNode] = calculateDist;
                    queue.offer(new int[] {nextNode, calculateDist});
                }
            }
        }
        return dist;
    }
}
