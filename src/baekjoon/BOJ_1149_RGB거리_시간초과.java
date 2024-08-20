package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리_시간초과 {
	// 집의 수, 답(초기값: 최대), 구할 합(0)
	static int N, ans = Integer.MAX_VALUE, sum = 0;
	// 비용 배열
	static int[][] cost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 집의 개수 입력받기
		N = Integer.parseInt(br.readLine());
		
		// 집의 개수만큼 RGB를 입력받는다.
		cost = new int[N][3];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				cost[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음에는 칠한 집의 개수가 0이고
		// 전에 칠한 색깔이 없기 때문에 -1을 넣어주었다.
		coloring(0, -1);
		
		// 출력
		System.out.println(ans);
	}

	/**
	 * 집을 칠하고 비용을 계산하는 메소드
	 * 
	 * @param count		칠한 집의 개수
	 * @param prevColor	전에 색칠한 색깔번호(0: 빨강, 1: 초록, 2: 파랑)
	 */
	public static void coloring(int count, int prevColor) {
		// 현재 합이 최종 답과 같거나 큰지 검사하고
		if(ans <= sum)
			// 그렇다면 재귀를 더 할 필요가 없으므로 탈출
			return;
			
		// 그리고 집의 개수를 초과했다면 위 조건을 통과했단 의미이므로
		if (count >= N) {
			// 답에 현재 합을 넣어주고 탈출
			ans = sum;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			// 전의 색깔을 피해서 집을 칠해준다.
			if (i == prevColor)
				continue;
			
			// 칠하는 비용만큼 합에 더해준다.
			sum += cost[count][i];
			// 다음 재귀를 호출
			coloring(count + 1, i);
			// 재귀에서 나왔다면 다음 합을 위해 다시 원복한다.
			sum -= cost[count][i];
		}
	}
}
