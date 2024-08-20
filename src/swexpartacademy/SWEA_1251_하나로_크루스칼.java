package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_1251_하나로_크루스칼 {
	// 부분집합
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 섬의 개수
			int V = Integer.parseInt(br.readLine());

			// 각 X, Y 좌표 입력받기
			StringTokenizer stX = new StringTokenizer(br.readLine());
			StringTokenizer stY = new StringTokenizer(br.readLine());

			// 환경 부담 세율 실수
			double E = Double.parseDouble(br.readLine());

			// 섬 위치 저장 [섬 번호][0: x좌표 / 1: y좌표]
			int[][] landPos = new int[V][2];
			for (int i = 0; i < V; i++) {
				landPos[i][0] = Integer.parseInt(stX.nextToken());
				landPos[i][1] = Integer.parseInt(stY.nextToken());
			}

			// 각 섬과 섬 사이의 비용을 인접행렬로 저장
			double[][] cost = new double[V][V];
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (i == j)
						cost[i][j] = cost[j][i] = 0;

					double x = landPos[j][0] - landPos[i][0];
					double y = landPos[j][1] - landPos[i][1];

					double dd = x * x + y * y;

					cost[i][j] = E * dd;
				}
			}

//			// cost 인접행렬 테스트
//			for (int i = 0; i < V; i++) {
//				System.out.println(Arrays.toString(cost[i]));
//			}
//			System.out.println();

			// 인접 행렬로 구해진 간선들 정보 입력받기
			int edge = V * V - V;
			double[][] info = new double[edge][3];
			int idx = 0;
			for (int a = 0; a < V; a++) {
				for (int b = 0; b < V; b++) {
					if (a == b)
						continue;

					// a섬과 b섬 사이의 비용
					info[idx][0] = cost[a][b];
					// a섬
					info[idx][1] = a;
					// b섬
					info[idx][2] = b;
					idx++;
				}
			}

			// 간선 정보 입력받았으니 info[][0] 기준 오름차순 정렬 비용 기준 가장 적은 비용부터 탐색
			Arrays.sort(info, new Comparator<double[]>() {
				@Override
				public int compare(double[] o1, double[] o2) {
					if (o1[0] < o2[0])
						return -1;
					else if (o1[0] == o2[0])
						return 0;
					else
						return 1;
				}
			});

//			// 간선 정보들 테스트
//			for (int i = 0; i < edge; i++) {
//				System.out.println(Arrays.toString(info[i]));
//			}
//			System.out.println();
			
			// 섬의 초기 부분집합 생성(makeset)
			p = new int[V];
			for (int i = 0; i < V; i++) {
				p[i] = i;
			}
			
			// 최소 비용
			double minCost = 0;
			// 뽑은 간선 개수
			int pick = 0;

			// 이제 info를 이용하여 최소거리를 구한다
			for (int i = 0; i < edge; i++) {
				int a = (int) info[i][1];
				int b = (int) info[i][2];
				
				// a와 b의 부분집합이 같으면 싸이클이 생성되므로 건너뛴다.
				if (findset(a) == findset(b))
					continue;
				
				
				// a와 b 사이의 간선을 놓았다면
				// 거리를 더해준다
				minCost += info[i][0];
				// 합해준다
				union(a, b);
				pick++;
				
//				// 무슨 거리가 더해지는지 테스트
//				System.out.println(info[i][0]);
//				// 합한 후 집합 테스트
//				System.out.println(Arrays.toString(p));
				
				// 선택한 간선이 (노드 수 - 1) 초과라면 나머지 간선 정보들은 확인할 필요 없다.
				if (pick > V - 1)
					break;
			}
			
			long ans = Math.round(minCost);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void union(int a, int b) {
		p[findset(b)] = findset(a);
	}

	private static int findset(int x) {
		if (p[x] != x)
			p[x] = findset(p[x]);
		
		return p[x];
	}
}
