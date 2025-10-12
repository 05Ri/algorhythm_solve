package baekjoon.BOJ_1167_트리의_지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Node {
    int nextNodeNum;
    int distance;

    public Node(int nextNodeNum, int distance) {
        this.nextNodeNum = nextNodeNum;
        this.distance = distance;
    }
}

public class Main {
    // 트리를 저장할 2차원 배열 생성
    public static List<Node>[] tree;
    // 정점 방문을 확인할 배열
    public static boolean[] visited;
    // 가장 긴 거리를 저장할 변수
    public static int maxDistance = 0;
    // 가장 긴 거리를 가진 정점번호를 저장할 변수
    public static int mostFarNodeNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 개수
        int V = Integer.parseInt(br.readLine());

        // 트리 초기화
        tree = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            tree[i] = new ArrayList<Node>();
        }

        // 정점 정보 입력받기
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            // 기준 정점 번호
            int nodeNum = Integer.parseInt(st.nextToken());

            while (true) {
                // 다른 정점 번호를 뽑고 음수인지 확인
                int nextNodeNum = Integer.parseInt(st.nextToken());
                if (nextNodeNum < 0) break;

                // 그 정점까지의 거리
                int distance = Integer.parseInt(st.nextToken());

                // 기준 정점 번호 리스트에 추가
                tree[nodeNum].add(new Node(nextNodeNum, distance));
            }
        }

        // 루트에서부터 가장 먼 노드 찾기
        visited = new boolean[V + 1];
        visited[1] = true;
        dfs(1, 0);
        
        // 가장 먼 노드에서 가장 먼 노드 찾기
        visited = new boolean[V + 1];
        visited[mostFarNodeNum] = true;
        dfs(mostFarNodeNum, 0);

        // 정답 출력
        System.out.println(maxDistance);
    }

    /**
     * 깊이 우선 탐색 함수
     * @param nodeNum 탐색 중인 노드 번호
     * @param distance 거리
     */
    public static void dfs(int nodeNum, int distance) {
        // 거리가 구해진 최장 길이보다 길다면 해당 노드 번호와 거리 저장
        if (distance > maxDistance) {
            maxDistance = distance;
            mostFarNodeNum = nodeNum;
        }

        for (Node node : tree[nodeNum]) {
            // 방문한 노드라면 건너뛰기
            if (visited[node.nextNodeNum]) continue;

            visited[nodeNum] = true;
            dfs(node.nextNodeNum, distance + node.distance);
        }
    }
}
