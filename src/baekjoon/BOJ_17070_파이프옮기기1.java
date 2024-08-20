package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	// 방 크기
	public static int N;
	// 방 배열
	public static int[][] room;
	// 가능한 수 세주기
	public static int cnt;

	// 델타 배열 오른쪽, 오른아래쪽 대각, 아래쪽
	public static final int[] dr = { 0, 1, 1 };
	public static final int[] dc = { 1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 집 크기 입력받기
		N = Integer.parseInt(br.readLine());
		// 방 배열 만들어주기
		room = new int[N][N];
		// 방 상태 입력받기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 0으로 초기화해주고
		cnt = 0;
		// dfs 시작
		dfs(0, 1, 0);

		System.out.println(cnt);
	}

	/**
	 * 깊이 우선 탐색
	 * 
	 * @param r         행
	 * @param c         열
	 * @param direction 어느 방향으로 가는지 (0: 우측, 1: 우하향 대각선, 2: 하향)
	 */
	public static void dfs(int r, int c, int direction) {
		System.out.println(r + " " + c + " " + direction);

		// r과 c가 마지막에 도달했다면 cnt 하나 세주고 dfs 종료
		if (r == N - 1 && c == N - 1) {
			cnt++;
			return;
		}

		// 우측의 경우
		if (direction != 2) {
			int nr = r + dr[0];
			int nc = c + dc[0];
			// nr과 nc가 범위 내에 있고
			if (nr < N && nc < N) {
				// 그 방향으로 가서 1이 아니면 dfs 탐색
				if (room[nr][nc] != 1)
					dfs(nr, nc, 0);
			}
		}

		// 우측 하향 대각선의 경우
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 범위 안에 없거나 장애물을 만나면 건너뛰고
			if (nr >= N || nc >= N)
				continue;

			// 장애물을 만나면 for문을 종료한다.
			if (room[nr][nc] == 1)
				break;

			// 우하향으로 갈 방향에서 장애물을 찾지 못했다면 깊이우선 탐색 실시한다.
			if (d == 2)
				dfs(r + 1, c + 1, 1);
		}

		// 하향의 경우
		if (direction != 0) {
			int nr = r + dr[2];
			int nc = c + dc[2];

			// nr과 nc가 범위 내에 있고
			if (nr < N && nc < N) {
				// 그 방향에 장애물이 없다면 탐색한다.
				if (room[nr][nc] != 1)
					dfs(nr, nc, 2);
			}
		}
	}
}
