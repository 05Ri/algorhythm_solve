package baekjoon.BOJ_1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node1 {
    int nextNodeNum;
    int distance;

    public Node1(int nextNodeNum, int distance) {
        this.nextNodeNum = nextNodeNum;
        this.distance = distance;
    }
}

public class Main_시간초과 {
    // 시작점으로부터 다른 노드의 최단 거리를 저장할 배열
    public static int[] shortestDistanceArr;
    // 노드의 연결을 담을 배열
    public static List<Node1>[] graph;
    // 시작 노드 번호
    public static int K;
    // 짧은 거리들의 배열
    public static String[] minDistanceArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수와 간선의 개수 받기
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<Node1>();
        }

        // 최소거리 배열 초기화
        shortestDistanceArr = new int[V + 1];
        for (int value : shortestDistanceArr) {
            value = Integer.MAX_VALUE;
        }

        // 시작 정점의 번호
        K = Integer.parseInt(br.readLine());

        // 그래프 정보 입력받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            // 기준 노드 번호
            int currNode = Integer.parseInt(st.nextToken());
            // 다음으로 가는 노드 번호
            int nextNode = Integer.parseInt(st.nextToken());
            // 거리
            int distance = Integer.parseInt(st.nextToken());

            // 그래프에 간선 추가
            graph[currNode].add(new Node1(nextNode, distance));
        }

        // 가장 짧은 거리 노드 배열 초기화
        minDistanceArr = new String[V + 1];
        for (int i = 1; i <= V; i++) {
            minDistanceArr[i] = "INF";
        }

        /*
        시작 노드에서 모든 노드의 경로 구하기
        i) 이미 방문한 곳도 방문을 해서 거리를 측정해봐야 어느게 더 짧은건지 알 수 있음...?
            구현한다해도 시간 초과될 것 같음. 애초에 방향그래프라 방문처리가 필요할까...?
        ii) 시작노드를 제외한 모든 곳에서 dfs를 시작해보고 시작노드가 탐지되면 리턴하도록...!
            원하는 값이 안나옴... > 방향그래프이므로 역방향의 그래프를 새로 만들던가 해야하지만... 시간도 시간이고 메모리가 초과될 것 같다.
        iii) dfs를 하며 방문한 노드마다 최소 거리 값 비교...?
            확실하지만 시간 초과될 것 같다. 시도해보자. > 시간 초과가 되었다.
        iv) 루프가 되는 경로가 있다면 무한적으로 수를 비교해나갈것... > 가지치기를 했는데도 시간 초과
        */
        dfs(K, 0);

        for (int i = 1; i <= V; i++) {
            System.out.println(minDistanceArr[i]);
        }
    }

    /**
     * 깊이 우선 탐색 시작
     * @param nodeNum 시작할 노드 번호
     * @param distance 계산된 거리 값
     */
    public static void dfs(int nodeNum, int distance) {
        String minDistance = minDistanceArr[nodeNum];

        // 짧은 거리 배열에 있는 거리 값과 현재 거리 값 비교하여 짧은 값 저장
        if (minDistance == "INF") {
            minDistanceArr[nodeNum] = Integer.toString(distance);
        }
        else {
            minDistanceArr[nodeNum] = 
            Integer.parseInt(minDistance) < distance ? 
            minDistance : Integer.toString(distance);
        }

        // 만약 값을 갱신하지 못했다면 더 탐색하는 것이 의미가 없으므로 리턴
        if (minDistance == minDistanceArr[nodeNum]) return;
        
        for (Node1 node : graph[nodeNum]) {
            // 다음 탐색
            dfs(node.nextNodeNum, distance + node.distance);
        }
    }
}
