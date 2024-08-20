package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1303_전쟁_전투 {
	// 가로, 세로 크기
	public static int N, M;
	// 옷색깔 배열
	public static char[][] color;
	// 방문배열
	public static boolean[][] visited;
	// 아군 전투력
	public static int whiteAttack;
	// 적군 전투력
	public static int blueAttack;
	// dfs에 들어갔을 때 카운트 해줄 변수
	public static int cnt;

	// 델타 - 상하좌우
	public static final int[] dr = { -1, 1, 0, 0 };
	public static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N과 M 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 할당받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 병사들 위치 파악하기
		color = new char[M][N];
		for (int r = 0; r < M; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				color[r][c] = str.charAt(c);
			}
		}

		// 방문배열 초기화
		visited = new boolean[M][N];
		
		// 전투력 초기화
		whiteAttack = 0;
		blueAttack = 0;

		// 전투력 측정하기
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				// 방문한 곳이라면 건너뛰어준다
				if (visited[r][c] == true)
					continue;

				// 뭉쳐있는 병사 카운트 시작 1부터
				cnt = 1;
				// 현재 있는 곳을 방문처리해주고
				visited[r][c] = true;
				// dfs 탐색 시작해준다.
				dfs(r, c, color[r][c]);

				// dfs 종료 후 cnt와 군복 색에 따른 분기
				switch (color[r][c]) {
				case 'W':
					whiteAttack += cnt * cnt;
					break;
				case 'B':
					blueAttack += cnt * cnt;
					break;
				}
			}
		}

		System.out.println(whiteAttack + " " + blueAttack);
	}

	/**
	 * 깊이우선탐색
	 * 
	 * @param r  행
	 * @param c  열
	 * @param cl 군복 색깔
	 */
	public static void dfs(int r, int c, char cl) {
		for (int d = 0; d < 4; d++) {
			// 델타를 더해준다.
			int nr = r + dr[d];
			int nc = c + dc[d];

//			System.out.println(nr + " " + nc + " " + cnt);
			// nr과 nc가 범위 안에 있고 군복색이 일치하고 방문을 하지 않았다면
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && color[nr][nc] == cl && visited[nr][nc] == false) {
				// 해당좌표 방문처리해주고
				visited[nr][nc] = true;
				// 병사 수도 하나 세어주고
				cnt++;
				// 다음 깊이우선탐색을 실시한다.
				dfs(nr, nc, cl);
			}
		}
	}
}
