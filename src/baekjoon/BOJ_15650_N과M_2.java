package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650_N과M_2 {
	static int N, M;
	static int[] num;
	static int[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫째 줄 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1 ~ N 자연수
		N = Integer.parseInt(st.nextToken());
		// 중복없이 M개 고르기
		M = Integer.parseInt(st.nextToken());

		// 숫자들
		num = new int[N];
		select = new int[M];

		// 배열에 1 ~ N 까지 넣어주기
		for (int i = 0; i < N; i++) {
			num[i] = i + 1;
		}

		combination(0, 0);
	}

	public static void combination(int idx, int count) {
		// 기저 조건
		if (count >= M) {
			for (int i : select)
				System.out.print(i + " ");
			System.out.println();
			return;
		}

		// 재귀 호출
		for (int i = idx; i <= N - (M - count); i++) {
			select[count] = num[i];
			combination(i + 1, count + 1);
		}		
	}
}
