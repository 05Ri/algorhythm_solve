package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 아직 해결 못함

public class SWEA_2112_보호_필름_강사님풀이2 {
	// 보호 필름 두께, 가로크기, 합격기준, 약품 주입횟수
	static int D, W, K, injectionCnt;
	// 보호 필름 단면 배열
	static int[][] film;
	
	static int[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {

			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 필름 단면 입력받기
			film = new int[D][W];
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					film[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			A = new int[W];
			B = new int[W];
			Arrays.fill(B, 1);

			injectionCnt = K;

			makeFilm(0, 0);

			System.out.printf("#%d %d", tc, injectionCnt);
		}
	}

	/**
	 * 보호 필름 만들기
	 * 
	 * @param row 현재 약품을 주입할 행
	 * @param cnt 약품을 주입한 횟수
	 */
	public static void makeFilm(int row, int cnt) {
		if (isOK()) {
			injectionCnt = Math.min(injectionCnt, cnt);
			return;
		}

		if (injectionCnt < cnt)
			return;

		if (row == D)
			return;

		// 주입 X
		makeFilm(row + 1, cnt);

		// 약품 주입 전에 원상복구를 해야하니 메모리를 할당해서 저장을 해두자.
		int[] tmp = film[row];

		// 주입 A
		film[row] = A;
		makeFilm(row + 1, cnt + 1);

		// 주입 B
		film[row] = B;
		makeFilm(row + 1, cnt + 1);

		film[row] = tmp;
	}

	private static boolean isOK() {
		for (int c = 0; c < W; c++) {
			boolean flag = false;
			int cnt = 1;
			for (int r = 1; r < D; r++) {
				if (film[r][c] == film[r - 1][c])
					cnt++;
				else
					cnt = 1;
				if (cnt >= K) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return false;
		}
		return true;
	}
}
