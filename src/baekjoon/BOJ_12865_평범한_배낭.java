package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한_배낭 {
	// 물품의 수, 버틸 수 있는 무게
	static int N, K;
	// 물건 정보, dp
	static int[][] info, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][K + 1];
		
		// 0: 물건의 무게 W, 1: 물건의 가치 V
		info = new int[N + 1][2];
		// 물건들 입력받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 물건의 가지 수를 하나씩 늘려간다.
		for (int i = 1; i <= N; i++) {
			// 버틸 수 있는 무게를 하나씩 늘려간다.
			for (int w = 1; w <= K; w++) {
				// 버틸 수 있는 무게가 i번째 물건의 무게 이상이면
				if (w >= info[i][0])
					// 전 물건까지의 최적해와 전 물건까지의 가치에서 현재 넣을 물건의 가치를 더했을 때의 가치 중 무엇이 더 최적인지 비교한다.
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - info[i][0]] + info[i][1]);
				// i번째 물건의 무게가 더 무거운 상태라면
				else
					// 전 물건까지의 최적해를 가져온다.
					dp[i][w] = dp[i - 1][w];
			}
		}

		System.out.println(dp[N][K]);
	}
}
