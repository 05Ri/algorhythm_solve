package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_등산로_조성 {
	// 부지 크기, 최대 공사 가능 깊이, 등산로 최대 길이
	static int N, K, maxLength;
	// 공사를 했는지 여부
	static boolean isConstruction;
	// 부지 정보
	static int[][] map;
	// 방문처리
	static boolean[][] visited;

	// 델타 배열
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 답 초기화
			maxLength = 1;

			// 부지 크기, 최대 공사 가능 깊이 입력받고 저장
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 부지 정보 입력 및 저장, 꼭대기 지점 저장
			int highHeight = 0;
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] > highHeight)
						highHeight = map[r][c];
				}
			}

			// 꼭대기 지점을 찾으면 긴 등산로 찾는 DFS 시작
			visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == highHeight) {
						isConstruction = false;
						dfs(r, c, 1);
					}
				}
			}

			System.out.printf("#%d %d\n", tc, maxLength);
		}
	}

	/**
	 * 꼭대기에서 시작하는 긴 등산로 찾기
	 * 
	 * @param r   꼭대기의 r좌표
	 * @param c   꼭대기의 c좌표
	 * @param cnt 등산로 길이
	 */
	private static void dfs(int r, int c, int cnt) {
//		System.out.printf("(%d, %d) %d / %d\n", r, c, map[r][c], cnt);
		visited[r][c] = true;

		// 사방 탐색 시작
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// nr과 nc가 범위 내에 있으면 계속 진행
			if (0 <= nr && nr < N && 0 <= nc && nc < N) {
				// 방문처리가 되어있으면 건너뛴다.
				if (visited[nr][nc])
					continue;

				// 다음에 갈 곳이 내가 밟은 높이 미만이면 바로 다음 dfs로 들어가준다
				if (map[r][c] > map[nr][nc])
					dfs(nr, nc, cnt + 1);
				// 다음 갈 곳이 내가 밟은 높이 이상이라면
				else {
					// 공사를 했는지 안했는지 확인하여 공사를 진행했다면 건너뛰고
					if (isConstruction)
						continue;

					// 안했다면 주어진 깊이만큼 깎아서 (내가 밟은 위치 - 1)이 가능한지(이하인지) 확인
					// 대신 땅을 깎는 것은 내 위치가 0 초과일 때까지만 가능
					if (map[r][c] > 0 && map[r][c] - 1 >= map[nr][nc] - K) {
						// 가능하다면 공사를 진행한걸로 바꿔주고
						// 다음 밟은 높이를 map[r][c] - 1로 만들고(만들기 전 tmp에 저장) 다음 깊이우선탐색 진행
						int tmp = map[nr][nc];

						isConstruction = true;
						map[nr][nc] = map[r][c] - 1;
						dfs(nr, nc, cnt + 1);
						// 원복
						map[nr][nc] = tmp;
						isConstruction = false;
					}
				}
			}
		}
		// 아무데도 갈 곳이 없으면 cnt와 최대 길이와 비교하여 저장해준다
		if (cnt > maxLength)
			maxLength = cnt;
		// 원복
		visited[r][c] = false;
	}
}
