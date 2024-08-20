package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1209_Sum {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 100; // 배열의 크기

		for (int $ = 1; $ <= 10; $++) {
			// 테스트케이스 입력받기
			int t = Integer.parseInt(br.readLine());

			int max = 0; // 최대값 저장할 변수
			int[][] arr = new int[N][N]; // 배열생성

			// 배열값 입력받기(행 개수가 100개 이므로)
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 한 열의 합 중 가장 큰 값
			int sum = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					sum += arr[r][c];
				}
				if (sum > max) {
					max = sum;
				}
				sum = 0;
			}

			// 한 행의 합 중 가장 큰 값
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					sum += arr[r][c];
				}
				if (sum > max) {
					max = sum;
				}
				sum = 0;
			}

			// 대각선 /
			for (int k = 0; k < N; k++) {
				sum += arr[k][k];
			}
			if (sum > max) {
				max = sum;
			}
			sum = 0;

			// 대각선 \
			for (int k = 0; k < N; k++) {
				sum += arr[k][N - 1 - k];
			}
			if (sum > max) {
				max = sum;
			}

			System.out.printf("#%d %d\n", t, max);
		}
	}
}
