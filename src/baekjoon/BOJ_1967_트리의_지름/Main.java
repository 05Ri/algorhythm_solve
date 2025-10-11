package baekjoon.BOJ_1967_트리의_지름;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    public int nextNodeNum;
    public int distance;

    public Node(int nextNodeNum, int distance) {
        this.nextNodeNum = nextNodeNum;
        this.distance = distance;
    }
}

public class Main {
    // 노드 번호에 해당하는 List의 배열 = 트리
    public static List<Node>[] tree;
    // 노드를 방문했는지 체크하는 배열
    public static boolean[] visited;
    // 먼 거리에 있는 노드 번호와
    public static int mostFarNode = 0;
    // 계산된 최대 거리 저장
    public static int maxDistance = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드 개수 n
        int n = Integer.parseInt(br.readLine());

        // 트리 초기화
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<Node>();
        }

        // 노드의 부모자식 관계와 간선 정보 받기
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int parentNum = Integer.parseInt(st.nextToken());
            int childNum = Integer.parseInt(st.nextToken());
            int distanceNum = Integer.parseInt(st.nextToken());

            tree[parentNum].add(new Node(childNum, distanceNum));
            tree[childNum].add(new Node(parentNum, distanceNum));
        }

        // 방문 배열 초기화
        visited = new boolean[n + 1];
        // 루트 노드 방문 처리
        visited[1] = true;
        // 루트 노드와 가장 먼 거리에 있는 노드 찾기
        dfs(1, 0);

        // System.out.println(mostFarNode + " " + maxDistance);

        // 탐색 후 가장 먼 노드에서 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        visited[mostFarNode] = true;
        dfs(mostFarNode, 0);

        // 답 출력
        System.out.println(maxDistance);
    }

    /**
     * 깊이 우선 탐색
     * @param index 트리의 노드 번호
     * @param distance 거리
     */
    public static void dfs(int index, int distance) {
        
        // 거리가 최대거리보다 크다면 그 노드 번호와 노드까지의 최대거리 저장
        if (distance > maxDistance) {
            maxDistance = distance;
            mostFarNode = index;
        }
        
        for (Node node : tree[index]) {
            // 방문한 노드라면 건너뛰기
            if (visited[node.nextNodeNum]) continue;
            
            visited[node.nextNodeNum] = true;
            dfs(node.nextNodeNum, distance + node.distance);
        }
    }
}
