package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1251_하나로_프림 {
	// 무한 상수 선언
	static final Double INF = Double.MAX_VALUE;
	// 섬의 개수
	static int N;
	// 최소비용, 환경부담세율
	static double lowCost, E;
	// 섬을 들렀는지 확인할 방문배열
	static boolean[] visited;
	// 섬
	// 섬의 좌표를 저장할 배열
	static int[][] state;
	// 각 섬의 거리에 따른 비용을 저장할 인접행렬, 섬의 상태를 저장할 배열
	static double[][] adjMatrix, land;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 최소 비용 초기화
			lowCost = 0;

			// 섬의 개수
			N = Integer.parseInt(br.readLine());

			// 인접행렬 초기화
			adjMatrix = new double[N][N];

			// x좌표, y좌표 입력받기
			StringTokenizer stX = new StringTokenizer(br.readLine());
			StringTokenizer stY = new StringTokenizer(br.readLine());

			// 섬의 그래프 저장 / 0: 대표 저장 / 1: 최소거리 저장
			land = new double[N][2];
			// 섬의 좌표 저장 / 0: x좌표 / 1: y좌표
			state = new int[N][2];
			for (int i = 0; i < N; i++) {
				state[i][0] = Integer.parseInt(stX.nextToken());
				state[i][1] = Integer.parseInt(stY.nextToken());
				land[i][0] = -1;
				land[i][1] = INF;
			}

			// 환경 부담 세율
			E = Double.parseDouble(br.readLine());

			// 각 섬과 섬 사이의 가중치를 구해준다.
			calculateCost();

//			// 잘 계산되었는지 인접행렬 테스트
//			for (int i = 0; i < N; i++)
//				System.out.println(Arrays.toString(adjMatrix[i]));
//			System.out.println();
			
			visited = new boolean[N];
			findLowCost(0);

			for (int i = 0; i < N; i++) {
//				// 무슨 비용이 더해지는지 테스트
//				System.out.println(land[i][1]);
				lowCost += land[i][1];
			}
			
			long ans = Math.round(lowCost);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	/**
	 * 해저터널의 공사비용을 adjMatrix에 넣어주는 함수
	 */
	private static void calculateCost() {
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double x = state[i][0] - state[j][0];
				double y = state[i][1] - state[j][1];

				double dd = x * x + y * y;

				adjMatrix[i][j] = adjMatrix[j][i] = E * dd;
			}
		}
	}

	/**
	 * 해저터널 건설에 가장 적은 비용을 찾아주는 함수
	 * 
	 * @param num 섬의 번호
	 */
	private static void findLowCost(int num) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(num);
		visited[num] = true;
		land[num][1] = 0;

		while (!queue.isEmpty()) {
			// 이번 사이클에서 선택된 섬의 인덱스
			int idx = queue.poll();
			// 비용이 가장 낮은 섬을 찾아라
			double findLow = INF;
			// 비용이 가장 작은 섬의 Idx
			int minIdx = -1;
			
			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;
				
				// 방문처리가 안되어있으면 각 노드 distance 최신화
				// 가는 비용
				double cost = adjMatrix[idx][i];
				
				if (land[i][1] > cost) {
					land[i][1] = cost;
				}
				
				// 가는데에 
				if (land[i][1] < findLow) {
					findLow = land[i][1];
					minIdx = i;
				}
			}
			
			if (minIdx == -1)
				break;
			
//			System.out.printf("최소의 인덱스: %d, 최소비용: %f\n", minIdx, findLow);
			// 가장 작은 거리를 가진 노드를 찾았으니 큐에 넣어준다.
			queue.offer(minIdx);
			visited[minIdx] = true;
			land[minIdx][1] = findLow;
		}
	}
}
