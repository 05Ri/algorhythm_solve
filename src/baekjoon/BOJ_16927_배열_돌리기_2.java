package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16927_배열_돌리기_2 {
	// 배열의 세로, 가로 크기, 회전 수
	static int N, M, R;
	// 자주 쓰는 계산식을 간편화
	static int n, m;
	// 저장할 배열
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 배열의 크기 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 돌리는 횟수
		R = Integer.parseInt(st.nextToken());
		// 간편화 변수
		n = N - 1;
		m = M - 1;

		// 배열 초기화
		arr = new int[N][M];

		// 입력받기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 돌려야하는 껍데기 수 구하기
		int skin = Math.min(N, M) / 2;

		/*
		 * (시간초과 났음) 얼마나 돌리면 원상태로 돌아올까? 맨 겉 껍데기는 {(N - 1) + (M - 1)} * 2 번 돌리면 되는데 안쪽도
		 * 고려를 해야한다. 바로 안쪽 껍데기는 {(N - 1 - 2) + (M - 1 - 2)} * 2 번 돌리면 원상태, 그 다음 안쪽 껍데기는
		 * {(N - 1 - 4) + (M - 1 - 4)} * 2 번 돌리면 원상태 ... 모든 껍데기들 돌리는 횟수의 최소공배수를 구해주면 된다.
		 */
		// 최소공배수를 구하기 위해 필요한 각 껍데기의 회전수를 저장할 생성
		int[] num = new int[skin];

		// 돌렸을 때 원래대로 돌아오는 회전수들을 저장
		for (int i = 0; i < skin; i++) {
			num[i] = ((n - i * 2) + (m - i * 2)) * 2;
		}

//		// 최소공배수의 변수값
//		int lcm = 1;
//		// 그 후의 최소공배수 구하기
//		for (int i = 0; i < skin; i++) {
//			lcm = lcm * num[i] / GCD(lcm, num[i]);
//		}
//		
//		// 회전 수를 최소공배수로 나눈 나머지가 돌려야하는 횟수이다.
//		int rot = R % lcm;

		// 껍데기 개수만큼 배열 돌리기 함수 호출해주기
		for (int i = 0; i < skin; i++) {
			rotate(i, R % num[i]);
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

	/**
	 * 배열 돌리기
	 * 
	 * @param skin 무슨 껍데기인지
	 * @param rot 얼마나 돌려야하는지
	 */
	private static void rotate(int skin, int rot) {
		int i = skin;
		for (int $ = 0; $ < rot; $++) {
			// 껍데기 시작점 저장해두기
			int tmp = arr[i][i];

			/*
			 * 껍데기가 원래대로 돌아오는 주기는 {(N - 1 - 2i) + (M - 1 - 2i)} * 2 가 된다.
			 * 따라서 각 껍데기마다 회전수를 나누어준 나머지만큼 돌려준다.
			 */

			// 1. 윗부분 옮기기
			for (int j = i + 1; j < M - i; j++) {
				arr[i][j - 1] = arr[i][j];
			}

			// 2. 왼쪽부분 옮기기
			for (int j = i + 1; j < N - i; j++) {
				arr[j - 1][M - 1 - i] = arr[j][M - 1 - i];
			}

			// 3. 아랫부분 옮기기
			for (int j = m - i; j > i; j--) {
				arr[n - i][j] = arr[n - i][j - 1];
			}

			// 4. 오른쪽 부분 옮기기
			for (int j = n - i; j > i; j--) {
				arr[j][i] = arr[j - 1][i];
			}

			// 옮기며 중복으로 덮힌 부분 칠해주기
			arr[i + 1][i] = tmp;
		}
	}

	/**
	 * 들어온 두 수의 최대 공약수 구하기
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
//	private static int GCD(int i, int j) {
//		int cal = i % j;
//		if (cal == 0)
//			return j;
//
//		return GCD(j, cal);
//	}
}
