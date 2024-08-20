package swexpartacademy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SWEA_5174_subtree {
	// 노드표 만들어줄 배열
	public static int[][] adjArr;
	// 방문처리 시켜줄 노드 번호의 배열
	public static int[] visited;
	// 기준 부모의 자식 개수를 세어줄 변수
	public static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt();

		for (int t = 1; t <= testCase; t++) {
			// 간선개수 E
			int e = sc.nextInt();
			// 기준 부모 rootNode
			int rootNode = sc.nextInt();

			// 이진 트리이므로 자식의 최대 개수는 2이다.
			adjArr = new int[e + 2][2];

			// 노드의 번호는 e + 1개까지 존재한다고 하였으므로
			visited = new int[e + 2];

			// 그래프를 그릴 조건들 입력받고 노드표 만들기
			for (int i = 0; i < e; i++) {
				// 부모 노드 입력받기
				int a = sc.nextInt();
				// 자식 노드 입력받기
				int b = sc.nextInt();

				// 노드표 만들어주기
				if (adjArr[a][0] == 0)
					adjArr[a][0] = b;
				else
					adjArr[a][1] = b;
			}
//			System.out.println(Arrays.deepToString(adjArr));

			// 0번은 존재하지 않고
			visited[0] = 1;
			// 기준 노드부터 방문할 것이므로 방문 처리
			visited[rootNode] = 1;
			// 카운트를 1로 만들어주고
			cnt = 1;

			// 기준 부모 노드에서 깊이 우선 탐색을 한다.
			dfs(rootNode);

			System.out.printf("#%d %d\n", t, cnt);
		}
	}

	/**
	 * 깊이 우선 탐색
	 * 
	 * @param node 탐색할 노드
	 */
	public static void dfs(int node) {

		for (int i = 0; i < 2; i++) {
			// 해당 노드에 이어진 노드를 탐색한다.
			int next = adjArr[node][i];

			// 이미 방문 처리가 된 노드라면 건너뛴다.
			if (visited[next] == 1)
				continue;

			// 방문하지 않았다면 방문처리를 해주고 카운트를 센 후
			visited[next] = 1;
			cnt++;
			// 다음 노드에서의 깊이 우선 탐색을 실시한다.
			dfs(next);
		}
	}
}
