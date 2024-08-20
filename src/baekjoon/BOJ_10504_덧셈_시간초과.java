package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_10504_덧셈_시간초과 {
	// 구할 수, 구할 수의 반
	static int N, halfN;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			halfN = N / 2;
			
			boolean flag = false;
			
			// N이 1이 아닌 홀수면
			if (N % 2 == 1 && N != 1) {
				sb.append(String.format("%d = %d + %d", N, halfN, halfN + 1));
				flag = true;
			}
			// N이 짝수면
			else {
				// 최소 길이 3부터 시작
				out: for (int k = 3; k <= halfN; k++) {
					// 테스트할 값 초기화
					int tmp = 0;
					// N을 k로 나눈 몫 저장
					int div = N / k;
					// k의 반 값 저장
					int halfk = k / 2;
					
					// 채워져있던 sb를 초기화
					sb.setLength(0);
					// 정답일 수도 있으니 "N = "을 미리 넣어놓는다.
					sb.append(N).append(" = ");
					
					// k가 짝수일 때
					if (k % 2 == 0) {
						// 나눠떨어지면 건너뛴다.
						if (N % k == 0)
							continue;
						
						// 나머지가 있다면
						// (mod - (k/2-1)) + (mod - (k/2-2)) + ... + (mod) + (mod+1) + ... + (mod + k/2-1) + (mod + k/2)
						for (int i = -(halfk - 1); i <= halfk; i++) {
							int sum = div + i;
							// 만약 div-(k/2-1)이 음수라면 그 후는 불가능한 케이스이다.
							if (sum <= 0)
								break out;
							
							tmp += sum;
							sb.append(sum).append(" + ");
						}
						
						// tmp에 더해진 값이 N이라면
						if (tmp == N) {
							// flag를 바꿔주고
							flag = true;
							// 마지막에 붙어있는 + 를 없애주기 위함
							sb.setLength(sb.length() - 2);
							// 정답을 출력하러 간다.
							break;
						}
					}
					
					// k가 홀수일 때
					else {
						// 나머지가 있다면 건너뛴다.
						if (N % k != 0)
							continue;
						
						// 나머지가 없다면 출력값을 담은 후 바로 출력
						for (int i = -halfk; i <= halfk; i++) {
							sb.append(div + i).append(" + ");
						}
						sb.setLength(sb.length() - 2);
						flag = true;
						break;
					}
				} // k를 증가시키는 for 닫힘
			} // k가 짝수일 때 닫힘
			
			// 답이 되는 곳을 들르지 않았다면
			if (!flag) {
				sb.setLength(0);
				sb.append("IMPOSSIBLE");
			}
			
			bw.write(sb + "\n");
		} // testcase 닫힘
		bw.close();
	}
}
