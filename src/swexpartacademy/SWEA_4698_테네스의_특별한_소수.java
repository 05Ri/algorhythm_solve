package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4698_테네스의_특별한_소수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {

			st = new StringTokenizer(br.readLine());

			// 포함하는 숫자(0 ~ 9)
			char D = st.nextToken().charAt(0);
			// A 이상 B 이하
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 소수 판별 배열, 소수이면 false 이다.
			boolean[] check = new boolean[B + 1];
			// 0과 1은 소수가 아니므로 방문처리해준다.
			check[0] = true;
			check[1] = true;

			int ans = 0;

			// B까지의 소수를 판별한다.
			for (int i = 2; i <= B; i++) {
				// 무언가의 배수이면 건너뛴다
				if (check[i])
					continue;

				// false일 경우 B까지의 배수들을 다 체크해준다.
				// 소수 본인은 제외해야하므로 배수는 2부터 시작
				for (int k = 2; i * k <= B; k++) {
					check[i * k] = true;
				}
			}

			// 위의 for문을 통과한 아이가 소수이므로 D를 포함하는지 검사한다.
			for (int i = A; i <= B; i++) {
				if (check[i]) 
					continue;
				
				// i를 str으로 변환
				String kStr = Integer.toString(i);
				// 변환된 str을 char 배열로 변환
				char[] kCh = kStr.toCharArray();
				
				// char 배열에서
				for (int c = 0; c < kCh.length; c++) {
					// D와 같은 부분이 있다면
					if (kCh[c] == D) {
						// 개수를 하나 늘려주고 자리수가 중복되면 또 셀 수 있으니 탈출한다.
						ans++;
						break;
					}
				}
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
