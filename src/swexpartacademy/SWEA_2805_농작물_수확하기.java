package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2805_농작물_수확하기 {
	static int[][] farm;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 한 변의 크기 입력받기
			int N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			
			// 농장에 수익 입력하기
			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < N; c++) {
					farm[r][c] = line.charAt(c) - '0';
				}
			}
			
			// 마름모
			int k = 0;
			int mid = N / 2;
			int sum = 0;
			for (int r = 0; r < N; r++) {
				for (int c = mid - k; c <= mid + k; c++) {
					// 해당 좌표의 이득을 더해준다.
					sum += farm[r][c];					
				}
				// k의 가중치
				k += (r >= mid) ? -1 : 1;
			}
			System.out.printf("#%d %d\n", tc, sum);
		}
	}
}
