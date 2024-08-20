package swexpartacademy;


import java.util.Scanner;
import java.lang.Math;

public class SWEA_1959_두_개의_숫자열 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] Bj = new int[21];
			int[] Ai = new int[21];

			int move_num = Math.abs(N - M);
			int smaller_num = 0;
			int sumOfMulti = 0;
			int biggestSOM = 0;

			if (N > M) {
				for (int k = 0; k < N; k++)
					Bj[k] = sc.nextInt();
				for (int k = 0; k < M; k++)
					Ai[k] = sc.nextInt();
				smaller_num = M;
			} else {
				for (int k = 0; k < N; k++)
					Ai[k] = sc.nextInt();
				for (int k = 0; k < M; k++)
					Bj[k] = sc.nextInt();
				smaller_num = N;
			}
			
//			for (int i = 0; i < N; i++)
//				System.out.print(Ai[i] + " ");
//			System.out.println();
//			for (int j = 0; j < M; j++)
//				System.out.print(Bj[j] + " ");

			for (int j = 0; j <= move_num; j++) {
				for (int i = 0; i < smaller_num; i++) {
					sumOfMulti += Ai[i] * Bj[i + j];
//					System.out.print("[" + i + "] * [" + (i + j) + "] = " + Ai[i] + " * " + Bj[i + j] + " → " + sumOfMulti);
//					System.out.println();
				}
				if(biggestSOM < sumOfMulti)
					biggestSOM = sumOfMulti;

				sumOfMulti = 0;
			}
			
			System.out.println("#" + test_case + " " + biggestSOM);			
		}
	}
}
