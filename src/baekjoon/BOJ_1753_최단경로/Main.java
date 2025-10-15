package baekjoon.BOJ_1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int nextNodeNum;
    int distance;

    public Node(int nextNodeNum, int distance) {
        this.nextNodeNum = nextNodeNum;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}

public class Main {
    // 시작점으로부터 다른 노드의 최단 거리를 저장할 배열
    public static int[] dist;
    // 노드의 연결을 담을 배열
    public static List<Node>[] graph;
    // 최댓값 정의
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 정점의 개수와 간선의 개수
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 그래프 및 최소거리 배열 초기화
        graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<Node>();
        }

        // 시작 노드의 번호
        int K = Integer.parseInt(br.readLine());

        // 그래프 정보 입력받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            // 기준 노드 번호
            int u = Integer.parseInt(st.nextToken());
            // 다음으로 가는 노드 번호
            int v = Integer.parseInt(st.nextToken());
            // 거리
            int w = Integer.parseInt(st.nextToken());

            // 그래프에 간선 추가
            graph[u].add(new Node(v, w));
        }

        // 가장 짧은 거리 노드 배열 초기화
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        // 다익스트라 실행
        dijkstra(K);

        // 정답 출력
        for (int i = 1; i < dist.length; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
        }
        System.out.println(sb);
    }

    /**
     * 다익스트라 탐색
     * @param K 시작할 노드 번호
     */
    public static void dijkstra(int K) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist[K] = 0;
        queue.offer(new Node(K, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.poll();

            // 이미 더 짧은 경로가 있으면 무시
            if (dist[curr.nextNodeNum] < curr.distance) continue;
            
            for (Node next : graph[curr.nextNodeNum]) {
                int calculateDist = dist[curr.nextNodeNum] + next.distance;
                if (dist[next.nextNodeNum] > calculateDist) {
                    dist[next.nextNodeNum] = calculateDist;
                    queue.offer(new Node(next.nextNodeNum, calculateDist));
                }
            }
        }
    }
}
