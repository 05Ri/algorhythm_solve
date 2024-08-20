package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1267_작업순서_Queue {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.setLength(0);
			// 먼저 테스트 케이스를 올려둔다
			sb.append(String.format("#%d ", tc));
			
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 인접행렬(가중치가 없으므로 boolean으로 해주었다)
			boolean[][] adj = new boolean[V + 1][V + 1];
			// 진입차수
			int[] degree = new int[V + 1];
			
			// 인접행렬 및 진입 차수 입력받기
			st = new StringTokenizer(br.readLine());
			for (int $ = 0; $ < E; $++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a에서 b로 연결되기 때문에 그 방향만 true
				adj[a][b] = true;
				// b쪽으로 들어오기 때문에 b의 차수를 올려준다.
				degree[b]++;
			}
			
			Queue<Integer> queue = new LinkedList<>();
			
			// 0 차수인 노드들 먼저 큐에 넣어주기
			for (int i = 1; i < V + 1; i++) {
				if (degree[i] == 0)
					queue.offer(i);
			}
			
			while (!queue.isEmpty()) {
				// 하나 뽑고 sb에 넣어준다.
				int q = queue.poll();
				sb.append(q).append(" ");
				
				// 다음 노드를 찾아서 그 노드의 차수를 낮춰준다.
				for (int i = 1; i < V + 1; i++) {
					if (adj[q][i]) {
						degree[i]--;
						
						// 노드의 차수가 0이라면 큐에 넣어준다.
						if (degree[i] == 0)
							queue.offer(i);
					}
				}
			}
			System.out.println(sb);
		}
	}
}
