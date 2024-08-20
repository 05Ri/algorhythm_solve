package swexpartacademy;

import java.util.Scanner;

public class SWEA_2005_파스칼의_삼각형 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			int n = sc.nextInt(); // 파스칼 삼각형 크기 입력받기
			int[][] pascal = new int[n][]; // 파스칼 삼각형 저장할 곳
			int init[] = new int[] { 0, 1, 0 }; // 초기값
			
			// 삼각형 초기화하기
			pascal[0] = init; // 삼각형에 초기값 저장
			
			for (int i = 1; i < n; i++) {
				int[] temp = new int[i + 3];
				pascal[i] = temp;
			}

			// 파스칼 로직 만들기
			for (int p = 1; p < n; p++) { // 삼각형[0]에 초기값을 넣어줬으므로 1부터 시작 
				int[] temp = new int[pascal[p - 1].length + 1]; // 현재 저장할 줄의 너비 = 전에 저장된 줄의 너비 +1
				for (int i = 1; i < (temp.length - 1); i++) {
					// init 배열에서 두 개씩 차례대로 더하여 로직을 만들어갈 것이다.
					// 처음과 끝은 0 이어야하므로 temp[0]는 건너뛰어서 저장하기
					temp[i] = pascal[p - 1][i - 1] + pascal[p - 1][i];
				}
				pascal[p] = temp;
			}

			System.out.println("#" + t);
			for (int r = 0; r < pascal.length; r++) {
				for (int c = 1; c < pascal[r].length - 1; c++) { // 출력할 숫자 범위 지정
					System.out.print(pascal[r][c] + " ");
				}
				System.out.println();
			}
		}
	}
}
