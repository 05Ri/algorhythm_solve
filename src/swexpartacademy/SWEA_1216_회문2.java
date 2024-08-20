package swexpartacademy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1216_회문2 {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/swea_1216_회문2/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);

		int N = 100;

		for (int $ = 1; $ <= 10; $++) {
			int tc = sc.nextInt();

			// 문자평면 charPlate > cp
			char[][] cp = new char[N][N];

			// 100행이므로 N번 반복
			for (int r = 0; r < N; r++) {

				// 입력받은 줄을 저장할 String 생성
				String st = sc.next();

				// 열로 쪼개서 char로 저장할 배열 생성
				char[] ch = new char[N];

				// char배열에 쪼갠 st 넣어주기
				for (int i = 0; i < N; i++) {
					ch[i] = st.charAt(i);
				}
				// cp r행에 char배열 넣어주기
				cp[r] = ch;
			}

			// 회문의 최고 길이
			int cnt = 1;
			// 처음에는 문자 하나가 최고 길이여서 비교하는 의미가 없다.
			// 따라서 cnt = 2부터 시작하도록 세팅한다.

			// 가로의 경우
			// cnt길이가 짝수일 경우
			cnt = Math.max(cnt, Palinedrome(N, cp, cnt, 0));
			
			// cnt길이가 홀수일 경우
			cnt = Math.max(cnt, Palinedrome(N, cp, cnt, 0));
			
			// 세로의 경우
			// 짝수일 경우
			cnt = Math.max(cnt, Palinedrome(N, cp, cnt, 1));
			
			// 홀수일 경우
			cnt = Math.max(cnt, Palinedrome(N, cp, cnt, 1));
			
			// 출력
			System.out.printf("#%d %d\n", tc, cnt);
		}

	}

	// 가로 문자열 팰린드롬의 최대 길이를 알려줄 메소드
	public static int Palinedrome(int N, char[][] cp, int cnt, int isVertical) {
		cnt += 1;
		// cnt(최대길이)가 홀수인지 짝수인지에 따라 결과가 달라질 수 있기 때문에 위해 i에 2씩 더해줬다.
		out: for (int i = cnt; i <= N; i += 2) {
			// i만큼 추출해서 담아줄 배열
			char[] checker = new char[i];

			for (int r = 0; r < N; r++) {
				// 가중치 d를 설정해준다.
				for (int d = 0; d + i < N; d++) {
					// checker의 인덱스
					int idx = 0;
					// i 길이만큼 추출해준다.
					for (int c = d; c < d + i; c++) {
						// 가로 세로인지 검사하여 세로면은 c와 r값을 바꿔주었다.
						if (isVertical == 1) {
							checker[idx++] = cp[c][r];
						} else {
							checker[idx++] = cp[r][c];
						}
					}
					// i 길이만큼 추출하여 회문인지 검사한다.
					cnt = checkPalindrome(checker);
					if (cnt == i) {
						// i 길이의 회문이 존재하면 다음 길이를 검사한다.
						continue out;
					}
				}
			}
			// 다 돌았는데도 i길이의 회문이 존재하지 않으면
			// 최대 길이에 i - 2를 저장하고 회문 검사를 종료한다.
			// 회문을 검사하기 위해 i에 +2를 해줬기 때문이다.
			cnt = i - 2;
			break;
		}
		return cnt;
	}

	// 회문 체크해주는 함수
	public static int checkPalindrome(char[] ch) {
		int L = ch.length;
		for (int i = 0; i < L / 2; i++) {
			// 같지 않으면 -1을 리턴한다.
			if (ch[i] != ch[L - 1 - i]) {
				return -1;
			}
		}
		// 전부 일치하면 배열의 길이를 반환
		return L;
	}
}
