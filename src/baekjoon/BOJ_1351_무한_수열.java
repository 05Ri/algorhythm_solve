package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1351_무한_수열 {
	// 입력받을 수들
	public static long N;
	public static int P, Q;
	static Map<Long, Long> memoi = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		// 재귀
		long ans = A(N);
		
		System.out.println(ans);
	}
	
	/**
	 * 재귀함수
	 * 
	 * @param n	An번째 계산
	 * @return	An의 값
	 */
	public static long A(long n) {
		long cal = 0;
		
		// n이 0이 된다면 1로 리턴해준다.
		if (n == 0)
			return 1;
		
		// 만약 n번째가 저장되었다면 memoi에 키:n이 저장된 밸류 값을 리턴한다.
		if (memoi.get(n) != null)
			return memoi.get(n);
		
		// 무한수열
		cal = A(n / P) + A(n / Q);
		
		// 맵에 저장 > 키: n, 밸류: 계산된 A(n)
		memoi.put(n, cal);
		
		return cal;
	}
}
