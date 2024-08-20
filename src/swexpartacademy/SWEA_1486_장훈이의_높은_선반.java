package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의_높은_선반 {
	// 점원 명 수, 선반의 높이, 가장 적은 차이 저장
	static int N, B, minGap;
	// 점원 키 저장할 배열
	static int[] height;
	// 점원 키 배열 방문체크
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			
			// 점원들 명 수, 선반의 높이
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// 점원들 키 입력받고 저장
			// 모든 점원의 키를 더해주는 변수
			int tmp = 0;
			st = new StringTokenizer(br.readLine());
			height = new int[N];
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
				tmp += height[i];
			}
			
			visited = new boolean[N];
			
			// 최소 차이 초기화 - 최소 차이는 (모든 점원의 키의 합 - 선반 높이) 이다.
			minGap = tmp - B;
			
			// 만약 최소 차이가 음수이거나 0이면 모든 점원의 키보다 선반 높이가 같거나 높은 것이므로 최소 차이에 음수를 붙여준다.
			if (minGap <= 0)
				minGap = -minGap;
			else
				selectEmployee(0, 0);
			
			System.out.printf("#%d %d\n", tc, minGap);
		}
	}

	/**
	 * 점원들 골라서 쌓기
	 * 
	 * @param cnt 쌓인 점원들의 수
	 * @param sum 쌓인 점원들의 키
	 */
	private static void selectEmployee(int cnt, int sum) {
		// 쌓인 키와 선반 높이 차
		int gap = sum - B;
		
//		System.out.printf("cnt: %d, sum: %d, gap: %d, minGap: %d\n", cnt, sum, gap, minGap);
		
		// 가장 적은 차이보다 갭이 더 크거나 같으면 함수 탈출
		if (minGap <= gap)
			return;
		
		// (쌓인 키가 선반 높이 이상 = gap이 0 이상)이거나 idx가 직원 수를 넘어가면 함수 탈출
		if (gap >= 0 || cnt >= N) {
			// gap이 가장 적은 차이보다 적으면 저장
			if (gap < minGap)
				minGap = gap;
			return;
		}
		
		for (int i = cnt; i < N; i++) {
			if (visited[i])
				continue;
			
			// 가장 적은 차이가 0이면 계산할 필요 없으므로 반복문 탈출
			if (minGap == 0)
				break;
			
			visited[i] = true;
			selectEmployee(i, sum + height[i]);
			visited[i] = false;
		}
	}
}
