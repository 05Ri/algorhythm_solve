package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1860_진기의_최고급_붕어빵 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// test case 입력받기
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			// 첫번째 줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 사람 수 n
			int n = Integer.parseInt(st.nextToken());
			// 붕어빵 초 m
			int m = Integer.parseInt(st.nextToken());
			// 붕어빵 개수 k
			int k = Integer.parseInt(st.nextToken());
			// 사람들을 전부 만족시키기 위해 필요한 시간
			int needTime = (n / k + 1) * m;

			// 두번째 줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 도착하는 시간 카운팅 해줄 배열 만들기(arriving time count)
			int[] arrTCnt = new int[needTime + 1];

			// 사람들이 도착하는 시간 카운팅하기
			for (int i = 0; i < n; i++) {
				int time = Integer.parseInt(st.nextToken());
				// 만약 도착하는 시간이 필요한 시간보다 크다면
				// 즉, 카운팅 배열보다 크다면 카운팅 배열 최고점에 맞춰준다.
				if (time > needTime)
					time = needTime;
				// 시간 카운트
				arrTCnt[time]++;
			}
			// 테스트
//			System.out.println(Arrays.toString(arrTCnt));

			// 붕어빵 굽기
			// fish bread
			int[] fb = new int[needTime + 1];
			int d = 0;
			int fish = 0;
			// 기본은 가능한걸로 해놓는다.
			String isPossible = "Possible";
			
			for (int i = 0; i < needTime; i++) {
				// 시간이 되었을 때 붕어빵 개수를 늘려준다
				if (d % m == 0 && d != 0) {
					fish += k;
				}
				d++;
				// 손님이 도착하면 붕어빵이 팔린다!
				fish -= arrTCnt[i];
				// 만들어진 붕어빵과 손님이 도착한 시간의 차가 0 미만이면 불가능
				if (fish < 0) {
					isPossible = "Impossible";
					break;
				}
				
				fb[i] = fish;
			}

			System.out.printf("#%d %s\n", t, isPossible);
		}
	}
}
