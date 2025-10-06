package baekjoon.BOJ_15652_N과M_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		MandN(0, 0);
	}

	public static void MandN(int cnt, int prevNum) {
		if (cnt >= M) {
			System.out.println(sb);
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (i < prevNum)
				continue;
			
			sb.append(i).append(" ");
			MandN(cnt + 1, i);
			sb.setLength(sb.length() - 2);
		}
	}
}
