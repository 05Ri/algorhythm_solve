package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서_연결하기 {
	// N개의 셀, 전선의 최소 길이, 전원이 연결된 최대 코어 개수
	static int N, minLine, activeMaxCores;
	// N * N 의 셀
	static int[][] cell;
	// 코어 방문배열
	static boolean[][] visited;

	// 델타배열(상하좌우)
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			minLine = 987654321;
			activeMaxCores = 0;

			N = Integer.parseInt(br.readLine());

			cell = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					cell[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[N][N];

			calculateLineLength(0, 0);

			System.out.printf("#%d %d\n", tc, minLine);
		}

	}

	/**
	 * 전선 최대 길이 구하는 함수
	 * 
	 * @param coreCnt    전원이 켜진 코어의 개수
	 * @param lineLength 선의 길이
	 */
	private static void calculateLineLength(int coreCnt, int lineLength) {
		// 셀을 저장해둔다.
		int[][] cellCopy = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cellCopy[r][c] = cell[r][c];
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (cell[r][c] == 1 && !visited[r][c]) {
					
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						while (0 <= nr && nr < N && 0 <= nc && nc < N) {
							if (cell[nr][nc] > 0) {
								for (int hr = 0; hr < N; hr++) {
									for (int hc = 0; hc < N; hc++) {
										cell[hr][hc] = cellCopy[hr][hc];
									}
								}
								break;
							}
							
							cell[nr][nc] = 2;
							nr += dr[d];
							nc += dc[d];
						}
					}
					
					
				}
			}
		}
	}
}
