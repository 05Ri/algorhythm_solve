package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2133_타일_채우기 {
	// 크기
	static int N;
	// 저장해놓을 배열
	static int[] memoi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 크기가 홀수인 경우는 0 출력 후 바로 리턴
		if (N % 2 == 1) {
			System.out.println("0");
			return;
		}
		
		memoi = new int[N + 1];
		memoi[2] = 3;
		
		f(N);
		
//		System.out.println(Arrays.toString(memoi));
		System.out.println(memoi[N]);
	}

	/**
	 * 타일 계산하기
	 * 
	 * @param n	구하고 싶은 바닥의 가로 길이
	 * @return 바닥의 가로 길이에 맞는 경우의 수
	 */
	private static int f(int n) {
		if(n < 1)
			return 0;
		
		if (memoi[n] != 0)
			return memoi[n];
		
		// 계산 부분
		int cal = 3 * f(n - 2);
		
		for (int i = n - 4; i >= 2; i -= 2) {			
			cal += 2 * f(i);
		}
		
		cal += 2;
		
		if (memoi[n] == 0)
			memoi[n] = cal;
		return cal;		
	}
}
