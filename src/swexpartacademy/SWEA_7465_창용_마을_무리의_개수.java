package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_7465_창용_마을_무리의_개수 {
	// 대표를 저장해줄 배열
	static int[] p;
	// 배열의 크기 및 간선 수
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 초반 부분집합들 세팅해주기
			p = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				makeSet(i);
			}
			
			// 집합들 묶어서 수 파악하기
			for (int $ = 0; $ < M; $++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
//			System.out.println(Arrays.toString(p));
			
			// 무리 수 카운트
			int cnt = 0;
			// 합쳐진 집합들 카운트하기
			for (int i = 1; i <= N; i++) {
				if (p[i] == i) {
					cnt++;
				}
			}
			
			System.out.printf("#%d %d\n", tc, cnt);
		}
	}

	static void makeSet(int x) {
		p[x] = x;
	}
	
	static int findSet(int x) {
		if(x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}
	
	static void union(int a, int b) {
		p[findSet(b)] = findSet(a);
	}
}
