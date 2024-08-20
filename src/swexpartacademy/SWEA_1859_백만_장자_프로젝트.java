package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1859_백만_장자_프로젝트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 매매가 날의 개수 N
			int N = Integer.parseInt(br.readLine());
			// 매매가들 입력받기
			st = new StringTokenizer(br.readLine());
			// 매매가(시세) 배열에 넣기
			int[] quote = new int[N];
			for (int i = 0; i < N; i++) {
				quote[i] = Integer.parseInt(st.nextToken());
			}

			long max = 0;
			int bigger = 0;
			long cnt = 0;
			// 뒤에서부터 bigger와 비교해서
			// bigger가 크면 (bigger - 시세)를 해서 cnt에 저장해준다.
			// bigger가 더 작으면 저장해둔 cnt를 max에 합하고 현재 시세를 bigger에 저장해준다. + cnt 초기화
			// bigger와 같으면 패스
			for (int i = N - 1; i >= 0; i--) {
				if (bigger >= quote[i]) {
					cnt += bigger - quote[i];
				} else {
					max += cnt;
					bigger = quote[i];
					cnt = 0;
				}
			}
			// 남아있는 cnt를 합해준다.
			max += cnt;
			
			System.out.printf("#%d %d\n", tc, max);
		}
	}
}
