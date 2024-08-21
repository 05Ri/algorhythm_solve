package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629_곱셈 {
	public static long C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		System.out.println(power(A, B));
	}
	
	public static long power(long A, long B) {
		// 1번 곱해지는 경우 A % C로 리턴
		if (B == 1) {
			return A % C;
		}
		
		// 지수를 반으로 한 값 구하기
		long value = power(A, B / 2);
		
		if (B % 2 == 0) {
			// 지수가 짝수일 때
			return value * value % C;
		} else {
			// 지수가 홀수일 때
			return ((value * value % C) * (A % C)) % C;
		}
	}
}
