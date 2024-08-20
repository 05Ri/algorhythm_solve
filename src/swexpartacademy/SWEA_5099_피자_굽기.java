package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5099_피자_굽기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 테스트케이스 개수 입력받기
		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			// 피자가 들어가있는 오븐 만들기
			// 치즈
			Queue<Integer> oven = new LinkedList<>();
			// 피자 번호
			Queue<Integer> ovenNum = new LinkedList<>();

			// 피자의 번호와 치즈 정도를 나타낼 큐 생성
			Queue<Integer> pizza = new LinkedList<>();
			Queue<Integer> cheeze = new LinkedList<>();

			// 테스트케이스 첫번째 줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 오븐의 크기
			int N = Integer.parseInt(st.nextToken());
			// 피자의 개수
			int M = Integer.parseInt(st.nextToken());

			// 테스트케이스 두번째 줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 피자 번호와 피자치즈 입력하기
			for (int i = 1; i <= M; i++) {
				pizza.offer(i);
				cheeze.offer(Integer.parseInt(st.nextToken()));
			}

			// 일단 오븐에 피자 N개를 넣고
			for (int i = 1; i <= N; i++) {
				oven.offer(cheeze.poll());
				ovenNum.offer(pizza.poll());
			}

			// 오븐에 남아있는 피자가 1개가 될 때까지
			while (oven.size() > 1) {
				// 피자 치즈가 1/2이 된걸 체크
				int check = oven.poll() / 2;
				int checkNum = ovenNum.poll();

				// 체크했을 때 0이고
				if (check == 0) {
					// 다음 피자가 있다면
					if (cheeze.peek() != null) {
						// 오븐에 새 피자를 넣는다.
						oven.offer(cheeze.poll());
						// 피자 번호를 빼주고 새로 가져온다.
						ovenNum.offer(pizza.poll());
					}
				} else { // 체크했는데 0이 아니면 체크했던걸 다시 넣는다.
					oven.offer(check);
					ovenNum.offer(checkNum);
				}

//				테스트
//				System.out.print(oven + " ");
//				System.out.println();
//				System.out.print(cheeze + " ");
//				System.out.println();
//				System.out.print(ovenNum + " ");
//				System.out.println();
//				System.out.print(pizza + " ");
//				System.out.println();
//				System.out.println();
			}

			int lastPizza = ovenNum.peek();

			System.out.printf("#%d %d\n", t, lastPizza);
		}
	}
}
