package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2073_수도배관공사_답참고 {
	// 길이, 파이프 개수, 최대 수도관 용량
	static int D, P, maxCapacity;
	// 파이프의 정보
	static int[][] pipeInfo;
	// dp 배열
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		// 0: 길이 / 1: 용량
		pipeInfo = new int[P + 1][2];
		for (int i = 1; i <= P; i++) {
			st = new StringTokenizer(br.readLine());
			pipeInfo[i][0] = Integer.parseInt(st.nextToken());
			pipeInfo[i][1] = Integer.parseInt(st.nextToken());
		}

		dp = new int[D + 1];
		dp[0] = Integer.MAX_VALUE;
		
		for (int i = 1; i <= P; i++) {
			int pipeLength = pipeInfo[i][0];
			int pipeCapacity = pipeInfo[i][1];
			
			for (int d = D; d >= pipeLength; d--) {
				dp[d] = Math.max(dp[d], Math.min(pipeCapacity, dp[d - pipeLength]));
			}
			
//			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[D]);
	}
}
