package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2638_치즈_사이공간생각못함 {
	// 모눈종이 길이, 지난 시간
	static int N, M, meltingTime = 0;
	// 치즈의 왼쪽 위 좌표(rU, cL), 오른쪽 아래 좌표(rB, cR)
	static int minR, minC, maxR, maxC;
	// 모눈종이, 방문배열
	static int[][] graphPaper, visited;

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
			upperR = 0;
			leftC = 0;
			bottomR = 101;
			rightC = 101;

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
			upperR = minR;
			leftC = minC;
			bottomR = maxR;
			rightC = maxC;

//			System.out.printf("(%d, %d) (%d, %d)\n", minR, minC, maxR, maxC);

			// 치즈 녹이는 함수
			meltingCheeze();
		}

		System.out.println(meltingTime);
	}

	private static void meltingCheeze() {

		// 방문배열 초기화
		visited = new int[N][M];

		// 열 기준으로
		for (int c = minC; c <= maxC; c++) {
			// 위쪽으로 탐색
			for (int r = maxR; r >= minR; r--) {
				if (graphPaper[r][c] == 1) {
					visited[r][c] += 1;
					break;
				}
			}

			// 아래쪽으로 탐색
			for (int r = minR; r <= maxR; r++) {
				if (graphPaper[r][c] == 1) {
					visited[r][c] += 1;
					break;
				}
			}
		}

		// 행 기준으로
		for (int r = minR; r <= maxR; r++) {
			// 오른쪽으로 탐색
			for (int c = minC; c <= maxC; c++) {
				if (graphPaper[r][c] == 1) {
					visited[r][c] += 1;
					break;
				}
			}

			// 왼쪽으로 탐색
			for (int c = maxC; c >= minC; c--) {
				if (graphPaper[r][c] == 1) {
					visited[r][c] += 1;
					break;
				}
			}
		}

		// 방문배열 검사하여 2 이상이면 모눈종이의 치즈를 0으로 만든다
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				if (visited[r][c] > 1)
					graphPaper[r][c] = 0;
			}
		}

		meltingTime++;
	}
}
