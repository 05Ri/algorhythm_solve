package swexpartacademy;

import java.util.Scanner;

public class SWEA_1954_달팽이_숫자 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt();

		for (int t = 1; t <= testCase; t++) {
			// 크기 입력받기
			int n = sc.nextInt();

			// 배열생성
			int[][] arr = new int[n][n];

			int cnt = 0;
			int k = n - 1;
			int d = 0;

			if (n % 2 == 1) {
				arr[n / 2][n / 2] = n * n;
			}

			while (k >= 0) {
				
				// 오른쪽으로
				for (int c = d; c < k; c++) {
					arr[d][c] = ++cnt;
				}

				// 아래로
				for (int r = d; r < k; r++) {
					arr[r][k] = ++cnt;
				}

				// 왼쪽으로
				for (int c = k; c > d; c--) {
					arr[k][c] = ++cnt;
				}

				// 위쪽으로
				for (int r = k; r > d; r--) {
					arr[r][d] = ++cnt;
				}
				
				d++;
				k--;
			}

			System.out.println("#" + t);
			// 배열 출력
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					System.out.print(arr[r][c] + " ");
				}
				System.out.println();
			}
		}
	}
}
