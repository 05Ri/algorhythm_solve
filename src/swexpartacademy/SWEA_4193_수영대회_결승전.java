package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4193_수영대회_결승전 {
	// 바다 범위, 가장 적은 도착시간
	static int N, minTime;
	// 시작, 도착 지점
	static int[] startPoint, endPoint;
	// 바다 상태
	static int[][] sea;
	// 방문 체크
	static boolean[][] visited;

	// 델타 배열
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 최소시간 초기화 > 불가능할 때는 -1 출력
			minTime = -1;
			
			
			// 바다 크기 입력받기
			N = Integer.parseInt(br.readLine());

			// 바다 상태 입력받기
			sea = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					sea[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작지점 입력받기
			st = new StringTokenizer(br.readLine());
			startPoint = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			// 도착지점 입력받기
			st = new StringTokenizer(br.readLine());
			endPoint = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			
			// 방문배열 초기화
			visited = new boolean[N][N];
			
			DFS();

			System.out.printf("#%d %d\n", tc, minTime);
		}
	}

	private static void DFS() {
		Queue<int[]> queue = new LinkedList<>();
		// 시간을 0초로 잡아준다.
		int time = 0;

		queue.offer(startPoint);

		int stR = startPoint[0];
		int stC = startPoint[1];
		visited[stR][stC] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
					
			for (int $ = 0; $ < size; $++) {
				int[] qArr = queue.poll();
				
				int r = qArr[0];
				int c = qArr[1];
				
				// 만약 뽑은 값이 끝 지점과 같다면 최소시간에 저장해주고 함수를 종료한다.
				if (r == endPoint[0] && c == endPoint[1]) {
					minTime = time;
					return;
				}

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
						// 장애물을 만났을 경우 갈 수 없으므로 방문 체크 후 패스한다.
						if (sea[nr][nc] == 1) {
							visited[nr][nc] = true;
							continue;
						}
						
						// 소용돌이를 만났고 시간을 나눈 나머지가 2가 아니라면
						else if (sea[nr][nc] == 2 && time % 3 != 2) {
							// 소용돌이가 존재하므로 제자리를 넣어준다
							queue.offer(qArr);
						}
						
						// 이외의 값(0, 소용돌이가 사라짐)을 만났을 경우
						else {
							// 소용돌이가 없으므로 nr, nc를 넣어준다.
							queue.offer(new int[] { nr, nc });
							// 방문체크도 해둔다.
							visited[nr][nc] = true;
						}
					}
				}
			}
			// 큐를 다 넣어줬으면 시간을 더해준다.
			time++;
		}
	}
}
