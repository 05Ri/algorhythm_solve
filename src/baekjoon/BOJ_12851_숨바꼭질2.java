package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851_숨바꼭질2 {
	// 수빈의 위치, 동생의 위치, 최단거리의 개수, 걸린 시간(답)
	public static int N, K, cnt = 0, second = 1;
	
	public static boolean isFind = false;
	
	public static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 수빈의 위치
		N = Integer.parseInt(st.nextToken());
		// 동생의 위치
		K = Integer.parseInt(st.nextToken());
		// 수빈과 동생의 거리
		int distance = K - N;
		check = new boolean[100_001];

		// 거리가 0 미만일 경우 그만큼 뒤로 가면 최소 거리다.
		if (distance <= 0) {
			System.out.println(-distance);
			System.out.println(1);
		} else {
			// 0보다 크면 bfs를 하고 답을 출력한다.
			bfs();
			System.out.println(second);
			System.out.println(cnt);
		}
	}

	/**
	 * 너비 우선 탐색
	 */
	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();

		// 수빈의 위치를 넣어준다.
		queue.offer(N);

		// 큐에 값이 있는 동안
		while (!queue.isEmpty()) {
			
			int size = queue.size();
			
			for (int k = 0; k < size; k++) {
//				System.out.println(queue);
				// 큐에서의 값을 빼내서 저장해주고
				int a = queue.poll();
				check[a] = true;
				
				// 그 위치에서 1초 후에 갈 수 있는 방법을 큐에 넣되,
				// 범위 안에 있고 방문처리가 안되어있을 때 넣어주고 방문처리를 해준다.
				// 그 값이 동생의 위치라면 cnt를 늘려주고 최소값을 찾았으므로 isFind를 true로 바꿔준다.

				// 앞으로 한 칸
				int n = a + 1;
				if (n == K) {
					isFind = true;
					cnt++;
				}
				if (n <= 100_000 && !check[n]) {
					queue.offer(n);
				}
				
				// 뒤로 한 칸
				n = a - 1;
				if (n == K) {
					isFind = true;
					cnt++;
				}
				if (n >= 0 && !check[n]) {
					queue.offer(n);
				}
				
				// 2배를 해준 칸
				n = a * 2;
				if (n == K) {
					isFind = true;
					cnt++;
				}
				if (n <= 100_000 && !check[n]) {
					queue.offer(n);
				}
			}
			
			// 동생의 위치를 발견 못했다면 시간을 추가하고 찾았다면 함수를 탈출해준다.
			if (isFind)
				return;
			else
				second++;
		}
	}
}
