package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_2667_단지번호붙이기 {
	// 지도
	public static int[][] map;
	// 방문 배열
	public static int[][] visited;
	// 지도 한 변의 크기
	public static int N;
	// 단지 수를 세는 변수
	public static int complexCnt;
	// 단지에 있는 집의 개수를 저장할 큐
	public static PriorityQueue<Integer> complexInHouse;
	// 집 개수를 셀 변수
	public static int houseCnt;

	// 델타 상하좌우
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 지도 크기 입력받기
		N = Integer.parseInt(br.readLine());
		// 지도 생성
		map = new int[N][N];
		// 방문 배열 생성
		visited = new int[N][N];

		for (int r = 0; r < N; r++) {
			// 한 줄을 입력받고
			String line = br.readLine();
			for (int c = 0; c < N; c++) {
				// 각 문자를 char로 떼네고 int로 변환하여 저장
				map[r][c] = line.charAt(c) - '0';
			}
		}

		// 단지 수를 세는 변수를 초기화해주고
		complexCnt = 0;
		// 해당 단지의 집의 개수를 저장할 큐 초기화
		complexInHouse = new PriorityQueue<>();
		// 집 개수 초기화
		houseCnt = 0;

		// 탐색 시작
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 방문 처리가 되지 않은 집을 발견했다면
				if (visited[r][c] != 1 && map[r][c] == 1) {
					// 방문처리를 하고
					visited[r][c] = 1;
					// 단지를 정해주고
					++complexCnt;
					// 단지의 집 개수를 늘려주고
					houseCnt++;
					// 깊이 우선 탐색 시작
					dfs(r, c);

					// 깊이 우선 탐색이 끝나면 단지의 집 개수를 넣고 초기화
					complexInHouse.offer(houseCnt);
					houseCnt = 0;
				}
			}
		}

		// 단지의 개수를 출력하고
		System.out.println(complexCnt);
		// 각 단지의 집의 개수를 출력
		while (!complexInHouse.isEmpty()) {
			System.out.println(complexInHouse.poll());
		}
	}

	/**
	 * 깊이 우선 탐색
	 * 
	 * @param r 행 좌표
	 * @param c 열 좌표
	 */
	public static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 탐색할 곳이 범위 내에 있고 그곳에 방문처리가 되지 않은 집이 있다면
			if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] != 1 && map[nr][nc] == 1) {
				// 방문처리를 하고
				visited[nr][nc] = 1;
				// 단지의 집 개수를 늘려주고
				houseCnt++;
				// 다음 깊이 우선 탐색 시작
				dfs(nr, nc);
			}
		}
		// 상하좌우에 아무것도 없다면 끝
		return;
	}
}
