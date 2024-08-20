package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1974_스도쿠_검증_upgrade {
	public static int[][] sdoku;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		out: for (int tc = 1; tc <= testCase; tc++) {
			sdoku = new int[9][9];

			// 입력받은 값을 판으로 옮기기
			for (int r = 0; r < 9; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 9; c++) {
					sdoku[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 가로와 세로 라인에 다 들어있는지 체크
			if (chkLine(0) == 0 || chkLine(1) == 0) {
				System.out.printf("#%d 0\n", tc);
				continue out;
			}

			// 3X3에 다 들어있는지 체크
			for (int y = 0; y < 3; y++) {
				for (int x = 0; x < 3; x++) {
					
					int check[] = new int[10];
					for (int r = y * 3; r < (y + 1) * 3; r++) {
						for (int c = x * 3; c < (x + 1) * 3; c++) {
							check[sdoku[r][c]]++;
						}
					}
					if (chkVaild(check) == 0) {
						System.out.printf("#%d 0\n", tc);
						continue out;
					}
				}
			}

			System.out.printf("#%d 1\n", tc);
		} // test case 닫힘
	} // main 닫힘

	public static int chkVaild(int[] check) {
		for (int i = 1; i <= 9; i++) {
			if (check[i] == 0)
				return 0;
		}
		return 1;
	}

	public static int chkLine(int line) {
		int[] check;

		for (int r = 0; r < 9; r++) {
			check = new int[10];
			for (int c = 0; c < 9; c++) {
				if (line == 0)
					check[sdoku[r][c]]++;
				else
					check[sdoku[c][r]]++;
			}
			if (chkVaild(check) == 0)
				return 0;
		}
		return 1;
	}
}