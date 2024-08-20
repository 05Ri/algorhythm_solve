package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장_DP {
	// 이용권 요금 배열, 이용 계획 배열, dp
	static int[] costs, usePool, dp = new int[13];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 이용권 요금 줄
			st = new StringTokenizer(br.readLine());
			// 각 변수 초기화
			costs = new int[4];
			usePool = new int[12];

			// 0: 1일 요금 / 1: 1달 요금 / 2: 3달 요금 / 3: 1년 요금
			for (int i = 0; i < 4; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}

			// 12달 이용 계획 줄
			st = new StringTokenizer(br.readLine());
			// 배열에 입력하기
			for (int i = 0; i < 12; i++) {
				usePool[i] = Integer.parseInt(st.nextToken());
			}
			
			// dp 실행
			for (int i = 1; i <= 12; i++) {
				// 전 최소 비용에 1일 이용권과 한 달 이용권 비교
				dp[i] = Math.min(dp[i - 1] + usePool[i - 1] * costs[0], dp[i - 1] + costs[1]);
				
				// 3개월권과 비교
				if (i >= 3) 
					dp[i] = Math.min(dp[i], dp[i - 3] + costs[2]);
				// 1년권과 비교
				if (i >= 12)
					dp[i] = Math.min(dp[i], dp[i - 12] + costs[3]);
			}
			
			System.out.printf("#%d %d\n", tc, dp[12]);
		}
	}
}
