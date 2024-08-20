package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1012_유기농_배추 {
	// 배추밭
	public static int[][] cabbageFarm;
	// 방문배열
	public static int[][] visited;
	// 가로 길이
	public static int M;
	// 세로 길이
	public static int N;

	// 델타배열: 상하좌우
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 테스트 케이스
		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 필요한 지렁이 개수 카운트
			int whiteWorm = 0;

			// 첫째줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 배추를 심은 배추밭의 가로길이 M
			M = Integer.parseInt(st.nextToken());
			// 세로길이 N
			N = Integer.parseInt(st.nextToken());

			// 배추밭 생성
			cabbageFarm = new int[M][N];
			// 방문 했던 곳인지 표시하는 배열 생성
			visited = new int[M][N];

			// 배추가 심어져 있는 위치의 개수 K
			int K = Integer.parseInt(st.nextToken());

			for (int k = 0; k < K; k++) {
				// 배추의 위치 줄 입력받고
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				// 배추밭에 넣기
				cabbageFarm[x][y] = 1;
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (visited[x][y] != 1 && cabbageFarm[x][y] == 1) {
						// 배추 흰 지렁이의 개수를 하나 카운트해준다.
						whiteWorm++;
						// 해당 좌표의 방문배열을 1로 만들어주고
						visited[x][y] = 1;
						// 깊이 우선 탐색을 실시한다.
						dfs(x, y);
					}
				}
			}
			// 지렁이 개수가 답
			System.out.println(whiteWorm);
		}
	}

	public static void dfs(int x, int y) {
		// 상하좌우로 탐색한다.
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 만약 범위 내에서...
			if (0 <= nx && nx < M && 0 <= ny && ny < N) {
				// 탐색된 좌표가 방문하지 않았고 양배추가 있다면
				if (visited[nx][ny] != 1 && cabbageFarm[nx][ny] == 1) {
					// 그 좌표를 방문처리하고
					visited[nx][ny] = 1;
					// 다음 깊이우선탐색을 한다.
					dfs(nx, ny);
				}
			}
		}
	}
}
