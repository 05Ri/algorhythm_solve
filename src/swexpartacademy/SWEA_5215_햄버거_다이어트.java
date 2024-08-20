package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거_다이어트 {
	// 재료 수, 제한 칼로리
	static int N, L, ans;
	// 맛 점수, 칼로리, 방문 배열
	static int[] score, cal, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			// 재료의 수
			N = Integer.parseInt(st.nextToken());
			// 제한 칼로리
			L = Integer.parseInt(st.nextToken());

			score = new int[N];
			cal = new int[N];

			// 재료에 대한 맛 점수와 칼로리 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			combinations(0, 0, 0);

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	public static void combinations(int idx, int tasty, int calorie) {
		if (calorie > L) {
			return;
		}
		
		ans = Math.max(ans, tasty);
		
		for (int i = idx; i < N; i++) {
			combinations(i + 1, tasty + score[i], calorie + cal[i]);
		}
	}
}
