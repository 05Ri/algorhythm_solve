package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 강사님이 설명해주시면서 짠 코드다...!
public class BOJ_1260_DFS와_BFS_관계2차원배열 {
	// 필요 변수 선언
	// 노드의 개수 N, 간선의 개수 M, 탐색을 시작할 정점의 번호 V
	public static int N, M, V;

	// 노드끼리 연결할 관계의 배열
	public static int[][] adjMatrix;
	// 방문을 했는지 안했는지 나타내주는 배열
	public static int[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫번째 줄 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adjMatrix = new int[N + 1][N + 1];
		visited = new int[N + 1];

		// M개의 간선 개수에 따른 관계 배열 만들기
		for (int i = 0; i < M; i++) {
			// 간선이 연결하는 두 정점의 번호를 나타내는 라인 입력받기
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 관계 배열 두 노드에 추가
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;

//			System.out.println(Arrays.deepToString(adjMatrix));
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

		// next가 노드의 개수만큼 돌고
		for (int next = 1; next <= N; next++) {
			// 방문처리가 되었거나 관계 배열의 노드 다음이 없다면 다음 노드로 넘어간다.
			if (visited[next] == 1 || adjMatrix[node][next] == 0)
				continue;

			// 방문한 노드를 1로 만들고
			visited[next] = 1;

			// 다음 깊이 우선 탐색을 실시한다.
			dfs(next);
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

			// next가 노드의 개수만큼 돌고
			for (int next = 1; next <= N; next++) {
				// 만약 방문했었거나 관계 배열의 다음이 0이라면 다음 노드 진행한다.
				if (visited[next] == 1 || adjMatrix[node][next] == 0)
					continue;
				
				// 방문 처리 해주고
				visited[next] = 1;
				
				// 큐에 넣어준다
				q.offer(next);
			}
		}
	}
}
