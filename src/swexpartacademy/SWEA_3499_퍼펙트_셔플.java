package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_3499_퍼펙트_셔플 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 카드를 나눌 큐 생성하기
		Queue<String> card1 = new LinkedList<>(), card2 = new LinkedList<>();

		// 테스트 케이스
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {

			// 첫번째 줄 카드 이름 개수 N 입력받기
			int N = Integer.parseInt(br.readLine());

			// 2번째 줄 입력받기
			st = new StringTokenizer(br.readLine());

			// 카드 나누기
			// 첫번째 반 나눌 때는
			// N이 홀수면 반 나눈거에 +1을 해줘야하지만
			// 짝수이면 반만 나누면 된다.
			// 따라서 N을 2로 나눈 나머지를 더해주면 된다.
			for (int i = 0; i < (N / 2) + (N % 2); i++) {
				card1.add(st.nextToken());
			}

			// 나머지 남은 반 카드 넣기
			while (st.hasMoreTokens()) {
				card2.add(st.nextToken());
			}

			System.out.printf("#%d ", t);
			// 큐가 빌때까지 교차로 빼서 출력해주기
			while (true) {
				// 짝수인 경우 첫번째 카드가 먼저 빈다
				if (card1.isEmpty())
					break;
				System.out.print(card1.poll() + " ");
				
				// 홀수인 경우 두번째 카드가 먼저 빈다
				if (card2.isEmpty())
					break;
				System.out.print(card2.poll() + " ");
			}
			System.out.println();
		}
	}
}
