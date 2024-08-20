package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_12712_파리퇴치3 {
	public static int N;
	public static int M;

	public static int[][] fly;

	// 델타 값 생성
	public static int[] dr = { -1, 1, 0, 0, -1, 1, 1, -1 };
	public static int[] dc = { 0, 0, -1, 1, 1, 1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			// 파리 범위
			N = Integer.parseInt(st.nextToken());
			// 분사 범위
			M = Integer.parseInt(st.nextToken());
			
			// 최대값 저장할 변수
			int max = 0;

			// 배열 범위
			fly = new int[N][N];

			// 파리 수 입력받기
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					fly[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			max = maxFlyKill(0, max);
			max = maxFlyKill(4, max);

			System.out.printf("#%d %d\n", t, max);
		}
	}

	/**
	 * 최대 파리 kill 개수를 세주는 메소드
	 * 
	 * @param k   델타의 범위
	 * @param max 최대값
	 * @return 갱신된 델타의 범위에서의 최대값
	 */
	public static int maxFlyKill(int k, int max) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 초기값 설정
				int sum = fly[r][c];

				// 새로운 범위에서의 max값을 저장
				int maxF = 0;

				// 방향에 대한 for문
				for (int d = k; d < k + 4; d++) {
					// i의 세기까지에 대한 for문
					for (int i = 1; i < M; i++) {
						int nr = r + dr[d] * i;
						int nc = c + dc[d] * i;

						// 범위 내에 들어간다면
						if (0 <= nr && nr < N && 0 <= nc && nc < N)
							sum += fly[nr][nc];
					}
					// 계산된 sum값이 저장되어있던 maxF보다 크다면 maxF값 갱신
					if (maxF < sum)
						maxF = sum;
				}
				// 델타를 다 돌때마다 최대값 갱신
				max = Math.max(maxF, max);
			}
		}
		return max;
	}
}
