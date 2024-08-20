package swexpartacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225_암호생성기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int $ = 1; $ <= 10; $++) {
			Queue<Integer> password = new LinkedList<>();
			// 테스트 케이스 번호 입력받기
			int tc = Integer.parseInt(br.readLine());
			// 암호 입력받기
			st = new StringTokenizer(br.readLine());

			// 암호 큐에 넣어주기
			while (st.hasMoreTokens())
				password.offer(Integer.parseInt(st.nextToken()));
			int pL = password.size();

			out: while (true) {
				// 싸이클 돌기
				for (int num = 1; num <= 5; num++) {
					// 폴해서 계산한 값
					int temp = password.poll() - num;

					// 0보다 작거나 작으면 0을 넣어주고 종료한다.
					if (temp <= 0) {
						password.offer(0);
						break out;
					} else {
						// 0 위이면 그 값을 다시 넣어준다.
						password.offer(temp);						
					}
				}
			}
			System.out.printf("#%d ", tc);
			for (int i = 0; i < pL; i++)
				System.out.print(password.poll() + " ");
			System.out.println();
		}
	}
}
