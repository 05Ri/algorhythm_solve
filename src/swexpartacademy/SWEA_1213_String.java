package swexpartacademy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SWEA_1213_String {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int $ = 1; $ <= 10; $++) {
			// 테스트 케이스
			int t = sc.nextInt();

			// 찾을 문자열 및 (길이 - 1) 을 해서 쓸 인덱스
			String search = sc.next();
			int sL = search.length();

			// 스킵 배열
			Map<Character, Integer> skip = new HashMap<>();
			// 문자열의 순서대로 건너뛸 숫자가 할당된다.
			// 이외의 문자에서는 search의 길이만큼 건너뛴다.
			for (int i = 0; i < sL; i++)
				skip.put(search.charAt(i), sL - 1 - i);

			// 검색할 문장 및 (길이 - 1) 을 해서 쓸 인덱스
			String paragraph = sc.next();
			int pL = paragraph.length();
			// 검색이 완료되면 카운트해줄 변수
			int searchComplete = 0;

			// 보이어-무어 알고리즘
			// 전체 문장 돌기
			int d;
			for (int i = sL - 1; i < pL; i = i + d) {
				// 돌면서 건너뛰어줄 숫자
				d = 0;
				// 문자열과 비교하여 맞는 개수를 세어줄 변수
				// 일치하면 나머지를 세주어야하기 때문에 1로 초기화 해준다.
				int cnt = 1;

				// 찾을 문자열과 검색할 문장 비교
				// 찾을 문자열 끝부분과 비교한 검색할 문장의 char를 찾을 문자열을 -- 해가면서 비교
				for (int j = sL - 1; j >= 0; j--) {
					// 문자열 하나를 추출
					char c = search.charAt(j);
//					System.out.printf("paragraph[%d, %c]\n", i, paragraph.charAt(i));
//					System.out.printf("search[%d, %c]\n", j, c);
					// 찾을 문자열 끝부분과 문장이 같은 문자라면
					if (c == paragraph.charAt(i)) {
						// 그 부분부터 차례로 다 일치하는지 검사
						for (int check = 1; check <= j; check++) {
//							System.out.printf("check: [%d, %c] = [%d, %c]\n", j - check, search.charAt(j - check), i - check, paragraph.charAt(i - check));
							// 일치하지 않으면 탈출
							if (search.charAt(j - check) != paragraph.charAt(i - check)) {
								break;
							}
							// 일치하면 cnt++ 하고
							cnt++;
//							System.out.printf("cnt: %d\n", cnt);
						}
						// d에 skip의 value를 넣어준다.
						d = skip.get(c);
					}
				}
				// 다 비교해도 찾을 문자열와 같은 부분이 없거나 일치한다면
				if (d == 0) {
					// d에 sL을 넣어 검색할 문장을 문자열 길이만큼 건너뛰어준다.
					d = sL;
					// 가능했던 곳이 세질 것이므로
					// cnt와 문자열의 길이로 나눈 값이 1이 되어야 일치하는 문자열일 것이다.
					searchComplete += cnt / search.length();
				}

				// i가 문장의 끝이 아니고 d와 i를 더해서 문장의 길이가 넘어간다면
				if (i != pL - 1 && i + d > pL) {
					// 다음 for문을 위해 i를 (끝 - 1)에서 d만큼 빼준다
					i = pL - 1 - d;
				}
//				System.out.printf("d: %d\n", d);
//				System.out.println();
			}
			System.out.printf("#%d %d\n", t, searchComplete);
		}
	}
}
