package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15665_N과M_11 {
	static int N, M;
	static int[] arr, ans, number;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		ans = new int[M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		number = new int[N];
		number[0] = arr[0];
		int idx = 1;
		for (int i = 1; i < N; i++) {
			if(arr[i] == arr[i - 1])
				continue;
			
			number[idx++] = arr[i];
		}
		
		NandM(0);
		
		System.out.println(sb);
	}

	public static void NandM(int count) {
		if (count >= M) {
			for (int i : ans) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(number[i] == 0)
				continue;
			
			ans[count] = number[i];
			NandM(count + 1);
		}
	}
}
