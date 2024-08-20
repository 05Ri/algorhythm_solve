package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] date = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			date[i] = Integer.parseInt(st.nextToken());
		}

		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i <= N - K; i++) {
			int sum = 0;
			for (int j = i; j < i + K; j++) {
				sum += date[j];
			}
			maxSum = sum > maxSum ? sum : maxSum;
		}

		System.out.println(maxSum);
	}
}
