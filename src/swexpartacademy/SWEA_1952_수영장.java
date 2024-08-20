package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장 {
	// 이용권 요금 배열, 이용 계획 배열
	static int[] costs, usePool;
	// 계획 확인용 방문배열
	static boolean[] visited;
	// 가장 적은 비용
	static int minCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 이용권 요금 줄
			st = new StringTokenizer(br.readLine());
			// 각 변수 초기화
			costs = new int[4];
			usePool = new int[12];
			visited = new boolean[12];
			minCost = Integer.MAX_VALUE;

			// 0: 1일 요금 / 1: 1달 요금 / 2: 3달 요금 / 3: 1년 요금
			for (int i = 0; i < 4; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}

			// 12달 이용 계획 줄
			st = new StringTokenizer(br.readLine());
			// 배열에 입력하기
			for (int i = 0; i < 12; i++) {
				usePool[i] = Integer.parseInt(st.nextToken());
			}

			findMinCost(0, 0);

			System.out.printf("#%d %d\n", tc, minCost);
		}
	}

	/**
	 * 가장 적은 비용을 찾는 함수
	 * 
	 * @param idx  이용계획과 방문 처리하는 배열
	 * @param cost 이용계획과 이용권에 따른 저장되는 비용
	 */
	private static void findMinCost(int idx, int cost) {
		// idx가 12달을 벗어나면
		// minCost와 비교 후 리턴해준다.
		if (idx >= 12) {
//			System.out.printf("cost: %d / minCost: %d\n", cost, minCost);
			minCost = cost < minCost ? cost : minCost;
			return;
		}

		// 1. 1일 이용권을 이용
		cost += costs[0] * usePool[idx];
		visited[idx] = true;
		findMinCost(idx + 1, cost);
		visited[idx] = false;
		cost -= costs[0] * usePool[idx];

		// 2. 1달 이용권을 이용
		cost += costs[1];
		visited[idx] = true;
		findMinCost(idx + 1, cost);
		visited[idx] = false;
		cost -= costs[1];

		// 3. 3달 이용권을 이용
		for (int i = idx; i < idx + 3; i++) {
			/*
			 * 3달 연속으로 보았을 때
			 * 방문처리가 되어있거나
			 * i가 12월을 넘어간다면 불가능하므로
			 * 함수를 종료한다.
			 */
			if (i >= 12 || visited[i])
				return;
		}
		
		// 함수가 종료되지 않았다면 실행
		for (int i = idx; i < idx + 3; i++) {
			visited[i] = true;
		}
		cost += costs[2];
		findMinCost(idx + 3, cost);
		cost -= costs[2];
		for (int i = idx; i < idx + 3; i++) {
			visited[i] = false;
		}

		// 4. 1년 이용권을 이용
		for (int i = idx; i < idx + 12; i++) {
			/*
			 * 12달 연속으로 보았을 때
			 * 방문처리가 되어있거나
			 * i가 12월을 넘어간다면 불가능하므로
			 * 함수를 종료한다.
			 */
			if (i >= 12 || visited[i])
				return;
			
			// 0번째 인덱스에서만 가능
			cost += costs[3];
			findMinCost(idx + 12, cost);
			// 마지막이므로 원복 X
		}
	}
}
