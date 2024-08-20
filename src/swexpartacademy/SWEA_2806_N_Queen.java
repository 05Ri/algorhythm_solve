package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2806_N_Queen {
	// 퀸 개수 & 보드판 크기, 가능한 경우의 수
	public static int N;
	public static int ans;
	// 보드판
	public static int[][] board;
	// 놓인 퀸 파악하여 불가능한 곳을 true로 바꿔준다.(방문 배열)
	public static boolean[][] positionQ;

	// 델타 좌상, 상, 우상
	public static final int dr = -1;
	public static final int[] dc = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 보드판 크기 및 퀸의 개수
			N = Integer.parseInt(br.readLine());

			// 보드판, 방문 배열, 답 초기화
			board = new int[N][N];
			positionQ = new boolean[N][N];
			ans = 0;

			queenSetting(0);

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	/**
	 * 퀸을 보드에 놓는다
	 * 
	 * @param cntQueen 퀸의 개수 및 퀸의 row 값
	 */
	public static void queenSetting(int cntQueen) {
		if (cntQueen >= N) {
			ans++;
			return;
		}
		
		out: for (int c = 0; c < N; c++) {
			// 불가능한 곳이면 col 건너뛰기
			if (positionQ[cntQueen][c])
				continue;

			// 가능한 곳이면 델타 검사해서 가능한 곳인지 확인
			for (int d = 0; d < 3; d++) {
				int nr = cntQueen + dr;
				int nc = c + dc[d];

				// 범위 바깥으로 나갈때까지
				while (nr >= 0 && nc >= 0 && nc < N) {
					// 다른 퀸을 만나면 col 건너뛰기
					if (positionQ[nr][nc])
						continue out;

					// 다른 퀸이 없다면 델타 탐색을 그 방향으로 계속한다
					nr += dr;
					nc += dc[d];
				}
			}

			// 가능한 곳이라면 그 자리에 퀸을 놓고(방문 처리)
			positionQ[cntQueen][c] = true;
			// 다음 퀸을 놓을 곳을 찾는다.
			queenSetting(cntQueen + 1);
			// 경우의 수를 찾았다면 방문처리를 원복한다.
			positionQ[cntQueen][c] = false;
		}
	}
}
