package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1240_노드사이의_거리 {
	// 인접리스트를 저장할 배열
	static List<int[]>[] adjList;
	// 방문체크 배열
	static boolean[] visited;
	// 노드의 개수
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 노드의 개수
		N = Integer.parseInt(st.nextToken());
		// 알고 싶은 노드 쌍의 개수
		int M = Integer.parseInt(st.nextToken());
		
		// 인접리스트 초기화
		adjList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		// 트리 상에 연결된 두 점과 거리 입력받기
		for (int $ = 0; $ < N - 1; $++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			adjList[a].add(new int[] {b, dist});
			adjList[b].add(new int[] {a, dist});
		}
		
//		// 잘 입력받았는지 테스트
//		for (int i = 0; i < N + 1; i++) {
//			System.out.print(i + ": ");
//			for (int j = 0; j < adjList[i].size(); j++) {
//				System.out.print(Arrays.toString(adjList[i].get(j)) + " ");
//			}
//			System.out.println();
//		}
		
		// 거리를 알고 싶은 두 노드 입력받아서 거리 계산
		for (int $ = 0; $ < M; $++) {
			visited = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			visited[a] = true;
			calculateDistance(a, b, 0);
		}
		
		System.out.println(sb);
	}

	private static void calculateDistance(int a, int b, int distance) {
		if (a == b) {
			sb.append(distance).append("\n");
			return;
		}
		
		for (int k = 0; k < adjList[a].size(); k++) {
			// 인접 리스트에서 다음 노드의 정보를 빼온다.
			int[] nextNode = adjList[a].get(k);
			// 다음 노드의 번호
			int nodeNum = nextNode[0];
			// 다음 노드의 거리
			int nnd = nextNode[1];
			
			// 다음 노드가 방문처리 되어있다면 건너뛰어준다.
			if (visited[nodeNum])
				continue;
			
			visited[nodeNum] = true;
			distance += nnd;
			calculateDistance(nodeNum, b, distance);
			distance -= nnd;
		}
	}
}
