package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서_연결하기_재귀중복실패 {
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
		// 셀의 상태를 저장해놓는다.
		int[][] cellCopy = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cellCopy[r][c] = cell[r][c];
			}
		}

		for (int[] r : cell) {
			System.out.println(Arrays.toString(r));
		}
		System.out.printf("활성화 된 셀 개수: %d / 전선 길이: %d / 최대 켜진 코어: %d / 전선 최소 길이: %d\n\n", coreCnt, lineLength, activeMaxCores, minLine);

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 코어의 위치이고 방문체크가 되어있지 않다면 진행
				if (cell[r][c] == 1 && !visited[r][c]) {
					
					// 방문체크를 해준다.
					visited[r][c] = true;
					
					// 상하좌우 검사를 해본다.
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						// 전선을 직선으로 쭉 놨을 때 밖을 벗어나면 전원이 연결된 것이다.
						int lineCnt = 0;
						boolean isBreak = false;
						while (0 <= nr && nr < N && 0 <= nc && nc < N) {
							// 그러나 만약 직선으로 연결했는데 다른 코어나 전선을 만난다면
							// 연결이 불가능하므로 원복 후 다음 셀을 탐색한다.
							if (cell[nr][nc] > 0) {
								for (int hr = 0; hr < N; hr++) {
									for (int hc = 0; hc < N; hc++) {
										cell[hr][hc] = cellCopy[hr][hc];
									}
								}
								isBreak = true;
								break;
							}

							// 좌표에 전선을 놓는다.
							cell[nr][nc] = 2;
							// 전선 카운트를 늘려준다.
							lineCnt++;
							// 그 방향으로 계속 탐색한다.
							nr += dr[d];
							nc += dc[d];
						}

						// while의 범위를 초과해서 벗어났다면 cellCnt를 하나 늘린다.
						if (!isBreak)
							coreCnt++;

						// 최대 코어 수와 최소 전선을 갱신한다.
						if (coreCnt > activeMaxCores) {
							activeMaxCores = coreCnt;
							minLine = lineLength + lineCnt;
						} else if (coreCnt == activeMaxCores) {
							minLine = minLine < lineLength + lineCnt ? minLine : lineLength + lineCnt;
						}

						System.out.printf("다음 탐색 == (%d, %d), d: %d\n\n", r, c, d);
						
						// 다음 코어를 탐색한다.
						calculateLineLength(coreCnt, lineLength + lineCnt);

						// 재귀를 탈출한다면 core, cnt를 원복시킨다.
						if (!isBreak)
							coreCnt--;

						for (int hr = 0; hr < N; hr++) {
							for (int hc = 0; hc < N; hc++) {
								cell[hr][hc] = cellCopy[hr][hc];
							}
						} // 원복 for
					} // 델타 for
					// 델타를 나오면 방문체크를 해제한다
					visited[r][c] = false;
				} // 코어의 위치 if
			}
		}
		System.out.println("재귀 탈출");
	}
}
