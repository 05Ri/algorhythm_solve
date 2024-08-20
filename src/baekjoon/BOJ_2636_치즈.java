package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
	// 치즈의 크기 (세로, 가로)
	// 다 녹는데 걸리는 시간 (full melting hour)
	// 녹기 전의 치즈 칸 수 (before melting count)
	static int N, M, FMH = 0, BMC = 1;
	// 탐색을 시작할 각 최소 좌표, 탐색을 마칠 끝 최대 좌표
	static int startR, startC, endR, endC;
	// 탐색하며 얻은 치즈의 각각 가장 작은 좌표 값, 가장 큰 좌표 값
	static int minR = 101, minC = 101, maxR = 0, maxC = 0;
	// 치즈
	static int[][] cheeze;
	// 방문 배열
	static boolean[][] visited;

	// 델타
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 치즈 초기화 및 입력받기
		cheeze = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				cheeze[r][c] = Integer.parseInt(st.nextToken());

				// 입력받을 때 치즈가 있는 좌표 X, Y의 최소값, 최대값을 구해놓는다.
				if (cheeze[r][c] == 1) {
					if (r <= minR)
						minR = r - 1;
					if (c <= minC)
						minC = c - 1;
					if (r >= maxR)
						maxR = r + 1;
					if (c >= maxC)
						maxC = c + 1;
				}
			}
		}

		// 저장해놓은 좌표 입력
		startR = minR;
		startC = minC;
		endR = maxR;
		endC = maxC;

		// 치즈가 다 녹기 한시간 전 BMC 저장
		int oneHourBefore = 0;

		// 녹는 치즈가 없을 때까지
		while (BMC > 0) {
			// 방문 배열 초기화
			visited = new boolean[N][M];
			// 녹는 치즈 초기화
			BMC = 0;
			// 가장 작은 값, 큰 값 초기화
			minR = 101;
			minC = 101;
			maxR = 0;
			maxC = 0;

			visited[startR][startC] = true;
			// 녹는 치즈 개수 카운팅
			calMeltCheeze(startR, startC);

			// 탐색을 시작할 좌표, 탐색 끝 좌표를 지정한다.
			startR = minR;
			startC = minC;
			endR = maxR;
			endC = maxC;

//			// 테스트
//			System.out.printf("%d %d %d %d\n", minR, minC, maxR, maxC);
//
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(cheeze[i]));
//			}

			// 탐색 시작지점이 101 또는 끝 지점이 0이면 탈출한다.
			if (startR == 101 || startC == 101 || endR == 0 || endC == 0)
				break;

			// 녹기 전의 치즈 칸 수가 0이 아니라면 저장해놓는다.
			if (BMC > 0) {
				oneHourBefore = BMC;
				FMH++;
			}
		}

		System.out.println(FMH);
		System.out.println(oneHourBefore);
	}

	private static void calMeltCheeze(int r, int c) {
		// 만약 치즈를 만났다면
		if (cheeze[r][c] == 1) {
			// 치즈를 녹이고
			cheeze[r][c] = 0;
			// 녹은 치즈 카운트를 해준다
			BMC++;
			// 녹은 치즈의 각 좌표가 최소 및 최대라면
			// 공기의 공간 확보를 위해 -1 및 +1 해주고 저장해준다.
			if (minR >= r)
				minR = r - 1;
			if (minC >= c)
				minC = c - 1;
			if (maxR <= r)
				maxR = r + 1;
			if (maxC <= c)
				maxC = c + 1;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (startR <= nr && nr <= endR && startC <= nc && nc <= endC && !visited[nr][nc]) {
				visited[nr][nc] = true;
				calMeltCheeze(nr, nc);
			}
		}
	}
}
