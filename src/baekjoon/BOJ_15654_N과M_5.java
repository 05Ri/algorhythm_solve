package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654_N과M_5 {
	static int N, M;
	static int[] arr, ans;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		ans = new int[M];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		NandM(0);
		
		System.out.println(sb);
	}

	public static void NandM(int count) {
		if(count >= M) {
			for (int i : ans) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			
			visited[i] = true;
			ans[count] = arr[i];
			NandM(count + 1);
			visited[i] = false;
		}
	}
}
