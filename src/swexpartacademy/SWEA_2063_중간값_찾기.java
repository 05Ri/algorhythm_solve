package swexpartacademy;

import java.util.Scanner;
import java.util.Arrays;

public class SWEA_2063_중간값_찾기 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] score = new int[N];

		for (int i = 0; i < N; i++) {
			score[i] = sc.nextInt();
		}

		Arrays.sort(score);

		System.out.println(score[(int) N / 2]);
	}
}
