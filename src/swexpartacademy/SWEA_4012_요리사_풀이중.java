package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4012_요리사_풀이중 {
	// 식재료 개수, 답
	static int N, ans;
	// 시너지 테이블
	static int[][] S;
	// 방문 배열
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 식재료 개수
			N = Integer.parseInt(br.readLine());
			
			// 시너지 테이블 생성 및 값 입력하기
			S = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N];
			
			combination(0);
			
			System.out.printf("#%d %d", tc, ans);
		}
	}
	
	public static void combination(int idx) {
		
	}
}
