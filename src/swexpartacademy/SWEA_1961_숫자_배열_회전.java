package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1961_숫자_배열_회전 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] arr90 = new int[N * N];
			int[] arr180 = new int[N * N];
			int[] arr270 = new int[N * N];
			int i;
			
			// 시계 방향으로 90도 회전
			i = 0;
			for (int c = 0; c < N; c++) {
				for (int r = N - 1; r >= 0; r--) {
					arr90[i++] = arr[r][c];
				}
			}
			
			// 시계 방향으로 180도 회전
			i = 0;
			for (int r  = N - 1; r >= 0; r--) {
				for (int c = N - 1; c >= 0; c--) {
					arr180[i++] = arr[r][c];
				}
			}
			
			// 시계 방향으로 270도 회전
			i = 0;
			for (int c = N - 1; c >= 0; c--) {
				for (int r = 0; r < N; r++) {
					arr270[i++] = arr[r][c];
				}
			}
			
			System.out.printf("#%d\n", tc);
			for (int k = 0; k < N; k++) {
				for (int m = k * N; m < (k + 1) * N; m++) {
					System.out.print(arr90[m]);
				}
				System.out.print(" ");
				for (int m = k * N; m < (k + 1) * N; m++) {
					System.out.print(arr180[m]);
				}
				System.out.print(" ");
				for (int m = k * N; m < (k + 1) * N; m++) {
					System.out.print(arr270[m]);
				}
				System.out.println();
			}
		}
	}
}
