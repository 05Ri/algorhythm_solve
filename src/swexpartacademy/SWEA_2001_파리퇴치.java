package swexpartacademy;

import java.util.Scanner;

public class SWEA_2001_파리퇴치 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt(); // 테스트 케이스 입력받기

		for (int t = 1; t <= testCase; t++) {
			int n = sc.nextInt(); // 파리가 있을 배열 크기
			int m = sc.nextInt(); // 파리채의 크기

			// 파리의 개수 입력받기
			int[][] fly = new int[n][n];
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					fly[r][c] = sc.nextInt();
				}
			}

			int killMax = 0; // 잡힌 파리의 최대 수
			int k = n - m; // (파리 범위 - 파리채 범위)를 정해주기 위한 변수

			// 파리채가 잡을 범위들의 합을 구하기
			for (int i = 0; i <= k; i++) { // r값을 가중하기 위한 i
				for (int j = 0; j <= k; j++) { // c값을 가중하기 위한 j
					int kill = 0; // 잡은 파리의 개수

					// 파리채 범위 안에서 총합 구하기
					for (int r = i; r < m + i; r++) {
						for (int c = j; c < m + j; c++) {
//							System.out.printf("(%d, %d), %d\n", r, c, fly[r][c]);
							kill += fly[r][c];
//							System.out.println(kill);
						}
//						System.out.println();
					}
//					System.out.println();

					// 범위내 계산된 값이 최대 수보다 크다면 잡힌 파리 최대수를 갱신해준다.
					if (kill > killMax) {
						killMax = kill;
					}
				}
			}

			System.out.printf("#%d %d\n", t, killMax);
		}
	}
}
