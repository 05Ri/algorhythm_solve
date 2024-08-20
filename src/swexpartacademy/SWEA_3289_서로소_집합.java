package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3289_서로소_집합 {
	// 집합 배열
	static int[] p;
	// 초반 집합 개수, 연산의 개수
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			// 집합 개수 초기화 및 초기 설정
			p = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				makeSet(i);
			}
			
			// m개의 연산을 처리한다.
			for (int $ = 0; $ < m; $++) {
				st = new StringTokenizer(br.readLine().trim());
				
				// 연산 번호
				int op = Integer.parseInt(st.nextToken());
				// 첫 번째 값
				int a = Integer.parseInt(st.nextToken());
				// 두 번째 값
				int b = Integer.parseInt(st.nextToken());
				
				switch (op) {
				case 0:	// 합집합(union)
					union(a, b);
					break;
				case 1:	// 같은 집합에 있는가?(findSet)
					if (findSet(a) == findSet(b))
						sb.append("1");
					else
						sb.append("0");
					break;
				}
//				System.out.println(Arrays.toString(p));
			}
			
			System.out.printf("#%d %s\n", tc, sb);
		}
	}
	
	static void makeSet(int x) {
		p[x] = x;
	}
	
	static int findSet(int x) {
		if (x != p[x])
			p[x] = findSet(p[x]);
		
		return p[x];
	}
	
	static void union(int a, int b) {
		p[findSet(b)] = findSet(a);
	}
}
