package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7733_치즈_도둑_BFS {
	// 치즈 한 변의 길이, 제일 맛있는 정도, 제일 맛없는 정도
	static int N, ntst, wtst;
	// 치즈 맛 배열
	static int[][] cheeze;
	// 방문처리 배열
	static boolean[][] visited;

	// 델타배열
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			ntst = 0;
			wtst = 101;
			int mungtangMax = 1;

			// 치즈 맛 입력받기
			cheeze = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					cheeze[r][c] = Integer.parseInt(st.nextToken());

					// 제일 맛있는 정도와 제일 맛없는 정도 찾기
					if (cheeze[r][c] > ntst) {
						ntst = cheeze[r][c];
					}
					if (cheeze[r][c] < wtst) {
						wtst = cheeze[r][c];
					}
				}
			}

			for (int day = wtst; day <= ntst; day++) {
				visited = new boolean[N][N];
				int mungtang = 0;

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (visited[r][c] || cheeze[r][c] <= day)
							continue;
						
						mungtang++;
						visited[r][c] = true;
						bfs(r, c, day);
//						System.out.printf("day: %d / r: %d / c: %d\n", day, r, c);
					}
				}
				mungtangMax = mungtangMax > mungtang ? mungtangMax : mungtang;
			}

			System.out.printf("#%d %d\n", tc, mungtangMax);
		}
	}

	private static void bfs(int cr, int cc, int day) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { cr, cc });
		
		while (!queue.isEmpty()) {
			
			int length = queue.size();
			for (int k = 0; k < length; k++) {
				int[] point = queue.poll();

				int r = point[0];
				int c = point[1];

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && cheeze[nr][nc] > day) {
						visited[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
					}
				}
			}
		}
	}
}
