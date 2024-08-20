package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1217_거듭_제곱_upgrade {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int $ = 0; $ < 10; $++) {
			// 테스트 케이스 입력받기
			int t = Integer.parseInt(br.readLine());
			
			// 테스트 케이스의 첫 줄 입력받기
			st = new StringTokenizer(br.readLine());
			
			// N의 M제곱
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int ans = power(N, M);
			
			System.out.printf("#%d %d\n", t, ans);
		}
	}
	
	public static int power(int n, int m) {
		if(m <= 0) return 1;
		return n * power(n, m - 1);
	}
}
