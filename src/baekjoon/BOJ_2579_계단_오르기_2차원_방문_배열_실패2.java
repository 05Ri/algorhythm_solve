package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579_계단_오르기_2차원_방문_배열_실패2 {
	public static int[] stairs;
	public static int N;
	public static int[][] sumMax;
	
	public static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 계단 개수 입력받기
		N = Integer.parseInt(br.readLine());

		// 계단 배열 생성
		stairs = new int[N];

		// 계단 역으로 입력 받기
		for (int i = N - 1; i >= 0; i--) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		// 누적합 배열 생성 - 0: 연속이 아닐때 / 1: 연속일 때
		sumMax = new int[N][2];

		idx = 0;
		
		// 모든 경우의 수를 따져본다.
		ggangchong(0, 0, 0);

		int max = Math.max(sumMax[stairs.length - 1][0], sumMax[stairs.length - 1][1]);

		System.out.println(max);
	}

	/**
	 * 깡총
	 * 
	 * @param idx      계단배열의 인덱스
	 * @param isDouble 연속 두번 밟았는지
	 * @return 가장 큰 합
	 */
	public static void ggangchong(int idxPrev, int isDouble, int isDoublePrev) {

		System.out.println(Arrays.deepToString(sumMax));

		// idx가 계단 개수보다 같거나 크면 dfs를 끝낸다.
		if (idx >= stairs.length)
			return;
		
		System.out.println(idx +  " " + idxPrev + " " + isDouble + " " + isDoublePrev + " " + stairs[idx]);

		// idx가 2보다 크다면
		if (idx >= 2) {
			// 이곳에 오기 전에 저장해놨던 데이터 가져오기
			int prevSum = sumMax[idxPrev][isDoublePrev];
			// 이전 합과 현 계단의 합이 현 누적합보다 같거나 작다면 dfs 종료
			if (prevSum + stairs[idx] <= sumMax[idx][isDouble])
				return;
			// 크다면 저장
			sumMax[idx][isDouble] = prevSum + stairs[idx];
		} else {
			// idx가 2 이하라면 현 계단 수와 이전 데이터의 합을 저장
			if (idx == 0) {
				// 0이면 현 계단 수 저장
				sumMax[idx][isDouble] = stairs[idx];
			} else {
				// 1이면 이전 누적합과 현 계단수 합해서 저장
				sumMax[idx][isDouble] = sumMax[idx - 1][isDoublePrev] + stairs[idx];
			}
		}

		// 가능한 수를 탐색
		// 한 칸 앞으로 가려고 할 때
		// 두 번 연속 밟은 상태가 아니라면 한 칸 앞으로 가서 dfs 탐색한다.
		if (isDouble != 1) {
			idxPrev = idx;
			idx += 1;
			ggangchong(idxPrev, 1, isDouble);
			idx -= 1;
			System.out.println(idx +  " " + idxPrev + " " + isDouble + " " + isDoublePrev + " " + stairs[idx]);
		}
		
		// 두 칸 앞으로 가서 dfs 탐색한다.
		idxPrev = idx;
		idx += 2;
		ggangchong(idxPrev, 0, isDouble);
		idx -= 2;
		System.out.println(idx +  " " + idxPrev + " " + isDouble + " " + isDoublePrev + " " + stairs[idx]);
	}
}
