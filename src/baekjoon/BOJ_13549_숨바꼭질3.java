package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질3 {
	// 수빈의 위치, 동생의 위치, 동생을 찾는데 걸리는 시간
	static int N, K, findTime;
	// 방문처리
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K <= N)
			System.out.println(N - K);
		else {
			findTime = 0;
			visited = new boolean[200000];

			bfs();

			System.out.println(findTime);
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N);

		while (!queue.isEmpty()) {
			// 같은 시간대를 큐에 넣어주기
			int size = queue.size();
			for (int $ = 0; $ < size; $++) {
				int q = queue.poll();

				// 동생의 위치라면 함수를 탈출한다.
				if (q == K)
					return;
				
				// 좌표가 0일 때 2가 곱해지면 무한루프를 돌게 되므로 처리
				if (q == 0) {
					queue.offer(0);
					visited[0] = true;
					continue;
				} else {
					// 자기 자신도 넣어줘야 +-1 할 때 남아있을 수 있다.
					queue.offer(q);
					visited[q] = true;
					
					// 같은 시간대들을 큐에 넣어준다.(2배씩 간 좌표들)
					for (int i = q; i <= 100000; i *= 2) {
						// 동생의 위치라면 함수를 탈출한다.
						if (i == K)
							return;
						
						// 동생의 위치의 * 2가 되었다면 건너뛴다.
						if (i > 2 * K)
							continue;
						
						// 방문처리가 되지 않았다면 큐에 넣어준다.
						if (!visited[i]) {
							queue.offer(i);
							visited[i] = true;
						}
					}
				}
			}
//			System.out.println(queue);
			
			// +1초대의 위치들을 큐에 넣어준다.(한칸 앞/뒤로 간 좌표들)
			findTime++;
			size = queue.size();
			for (int $ = 0; $ < size; $++) {
				int q = queue.poll();

				// 1. 한 칸 뒤로 갈 때
				int qm = q - 1;
				if (0 <= qm && qm <= 100000 && !visited[qm]) {
					queue.offer(qm);
					visited[qm] = true;
				}

				// 2. 한 칸 앞으로 갈 때
				int qp = q + 1;
				if (0 <= qp && qp <= 100000 && !visited[qp]) {
					queue.offer(qp);
					visited[qp] = true;
				}
			}
//			System.out.println(queue);
//			System.out.println();
		}
	}
}
