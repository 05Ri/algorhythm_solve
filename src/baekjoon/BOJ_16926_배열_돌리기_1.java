package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열_돌리기_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 배열의 크기 입력받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 돌리는 횟수
		int R = Integer.parseInt(st.nextToken());

		// 배열 초기화
		int[][] arr = new int[N][M];

		// 입력받기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 돌려야하는 껍데기 수 구하기
		int skin = Math.min(N, M) / 2;

		// R번 만큼 배열 돌리기
		for (int $ = 0; $ < R; $++) {
			// 껍데기 수 만큼 돌려줘야한다.
			for (int i = 0; i < skin; i++) {
				// 껍데기 시작점 저장해두기
				int tmp = arr[i][i];

				// 1. 윗부분 옮기기
				for (int j = i + 1; j < M - i; j++) {
					arr[i][j - 1] = arr[i][j];
				}

				// 2. 왼쪽부분 옮기기
				for (int j = i + 1; j < N - i; j++) {
					arr[j - 1][M - 1 - i] = arr[j][M - 1 - i];
				}

				// 3. 아랫부분 옮기기
				for (int j = M - 1 - i; j > i; j--) {
					arr[N - 1 - i][j] = arr[N - 1 - i][j - 1];
				}

				// 4. 오른쪽 부분 옮기기
				for (int j = N - 1 - i; j > i; j--) {
					arr[j][i] = arr[j - 1][i];
				}

				// 옮기며 중복으로 덮힌 부분 칠해주기
				arr[i + 1][i] = tmp;
			}
		}
		
		StringBuilder sb = new StringBuilder();

		// 출력할 정답 입력받기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(arr[r][c]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
