package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579_계단_오르기_멍같이_실패_그리디 {
	public static int N;
	public static int[] stairs;
	public static int combo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 계단 개수 입력받기
		N = Integer.parseInt(br.readLine());

		// 계단 배열 생성(계단 개수 + 1)
		stairs = new int[N + 1];

		// 계단 역순으로 입력받고
		for (int i = N - 1; i >= 0; i--) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		// 마지막에는 0을 넣어준다.
		stairs[N] = 0;

		// 총합을 구해준다. 계단 끝부분은 무조건 밟아야하므로 해당 값으로 초기화해준다.
		int sum = stairs[0];

		// 계단을 연속해서 밟은 횟수(초기값은 끝의 계단을 밟았으므로 1로 해준다)
		combo = 1;

		// 깡총할 곳
		sum += ggangchong(1);

		System.out.println(sum);
	}

	/**
	 * 깡총 메소드
	 * 
	 * @param curr 현재 위치한 곳에서 비교를 시작할 계단의 인덱스
	 */
	public static int ggangchong(int comp) {
		int sum = 0;
		// 계단 끄트머리는 무조건 밟아야하므로 바로 뒤의 두칸 비교해서 큰 곳으로 가기
		// 한칸 앞으로 갔다면 무조건 한칸 건너고 다시 뒤의 두칸 비교
		// 두칸 갔다면 다시 뒤의 두칸 비교
		for (int i = comp; i < stairs.length - 1; i++) {

			// 2콤보이거나 다다음 계단이 더 크다면
			if (combo == 2 || stairs[i] < stairs[i + 1]) {
				// 다다음 계단의 수를 더해주고
				sum += stairs[i + 1];
//				System.out.printf("case1: %d (%d < %d) or %d combo\n", sum, stairs[i], stairs[i + 1], combo);
				// 해당 계단으로 건너뛰어준다.
				i++;

				// 건너뛰어서 밟았으므로 1콤보이다
				combo = 1;

				// 다음 계단이 더 크다면
			} else if (stairs[i] > stairs[i + 1]) {

				// 다음 계단의 수를 더해준다.
				sum += stairs[i];
//				System.out.printf("case2: %d (%d > %d)\n", sum, stairs[i], stairs[i + 1]);
				// 계단을 밟는다.
				combo++;

				// 다음 계단이랑 다다음 계단의 수가 같다면 분기점이 필요하다.
			} else {
				// 어떻게?? 서 있는 곳을 기준으로 다음칸을 가고 다다음 칸으로 간 문제를 푸는 것과 같다...
				// 그 문제를 풀어서 더 큰 값을 이전에 구해놨던 합이랑 합하면 될 것이다.
				
				// 미리 합에 다음칸을 더해놓는다. 다음칸과 다다음칸의 수가 같고 둘이 비교하여 더 큰 수를 추가로 더해줄 것이기 때문이다.
				sum += stairs[i + 1];
				
//				System.out.printf("깡총 다음 %d칸의 %d 진입\n", i + 1, stairs[i + 1]);
				combo++;
				int sum1 = ggangchong(i + 1);
//				System.out.printf("깡총 다음 %d칸의 %d 탈출\n", i + 1, stairs[i + 1]);
//				System.out.printf("깡총 다다음 %d칸의 %d 진입\n", i + 2, stairs[i + 2]);
				int sum2 = ggangchong(i + 2);
//				System.out.printf("깡총 다다음 %d칸의 %d 탈출\n", i + 2, stairs[i + 2]);
				sum += (sum1 > sum2) ? sum1 : sum2;
//				System.out.printf("case3: %d (sum1: %d, sum2: %d)\n", sum, sum1, sum2);
				break;
			}
		}
		
		return sum;
	}
}
