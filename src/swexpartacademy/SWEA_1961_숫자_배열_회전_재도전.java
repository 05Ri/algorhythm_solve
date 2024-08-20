package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1961_숫자_배열_회전_재도전 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// N * N 행렬
			int N = Integer.parseInt(br.readLine());

			// 배열 생성 및 입력받기
			int[][] arr = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int a = N - 1;

			System.out.printf("#%d\n", tc);
			// 각 회전의 줄
			for (int m = 0; m < N; m++) {
				// 90도 배열
				for (int n = a; n >= 0; n--) {
					System.out.print(arr[n][m]);
				}
				System.out.print(" ");
				// 180도 배열
				for (int n = a; n >= 0; n--) {
					System.out.print(arr[a - m][n]);
				}
				System.out.print(" ");
				// 270도 배열
				for (int n = 0; n < N; n++) {
					System.out.print(arr[n][a - m]);
				}
				System.out.println();
			}
		}
	}
}
