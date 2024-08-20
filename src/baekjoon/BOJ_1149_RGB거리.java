package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {
	// 집의 수, 답(초기값: 최대), 구할 합(0)
	static int N, ans = Integer.MAX_VALUE, sum = 0;
	// 비용 배열
	static int[][] cost;
	// 저장 배열
	static int[][] save;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 집의 개수 입력받기
		N = Integer.parseInt(br.readLine());

		// 집의 개수만큼 RGB를 입력받는다.
		cost = new int[N][3];

		save = new int[N][3];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				cost[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 집 칠하기
		// 처음에는 칠한 집의 개수가 0이다.
		coloring(0);

		// 저장된 값 중 가장 작은 값을 저장
		ans = Math.min(save[N - 1][0], save[N - 1][1]);
		ans = Math.min(ans, save[N - 1][2]);

		// 출력
		System.out.println(ans);
	}

	/**
	 * 집을 칠하고 비용을 계산하는 메소드
	 * 
	 * @param count 칠한 집의 개수
	 */
	public static void coloring(int count) {
		// 칠할 집의 수를 초과하면 탈출
		if (count >= N)
			return;

		if (count == 0) {
			// 전에 칠한 집이 없다면 저장된 비용은 전부 초기값이다.
			// 빨강
			save[0][0] = cost[0][0];
			// 초록
			save[0][1] = cost[0][1];
			// 파랑
			save[0][2] = cost[0][2];
		} else {
			// 전에 칠했던 집의 인덱스를 저장한다.
			int prev = count - 1;

			for (int c = 0; c < 3; c++) {
				// 빨강을 칠하게 되면
				if (c == 0)
					// [빨강을 칠하는데 드는 비용] + [초록과 파랑 중 적었던 저장된 비용] 을 저장해준다.
					save[count][0] = cost[count][0] + Math.min(save[prev][1], save[prev][2]);

				// 초록을 칠하게 되면
				if (c == 1)
					// [초록을 칠하는데 드는 비용] + [빨강과 파랑 중 적었던 저장된 비용] 을 저장해준다.
					save[count][1] = cost[count][1] + Math.min(save[prev][0], save[prev][2]);

				// 파랑을 칠하게 되면
				if (c == 2)
					// [파랑을 칠하는데 드는 비용] + [빨강과 초록 중 적었던 저장된 비용] 을 저장해준다.
					save[count][2] = cost[count][2] + Math.min(save[prev][0], save[prev][1]);
			}
		}
		// 다음 재귀를 해준다.
		coloring(count + 1);
	}
}
