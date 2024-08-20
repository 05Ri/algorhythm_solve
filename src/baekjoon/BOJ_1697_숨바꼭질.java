package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질 {
	// 수빈의 위치, 동생의 위치, 걸린 시간(답)
	public static int N, K, second = 1;
	// 방문 배열
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 방문 배열 초기화
		visited = new boolean[100_001];

		// 수빈의 위치
		N = Integer.parseInt(st.nextToken());
		// 동생의 위치
		K = Integer.parseInt(st.nextToken());

		// 수빈과 동생의 거리
		int distance = K - N;

		// 거리가 0 이하일 경우 그만큼 뒤로 가면 최소 거리다.
		if (distance <= 0) {
			System.out.println(-distance);
		} else {
			// 0보다 크면 bfs를 하고 답을 출력한다.
			bfs();
			System.out.println(second);
		}
	}

	/**
	 * 깊이 우선 탐색
	 */
	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();

		// 수빈의 위치의 방문 배열을 true로 설정해주어서 답으로 선택되지 않도록 한다.
		visited[N] = true;
		// 수빈의 위치를 넣어준다.
		queue.offer(N);

		// 큐에 값이 있는 동안
		while (!queue.isEmpty()) {
			
//			System.out.println(queue);
			int size = queue.size();
			
			for (int k = 0; k < size; k++) {
				// 큐에서의 값을 빼내서 저장해주고
				int a = queue.poll();
				
				// 그 위치에서 1초 후에 갈 수 있는 방법을 큐에 넣되,
				// 범위 안에 있고 방문처리가 안되어있을 때 넣어주고 방문처리를 해준다.
				// 다만 그 값이 동생의 위치라면 함수를 종료한다.

				// 앞으로 한 칸
				int n = a + 1;
				if (n == K) return;
				if (n <= 100_000 && !visited[n]) {
					queue.offer(n);
					visited[n] = true;
				}
				
				// 뒤로 한 칸
				n = a - 1;
				if (n == K) return;
				if (n >= 0 && !visited[n]) {
					queue.offer(n);
					visited[n] = true;
				}
				
				// 2배를 해준 칸
				n = a * 2;
				if (n == K) return;
				if (n <= 100_000 && !visited[n]) {
					queue.offer(n);
					visited[n] = true;
				}
			}
			
			// 동생의 위치를 발견 못했다면 시간을 추가한다.
			second++;
		}
	}
}
