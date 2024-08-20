package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1240_단순_2진_암호코드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			// 배열의 세로 N
			int N = Integer.parseInt(st.nextToken());
			// 배열의 가로 M
			int M = Integer.parseInt(st.nextToken());

			// 7자리씩 끊었을 때 찾아낼 자리 저장할 변수
			int start = 0;
			
			// 2진수를 입력받을 String
			String binaryString = "";
			// 2진수 문자열을 저장할 배열 생성
			String[] arr = new String[N];
			
			// 입력값 저장
			for (int n = 0; n < N; n++) {
				// 2진수를 String으로 입력받는다.
				binaryString = br.readLine();
				
				arr[n] = binaryString;
			}
			
			// 세로 찾기
			for (int n = 0; n < N; n++) {
				// 7자리씩 끊어서 확인
				for (int m = 0; m < M; m += 7) {
					System.out.println(m);
					// 자리수 체크할 변수
					int sum = 0;

					// 7자리씩 체크하면서 자리수를 더해본다.
					for (int i = m; i < m + 7; i++) {
						sum += binaryString.charAt(i) - '0';
					}

					// 만약 sum의 값이 0이면 .
					if (sum == 0)
						break;
						
					start = m;
				}
			}
			
			System.out.println(start);
			
			// 암호문 해석 후 저장할 배열이다. 암호문은 8자리라고 했으므로
			int[] password = new int[8];
			
			// 암호문에 들어갈 숫자를 순차적으로 넣어주기 위한 idx
			int idx = 0;
			
			// 암호문 분석 시작
			for (int k = start; k < start + 56; k += 7) {
				// 7자리가 무엇인지 입력받을 변수
				String partOfBinary = "";
				
				for (int i = k; i < k + 7; i++) {
					// i가 가로 길이를 벗어나면 for문 탈출
					if (i >= M)
						break;
					
					// 7자리를 입력받고 그에 맞는 정수를 암호문 배열에 저장한다.
					partOfBinary += binaryString.charAt(i);
					
					switch (partOfBinary) {
					case "0001101":
						password[idx++] = 0;
						break;
					case "0011001":
						password[idx++] = 1;
						break;
					case "0010011":
						password[idx++] = 2;
						break;
					case "0111101":
						password[idx++] = 3;
						break;
					case "0100011":
						password[idx++] = 4;
						break;
					case "0110001":
						password[idx++] = 5;
						break;
					case "0101111":
						password[idx++] = 6;
						break;
					case "0111011":
						password[idx++] = 7;
						break;
					case "0110111":
						password[idx++] = 8;
						break;
					case "0001011":
						password[idx++] = 9;
						break;
					}
				}
			}
			
			int sum = 0;
			
			// 해석한 암호문을 계산해본다
			for (int i = 0; i < 8; i++) {
				if (i % 2 == 0) {
					// 홀수번째 자리는 *3 해주고 더해야하고
					sum += password[i] * 3;
				} else {
					// 짝수번째 자리는 그냥 더해준다.
					sum += password[i];
				}
			}
			
			// 모두 더해준 값이 10의 배수가 아니라면
			if (sum % 10 != 0)
				// 올바르지 않은 암호문이므로 답은 0이다
				sum = 0;

			System.out.printf("#%d %d\n", tc, sum);
		}
	}
}
