package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4613_러시아_국기_같은_깃발 {
	// 판의 세로, 가로
	public static int N, M;
	// 각 색으로 칠할 때 바뀌는 칸 개수를 저장할 배열
	public static int[][] cArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// N과 M 입력 받기
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 0: 흰색, 1: 파란색, 2: 빨간색
			cArr = new int[N][3];

			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				// 값을 넣으며 바로 색 카운트
				int WCnt = 0;
				int BCnt = 0;
				int RCnt = 0;

				for (int c = 0; c < M; c++) {
					switch (str.charAt(c)) {
					case 'W':
						WCnt++;
						break;
					case 'B':
						BCnt++;
						break;
					case 'R':
						RCnt++;
						break;
					}
				}
				// 카운트가 끝났으니 줄마다 넣어준다
				cArr[r][0] = M - WCnt;
				cArr[r][1] = M - BCnt;
				cArr[r][2] = M - RCnt;
			}

			int min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N - 2; i++) {
				for (int j = i + 1; j < N - 1; j++) {
					int w, b, r;
					int sum = 0;
					for (w = 0; w <= i; w++) {
						sum += cArr[w][0];
					}
					for (b = w; b <= j; b++) {
						sum += cArr[b][1];
					}
					for (r = b; r < N; r++) {
						sum += cArr[r][2];
					}
					min = Math.min(sum, min);
				}
			}
			
			System.out.printf("#%d %d\n", tc, min);
		}
	}
}
