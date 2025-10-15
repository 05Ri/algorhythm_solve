package baekjoon.BOJ_1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int nextNodeNum;
    int distance;

    public Node(int nextNodeNum, int distance) {
        this.nextNodeNum = nextNodeNum;
        this.distance = distance;
    }
}

// 클래스 객체의 우선순위를 정하기 위한 클래스
class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.distance - o2.distance;
    }

}

public class Main_메모리초과 {
    // 시작점으로부터 다른 노드의 최단 거리를 저장할 배열
    public static int[] shortestDistanceArr;
    // 노드의 연결을 담을 배열
    public static List<Node>[] graph;
    // 노드를 방문했는지 볼 배열
    public static boolean[] visited;
    // 짧은 거리들의 배열
    public static int[] minDistanceArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수와 간선의 개수
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 그래프 및 최소거리 배열 초기화
        graph = new ArrayList[V + 1];
        shortestDistanceArr = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<Node>();
            shortestDistanceArr[i] = Integer.MAX_VALUE;
        }

        // 방문 배열 초기화
        visited = new boolean[V + 1];

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
        minDistanceArr = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            minDistanceArr[i] = Integer.MAX_VALUE;
        }

        // 다익스트라 실행
        dijkstra(K);

        // 정답 출력
        for (int i = 1; i <= V; i++) {
            if (minDistanceArr[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }
            else {
                System.out.println(minDistanceArr[i]);
            }
        }
    }

    /**
     * 다익스트라 탐색
     * @param K 시작할 노드 번호
     */
    public static void dijkstra(int K) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new NodeComparator());
        minDistanceArr[K] = 0;
        queue.offer(new Node(K, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            visited[curr.nextNodeNum] = true;

            for (Node node : graph[curr.nextNodeNum]) {
                if (visited[node.nextNodeNum]) continue;

                if (minDistanceArr[node.nextNodeNum] < minDistanceArr[curr.nextNodeNum] + node.distance) continue;

                minDistanceArr[node.nextNodeNum] = minDistanceArr[curr.nextNodeNum] + node.distance;
                queue.offer(new Node(node.nextNodeNum, minDistanceArr[node.nextNodeNum]));
            }
        }
    }
}
