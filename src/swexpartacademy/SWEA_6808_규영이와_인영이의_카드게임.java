package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6808_규영이와_인영이의_카드게임 {
	// 9
	public static final int NINE = 9;
	// 카드 세트, 규영의 카드, 인영의 카드
	public static int[] card = new int[NINE * 2];
	public static int[] cardGY = new int[NINE];
	public static int[] cardIY = new int[NINE];
	// 고정할 인영의 카드
	public static int[] fixedCardIY = new int[NINE];
	// 인영의 카드 방문처리
	public static boolean[] visited = new boolean[NINE];
	// 규영의 점수와 인영의 점수
	public static int scoreGY = 0, scoreIY = 0;
	// 승리 횟수, 패배 횟수
	public static int win, lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 카드 세트 세팅
		for (int i = 0; i < NINE * 2; i++) {
			card[i] = i + 1;
		}

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			win = 0;
			lose = 0;

			// 규영의 카드
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < NINE; i++) {
				cardGY[i] = Integer.parseInt(st.nextToken());
			}

			// 인영의 카드
			int idx = 0;
			out: for (int i = 0; i < NINE * 2; i++) {
				for (int j = 0; j < NINE; j++) {
					// 규영의 카드가 card에 있으면 다음 카드 숫자 카운트
					if (card[i] == cardGY[j])
						continue out;
				}
				// 카드 숫자가 규영의 카드에 없으면 인영의 카드에 추가
				cardIY[idx++] = card[i];
			}

			cardSelect(0);

			System.out.printf("#%d %d %d\n", tc, win, lose);
		}
	}

	/**
	 * 인영의 카드를 정해주는 메소드
	 * 
	 * @param idx 인영의 카드 인덱스
	 */
	public static void cardSelect(int idx) {
		// 카드 인덱스가 9 이상이면 게임을 하고 종료한다.
		if (idx >= NINE) {
			resultCalculate();
			return;
		}

		// 인영의 카드를 뽑는다
		for (int i = 0; i < NINE; i++) {
			// 방문처리 된 곳이면 패스한다
			if (visited[i])
				continue;

			fixedCardIY[idx] = cardIY[i];
			visited[i] = true;
			cardSelect(idx + 1);
			visited[i] = false;
		}
	}

	/**
	 * 정해진 인영의 카드와 게임하여 점수 계산
	 */
	public static void resultCalculate() {

		// 9라운드 동안
		for (int i = 0; i < NINE; i++) {
			// 카드가 더 높은 사람이 두 카드의 합의 점수를 가져가므로 먼저 score를 구해준다.
			int score = cardGY[i] + fixedCardIY[i];
			// 규영이 이겼다면 규영 점수 추가
			if (cardGY[i] > fixedCardIY[i])
				scoreGY += score;
			// 인영이 이겼다면 인영 점수 추가
			else
				scoreIY += score;
		}

		// 총 점수를 비교한다
		// 비기면 함수 탈출
		if (scoreGY == scoreIY) {
			return;
		}

		// 규영이 더 크면 승리, 인영이 더 크면 패배
		if (scoreGY > scoreIY)
			win++;
		else
			lose++;

		// 승패가 갈렸으므로 규영과 인영의 점수 초기화
		scoreGY = 0;
		scoreIY = 0;
	}
}
