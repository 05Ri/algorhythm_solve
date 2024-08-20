package swexpartacademy;

import java.util.Scanner;

public class 백만장자_강사님풀이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for (int tc = 1; tc < testCase; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			long answer = 0;
			
			int[] max = new int[N];
			max[N - 1] = arr[N - 1];
			
			for (int i = N - 2; i >= 0; i--) {
				if (arr[i] > max[i + 1]) {
					max[i] = arr[i];
				} else {
					max[i] = max[i + 1];
				}
				
				answer += max[i] - arr[i];
			}
			
			
			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}
}
