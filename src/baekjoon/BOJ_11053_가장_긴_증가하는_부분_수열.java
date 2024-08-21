package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053_가장_긴_증가하는_부분_수열 {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 수열의 길이
		int N = Integer.parseInt(br.readLine());
		
		// 배열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numArr = new int[N];
		
		int i = 0;
		while(st.hasMoreTokens()) {
			numArr[i++] = Integer.parseInt(st.nextToken());
		}
		
		// 배열 잘 입력받는지 출력
//		System.out.println(Arrays.toString(numArr));
		
		int[] dp = new int[N];
		
		for (int curr = 0; curr < N; curr++) {
			// 현재 배열의 수열 값은 나 자신이므로 항상 1
			dp[curr] = 1;
			
			for (int prev = curr - 1; prev >= 0; prev--) {
				// 현재 배열 값보다 이전 배열 값이 더 작고, 이번 배열 값의 dp가 현재 저장된 dp값보다 크면
				if (numArr[curr] > numArr[prev] && dp[curr] < dp[prev] + 1) {
					// 현재 dp값에 이전 dp값에 들어있는 수열 값 +1 하여 저장
					dp[curr] = dp[prev] + 1;
				}
			}
		}
		
		// 저장된 DP 중 가장 큰 값 찾기
		int max = 0;
		
		for (i = 0; i < N; i++) {
			if (dp[i] > max) {
				max = dp[i];
			}
		}
		
		System.out.println(max);
	}
}
