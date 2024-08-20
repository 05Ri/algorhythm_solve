package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한_배낭_시간초과 {
	// 물품의 수, 버틸 수 있는 무게, 물건의 무게, 물건의 가치
	static int N, K, W, V;
	// 무게의 합, 가치의 합, 최대 가치
	static int weightSum = 0, valueSum = 0, biggestValue = 0;
	// 물건 정보 저장할 곳
	static int[][] info;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 0: 물건의 무게 W, 1: 물건의 가치 V
		info = new int[N][2];
		// 물건들 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		niceTravel(0);
		
		System.out.println(biggestValue);
	}
	
	/**
	 * 최고의 여행을 위해
	 */
	public static void niceTravel(int idx) {
		if (weightSum > K)
			return;
		
		
		for (int i = idx; i < N; i++) {
			weightSum += info[i][0];
			valueSum += info[i][1];
			niceTravel(i);
			weightSum -= info[i][0];
			valueSum -= info[i][1];
		}
		
		biggestValue = Math.max(valueSum, biggestValue);
	}
}
