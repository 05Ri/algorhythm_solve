package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651_N과M_3 {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder ans = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫째 줄 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1 ~ N 자연수
		N = Integer.parseInt(st.nextToken());
		// 중복없이 M개 고르기
		M = Integer.parseInt(st.nextToken());
		
		combinationWithRepeatition(0);
		System.out.print(ans);
	}

	public static void combinationWithRepeatition(int count) {
		// 기저 조건
		if (count >= M) {
			ans.append(sb);
			ans.append("\n");
			return;
		}

		// 재귀 호출
		for (int i = 1; i <= N; i++) {
			sb.append(i).append(" ");
			combinationWithRepeatition(count + 1);
			sb.setLength(sb.length() - 2);
		}		
	}
}
