package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
	// 영역의 길이
	public static int N;
	// 영역
	public static int[][] area;
	// 방문처리 배열
	public static int[][] visited;
	// 침수되는 상황 연출
	public static int K;
	
	// 델타 배열 (상하좌우)
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		area = new int[N][N];

		// 지반의 가장 낮은 높이와 높은 높이를 저장할 변수
		int highest = 0;
		int lowest = 101;

		// 영역 높이 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				highest = Math.max(highest, area[i][j]);
				lowest = Math.min(lowest, area[i][j]);
			}
		}
		
		// 안전한 영역 중 가장 많은 수
		int maxSafeArea = 1;

		for (K = lowest + 1; K <= highest; K++) {
			// 침수 뒤 안전한 영역 세어줄 변수
			int safeArea = 0;
			visited = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c] != 1 && area[r][c] >= K) {
						visited[r][c] = 1;
						safeArea++;
						dfs(r, c);
					}
				}
			}
			maxSafeArea = Math.max(maxSafeArea, safeArea);
		}
		System.out.println(maxSafeArea);
	}
	
	public static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] != 1 && area[nr][nc] >= K) {
				visited[nr][nc] = 1;
				dfs(nr, nc);
			}
		}
		return;
	}
}
