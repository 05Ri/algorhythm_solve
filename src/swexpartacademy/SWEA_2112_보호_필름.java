package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 아직 해결 못함

public class SWEA_2112_보호_필름 {
	// 보호 필름 두께, 가로크기, 합격기준, 약품 주입횟수
	static int D, W, K, injectionCnt;
	// 보호 필름 단면 배열
	static int[][] film;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			injectionCnt = 0;

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

			makeFilm(0, 0);

			System.out.printf("#%d %d", tc, injectionCnt);
		}
	}

	/**
	 * 필름 만들기
	 * 
	 * @param row 주입할 행
	 * @param cnt 주입 횟수
	 */
	private static void makeFilm(int row, int cnt) {
		if (checkFine()) {
			injectionCnt = cnt;
			return;
		}
		
		
		
		
		// 원본
		
		
		// A 주입
		
		
		// B 주입
		
	}

	private static boolean checkFine() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
