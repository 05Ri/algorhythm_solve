package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940_쉬운_최단거리 {
	public static int n, m, targetR, targetC;
	public static int[][] map, ans;
	public static boolean[][] visited;
	public static StringBuilder sb = new StringBuilder();

	public static final int[] dr = { -1, 1, 0, 0 };
	public static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		ans = new int[n][m];
		visited = new boolean[n][m];

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					targetR = r;
					targetC = c;
				} else if (map[r][c] == 0) {
					ans[r][c] = 0;
				} else {
					ans[r][c] = -1;
				}
			}
		}

		bfs();

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				sb.append(ans[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	public static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();

		int[] point = { targetR, targetC };

		queue.offer(point);
		visited[targetR][targetC] = true;
		ans[targetR][targetC] = 0;

		int cnt = 1;
		while (!queue.isEmpty()) {

			int size = queue.size();
			// 해당 depth에서 추가된 요소만큼 반복
			for (int i = 0; i < size; i++) {
				point = queue.poll();

				int r = point[0];
				int c = point[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr >= 0 && nr < n && nc >= 0 && nc < m && visited[nr][nc] == false) {
						visited[nr][nc] = true;
						if (map[nr][nc] != 0) {
							point = new int[3];
							point[0] = nr;
							point[1] = nc;
							queue.offer(point);
							ans[nr][nc] = cnt;
						}
					}
				}
			}
			cnt++;
		}
	}
}
