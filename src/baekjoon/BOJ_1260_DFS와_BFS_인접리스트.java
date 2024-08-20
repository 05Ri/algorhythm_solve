package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 강사님이 설명해주시면서 짠 코드를 리스트로 바꿔본 것이다...!
public class BOJ_1260_DFS와_BFS_인접리스트 {
	// 필요 변수 선언
	// 노드의 개수 N, 간선의 개수 M, 탐색을 시작할 정점의 번호 V
	public static int N, M, V;

	// 노드에 연결된 다른 노드와의 관계 맵
	public static Map<Integer, List<Integer>> adjMap;
	// 방문을 했는지 안했는지 나타내주는 배열
	public static int[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫번째 줄 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adjMap = new HashMap<>();

		visited = new int[N + 1];

		// M개의 간선 개수에 따른 관계 배열 만들기
		for (int i = 0; i < M; i++) {
			// 간선이 연결하는 두 정점의 번호를 나타내는 라인 입력받기
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 관계 배열 인덱스에 노드 배열을 넣어준다
			if (adjMap.get(a) == null)
				adjMap.put(a, new ArrayList<>());
			adjMap.get(a).add(b);

			// 양방향이므로 반대도 해준다.
			if (adjMap.get(b) == null)
				adjMap.put(b, new ArrayList<>());
			adjMap.get(b).add(a);
		}
		
		// 관계 배열들을 오름차순으로 만들어준다.
		for (int i = 0; i <= M; i++) {
			if(adjMap.get(i) == null)
				continue;
			
			Collections.sort(adjMap.get(i));
		}

		// 시작 노드는 방문 처리
		visited[V] = 1;
		// 깊이 우선 탐색 시작
		dfs(V);

		// 방문 배열 초기화
		visited = new int[N + 1];
		System.out.println();

		// 너비 우선 탐색 시작
		bfs();
	}

	public static void dfs(int node) {
		// 해당 노드를 일단 출력
		System.out.print(node + " ");

		// 가지 수가 0일 때의 대처
		if (adjMap.get(node) == null)
			return;
		
		// 해당 노드의 리스트를 검사하면서
		for (int i = 0; i < adjMap.get(node).size(); i++) {
			int a = adjMap.get(node).get(i);
			// 방문처리가 되지 않았다면
			if (visited[a] != 1) {
				// 방문한 노드를 1로 만들고
				visited[a] = 1;
				// 다음 깊이 우선 탐색을 실시한다.
				dfs(a);
			}
		}
	}

	public static void bfs() {
		// 너비 우선 탐색을 위한 큐
		Queue<Integer> q = new LinkedList<>();

		// 첫번째 노드를 추가하고 방문처리해준다.
		q.offer(V);
		visited[V] = 1;

		// 큐가 빌때까지
		while (!q.isEmpty()) {
			// 큐에서 하나 빼고 그 값을 출력
			int node = q.poll();
			System.out.print(node + " ");
			
			// 가지수가 0일 때의 대처
			if (adjMap.get(node) == null)
				return;

			// 해당 노드의 리스트를 검사하여
			for (int i = 0; i < adjMap.get(node).size(); i++) {
				int a = adjMap.get(node).get(i);
				// 방문처리가 되지 않았다면
				if (visited[a] != 1) {
					// 방문한 노드를 1로 만들고
					visited[a] = 1;
					// 큐에 넣어준다
					q.offer(a);
				}
			}
		}
	}
}
