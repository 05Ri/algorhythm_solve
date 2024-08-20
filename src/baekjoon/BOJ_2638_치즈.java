package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2638_치즈 {
	// 모눈종이 길이, 지난 시간
	static int N, M, meltingTime = 0;
	// 치즈의 왼쪽 위 좌표(rU, cL), 오른쪽 아래 좌표(rB, cR)
	static int minR, minC, maxR, maxC;
	// 모눈종이, 방문배열
	static int[][] graphPaper, visited;

	// 델타 (상, 하, 좌, 우)
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		minR = 0;
		minC = 0;
		maxR = N - 1;
		maxC = M - 1;

		graphPaper = new int[N][M];

		// 모눈종이의 상태를 입력받는다
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				graphPaper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 치즈가 있는지 없는지 확인
		boolean noMoreCheeze;
		// 찾아낼 좌표
		int upperR, leftC, bottomR, rightC;

		while (true) {
			// 각 변수들 초기화
			noMoreCheeze = true;
			upperR = 101;
			leftC = 101;
			bottomR = 0;
			rightC = 0;

			// 치즈가 있는지 확인한다.
			for (int r = minR; r <= maxR; r++) {
				for (int c = minC; c <= maxC; c++) {
					// 치즈가 있다면 탐색할 새 좌표를 찾는다.
					if (graphPaper[r][c] == 1) {
						noMoreCheeze = false;

						if (r < upperR)
							upperR = r;
						if (c < leftC)
							leftC = c;
						if (r > bottomR)
							bottomR = r;
						if (c > rightC)
							rightC = c;
					}
				}
			}

			// 치즈가 없다면 탈출
			if (noMoreCheeze)
				break;

			// 찾은 좌표 대입해주기
			minR = upperR - 1;
			minC = leftC - 1;
			maxR = bottomR + 1;
			maxC = rightC + 1;

//			System.out.printf("(%d, %d) (%d, %d)\n", minR, minC, maxR, maxC);

			// 바깥공기 2로 대입하기
			injectionAir(minR, minC, 0, 2);
//
//			for (int[] i : graphPaper) {
//				System.out.println(Arrays.toString(i));
//			}

			// 치즈 녹이는 함수
			meltingCheeze();
			
			// 바깥공기(2) 다시 0으로 돌려놓기
			injectionAir(minR, minC, 2, 0);
		}

		System.out.println(meltingTime);
	}

	private static void injectionAir(int r, int c, int nationAir, int changeAir) {
		// 외부공기를 주입
		graphPaper[r][c] = changeAir;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 범위 내에 있고 치즈의 바깥공기라면
			if (minR <= nr && nr <= maxR && minC <= nc && nc <= maxC && graphPaper[nr][nc] == nationAir) {
				// 다음 탐색
				injectionAir(nr, nc, nationAir, changeAir);
			}
		}
	}

	private static void meltingCheeze() {
		visited = new int[N][M];
		
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				// 치즈의 좌표라면
				if (graphPaper[r][c] == 1) {
					// 상하좌우를 비교하여 외부 공기의 개수를 세준다.
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						// 해당 치즈의 방문배열 카운트를 늘려준다.
						if (graphPaper[nr][nc] > 1)
							visited[r][c]++;
						
						// 방문배열 카운트가 2이상이 되었다면 치즈가 녹은 것이므로 모눈종이를 0으로 만들어준다.
						if (visited[r][c] >= 2)
							graphPaper[r][c] = 0;
					}
				}
			}
		}
		
		meltingTime++;
	}
}
