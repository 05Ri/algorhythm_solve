package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1267_작업순서_Stack {
	static Stack<Integer> stack = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	static int V;
	static boolean[][] adj;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.setLength(0);
			// 먼저 테스트 케이스를 올려둔다
			sb.append(String.format("#%d ", tc));
			
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 인접행렬(가중치가 없으므로 boolean으로 해주었다)
			adj = new boolean[V + 1][V + 1];
			// 진입 차수
			int[] degree = new int[V + 1];
			
			// 인접행렬 및 진입 차수 입력받기
			st = new StringTokenizer(br.readLine());
			for (int $ = 0; $ < E; $++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a에서 b로 연결되기 때문에 그 방향만 true
				adj[a][b] = true;
				// b에 진입하므로 차수 올려주기
				degree[b]++;
			}
			
			// 방문배열 초기화
			visited = new boolean[V + 1];
			
			// 진입차수가 0인 노드들 dfs 진입시키기
			for (int i = 1; i < V + 1; i++) {				
				if (degree[i] == 0)
					dfs(i);
			}
			
			// dfs를 다 돌았다면 stack에 들어있는 값들을 꺼내서 올려준다
			while(!stack.isEmpty()) {
				sb.append(stack.pop()).append(" ");
			}
			
			System.out.println(sb);
		}
	}

	private static void dfs(int node) {
		visited[node] = true;
		
		for (int i = 1; i < V + 1; i++) {
			// 방문처리가 되지 않았고 다음 노드와 연결되어 있으면
			if (!visited[i] && adj[node][i]) {
				// 다음 dfs로 진입
				dfs(i);
			}
		}
		
		// 들어간 곳이 끝자락이어서 반복문을 탈출했다면 stack에 넣어준다
		stack.push(node);
	}
}
