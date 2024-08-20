package baekjoon;

import java.util.Scanner;

public class BOJ_2578_빙고 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		int bingoMax = 5; // 5개가 되면 빙고!
		int[][] bingo = new int[bingoMax][bingoMax]; // 빙고판 만들기
		
		// 빙고판에 빙고 입력
		for (int r = 0; r < bingoMax; r++) {
			for (int c = 0; c < bingoMax; c++) {
				bingo[r][c] = sc.nextInt();
			}
		}
		
		int cnt = 0; // 사회자가 부르는 번호 카운트
		int[] rCnt = new int[5]; // 세로번호 카운트 할 배열
		int[] cCnt = new int[5]; // 가로번호 카운트 할 배열
		int diagonalLR = 0; // \ 방향 대각선 카운트
		int diagonalRL = 0; // / 방향 대각선 카운트
		int bingoMaxCnt = 0; // 빙고의 개수 세주기
		
		for (int $ = 0; $ < bingoMax * bingoMax; $++) {
			// 사회자가 부르는 번호 입력받기
			int num = sc.nextInt();
			cnt++; // 카운트 증가
			
			// 빙고 숫자 찾기
			out: for (int r = 0; r < bingoMax; r++) {
				for (int c = 0; c < bingoMax; c++) {
					if (bingo[r][c] == num) {
						// 해당하는 위치의 인덱스를 카운트해준다
						rCnt[r]++;
						cCnt[c]++;
						// 대각선도 해당되는지 확인하고 세준다
						if (r == c)
							diagonalLR++;
						if (r + c == 4)
							diagonalRL++;
						
						break out; // 빙고 숫자를 찾았으면 탈출한다.
					}
				}
			}
			
			// 방고를 몇 개 했는지 카운트 해준다
			for (int i = 0; i < bingoMax; i++) {
				// 빙고를 하나 올려주고
				// 해당 카운트를 -1로 하여 중복으로 세는 것을 방지해준다
				if (rCnt[i] == bingoMax) {
					bingoMaxCnt++; 
					rCnt[i] = -1; 
				}
				if (cCnt[i] == bingoMax) {
					bingoMaxCnt++;
					cCnt[i] = -1;
				}
			}
			// 대각선도 빙고 확인
			if (diagonalLR == bingoMax) {
				bingoMaxCnt++; // 빙고를 하나 올려주고
				diagonalLR = -1; // 해당 카운트를 -1로 하여 중복으로 세는 것을 방지해준다
			}
			if (diagonalRL == bingoMax) {
				bingoMaxCnt++; // 빙고를 하나 올려주고
				diagonalRL = -1; // 해당 카운트를 -1로 하여 중복으로 세는 것을 방지해준다
			}
			
			// 빙고 개수 확인해서 3개 이상이면 게임 종료
			if (bingoMaxCnt >= 3)
				break;
		}
		
		System.out.println(cnt);
	}
}
