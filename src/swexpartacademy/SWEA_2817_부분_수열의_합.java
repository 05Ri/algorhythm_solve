package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2817_부분_수열의_합 {
	// 자연수 개수, 자연수의 합, 답
	static int N, K, ans;
	// 자연수들의 배열 A
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			// 자연수 개수
			N = Integer.parseInt(st.nextToken());
			// 자연수의 합
			K = Integer.parseInt(st.nextToken());

			// 자연수 배열 생성
			A = new int[N];

			// 자연수 입력받고 배열에 넣기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			combination(0, 0);

			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	/**
	 * 조합
	 * 
	 * @param idx 기저조건을 맞춰줄 인덱스
	 * @param sum 들어간 곳까지의 합
	 */
	public static void combination(int idx, int sum) {
		if (sum >= K) {
			if (sum == K)
				ans++;
			return;
		}
		
		for (int i = idx; i < N; i++) {
			combination(i + 1, sum + A[i]);
		}
	}
}
