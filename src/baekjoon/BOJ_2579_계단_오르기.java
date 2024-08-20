package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579_계단_오르기 {
	public static int N;
	public static int[] stairs;
	public static int [][] maxSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		stairs = new int[N + 1];
		maxSum = new int[N + 1][2];
		
		for (int i = 1; i < N + 1; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		ggangchong(N);
		
//		System.out.println(Arrays.deepToString(maxSum));
		int max1 = Math.max(maxSum[0][0], maxSum[0][1]);
		int max2 = Math.max(maxSum[1][0], maxSum[1][1]);
		
		System.out.println(Math.max(max1, max2));
	}
	
	public static void ggangchong(int idx) {
		if (idx < 0)
			return;
		
		if (idx == N) 
			maxSum[idx][0] = stairs[idx];
		else if (idx == N - 1)
			maxSum[idx][0] = stairs[idx] + maxSum[idx + 1][0];
		else if (idx == N - 2)
			maxSum[idx][1] = stairs[idx] + maxSum[idx + 2][0];
		else {
			maxSum[idx][0] = stairs[idx] + maxSum[idx + 1][1];
			maxSum[idx][1] = stairs[idx] + Math.max(maxSum[idx + 2][0], maxSum[idx + 2][1]);
		}

		ggangchong(idx - 1);
	}
}
