package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact {
	// 인접행렬 [from][to]
	static boolean[][] adjMatrix;
	// 연락을 중복으로 보내지 않도록 방문체크할 배열
	static boolean[] visited;
	// 비상연락을 받은 사람 중 가장 큰 값을 갖는 노드
	static int maxValVertex;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {

			st = new StringTokenizer(br.readLine());

			// 입력받는 데이터의 길이
			int length = Integer.parseInt(st.nextToken());
			// 시작점
			int start = Integer.parseInt(st.nextToken());

			// 인접행렬 초기화
			adjMatrix = new boolean[101][101];
			// 방문체크 배열 초기화
			visited = new boolean[101];
			// 가장 큰 값을 가지는 노드 초기화
			maxValVertex = 0;

			st = new StringTokenizer(br.readLine());

			// 입력받기
			for (int i = 0; i < length / 2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 인접행렬 from > to 를 true로 만들기
				adjMatrix[a][b] = true;
			}

			emergencyContect(start);

			System.out.printf("#%d %d\n", tc, maxValVertex);
		}
	}

	/**
	 * 비상 연락망을 돌리는 메소드
	 * 
	 * @param start : 시작점
	 */
	private static void emergencyContect(int start) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			// 마지막으로 연락받은 사람 중 가장 큰 값
			int maxValLevel = 0;
			
			// 같은 시간대에 연락 받는 사람들을 계산해준다.
			int len = queue.size();
			for (int pollNum = 0; pollNum < len; pollNum++) {
				int x = queue.poll();

				if (x > maxValLevel)
					maxValLevel = x;
				
				for (int i = 1; i < 101; i++) {
					// 방문처리가 되어있거나 연락이 가능한 상태가 아니면 건너뛴다.
					if (visited[i] || !adjMatrix[x][i])
						continue;
					
					visited[i] = true;
					queue.offer(i);
				}
			}
			
			// 계산이 다 되었는데도 큐가 비어있다면 이어져있는 곳에서는 모든 연락이 닿은 것이다.
			if (queue.isEmpty())
				maxValVertex = maxValLevel;
		}
	}
}
