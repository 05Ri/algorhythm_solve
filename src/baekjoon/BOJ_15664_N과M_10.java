package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15664_N과M_10 {
	static int N, M;
	static int[] arr, ans;
	static int[][] number;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		ans = new int[M];
		number = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		number[0][0] = arr[0];
		number[0][1]++;
		int idx = 0;
		for (int i = 1; i < N; i++) {
			if (arr[i] == arr[i - 1]) {
				if (number[idx][0] == 0)
					number[idx][0] = arr[i];
				
				number[idx][1]++;
			} else {
				idx++;
				number[idx][0] = arr[i];
				number[idx][1]++;
			}
		}
		
		NandM(0, 0);
		
		System.out.println(sb);
	}

	public static void NandM(int count, int prev) {
		if (count >= M) {
			for (int i : ans) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (number[i][1] == 0 || number[i][0] < prev)
				continue;
			
			number[i][1]--;
			ans[count] = number[i][0];
			NandM(count + 1, number[i][0]);
			number[i][1]++;
		}
	}
}
