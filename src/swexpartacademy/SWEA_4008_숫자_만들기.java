package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4008_숫자_만들기 {
	// 숫자 개수
	static int N;
	// 연산자를 넣어 계산된 최대값과 최소값
	static int calMax, calMin;
	// 연산자 개수 입력받는 배열, 수식에 사용되는 숫자 배열, 순서 저장할 배열
	static int[] operator = new int[4], numbers, select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 최대값과 최소값 초기화
			calMin = 100_000_001;
			calMax = -100_000_001;
			
			// 숫자 개수 입력받기
			N = Integer.parseInt(br.readLine());
			// 숫자 배열 초기화
			numbers = new int[N];
			// 순서 배열 초기화
			select = new int[N - 1];
			
			// 각 연산자 개수 입력받고 배열에 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			
			// 숫자 입력받고 배열에 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			operatorSelect(0);

			int ans = calMax - calMin;
			
			System.out.printf("#%d %d\n", tc, ans);
		}
		
	}

	private static void operatorSelect(int idx) {
		// idx가 (문자개수 - 1) 이상이면 계산식에 넣어서 계산해보고 탈출한다.
		if (idx >= N - 1) {
			calculate();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operator[i] == 0)
				continue;
			
			select[idx] = i;
			operator[i] -= 1;
			operatorSelect(idx + 1);
			operator[i] += 1;
		}
	}

	private static void calculate() {
		int numbersArrIdx = 0;
		
		int result = numbers[numbersArrIdx++];
		
		for (int i = 0; i < N - 1; i++) {
			switch (select[i]) {
			case 0:
				result += numbers[numbersArrIdx++];
				break;
			case 1:
				result -= numbers[numbersArrIdx++];
				break;
			case 2:
				result *= numbers[numbersArrIdx++];
				break;
			case 3:
				result /= numbers[numbersArrIdx++];
				break;
			}
		}
		
		// 결과보다 저장된 최대값이 작다면 갱신
		if (calMax < result)
			calMax = result;
		// 결과보다 저장된 최소값이 크다면 갱신
		if (calMin > result)
			calMin = result;
	}
}
