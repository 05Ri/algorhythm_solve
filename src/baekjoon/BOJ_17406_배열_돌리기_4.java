package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17406_배열_돌리기_4 {
	// 배열 크기, 회전 연산의 수, 행의 최소 합
	static int N, M, K, minRow = Integer.MAX_VALUE;
	// 배열, 좌표 저장배열
	static int[][] arr, cal;
	// 방문 체크할 배열
	static boolean[] visited;
	// 좌표 순서를 넣어줄 배열
	static int[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 세로크기
		N = Integer.parseInt(st.nextToken());
		// 가로크기
		M = Integer.parseInt(st.nextToken());
		// 연산 수
		K = Integer.parseInt(st.nextToken());

		// 배열과 회전연산 초기화
		arr = new int[N][M];
		cal = new int[K][4];

		// 배열 입력받기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 회전 연산을 입력받고 좌표 계산하여 저장하기
		// cal[K]은 연산을 할 좌표들이 저장된 곳 < 이곳을 순열로 골라서 계산한다.
		// cal[K][4]는 계산을 시작할 각 r과 c의 정보가 저장된 곳
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			cal[i][0] = r - s;
			cal[i][1] = c - s;
			cal[i][2] = r + s;
			cal[i][3] = c + s;
		}

		visited = new boolean[K];
		select = new int[K];

		// 답 구하기
		doAns(0);

		System.out.println(minRow);
	}

	/**
	 * 회전 연산 순서 정해서 배열 돌려주기
	 * 
	 * @param idx cal의 인덱스
	 */
	private static void doAns(int idx) {
		if (idx >= K) {
//			System.out.println(Arrays.toString(select));
			// 배열 회전 들어가기
			turnArr();
			return;
		}

		for (int i = 0; i < K; i++) {
			if (visited[i])
				continue;

			select[idx] = i;
			visited[i] = true;
			doAns(idx + 1);
			visited[i] = false;
		}
	}

	/**
	 * 배열 돌리기
	 */
	private static void turnArr() {
		int[][] copyArr = copyArr();
		
//		for (int r = 0; r < N; r++) {
//			System.out.println(Arrays.toString(copyArr[r]));
//		}
		

		// 정해준 순서대로 저장해둔 select를 활용한다.
		// cal에 저장했던 꼭지점들을 꺼내와서 활용해야한다.
		for (int idx = 0; idx < K; idx++) {
			int k = select[idx];
			// 돌려줘야하는 껍데기 수 알아내기
			// 선택된 좌표들로 사각형을 보면 무조건 정사각형이므로 한 변만 생각해도 된다.
			int skin = Math.abs(cal[k][0] - cal[k][2]) / 2;
//			System.out.println(skin);
			
			// 껍데기 수 만큼 돌려줘야한다.
			for (int n = 0; n < skin; n++) {
				int r1 = cal[k][0] - 1 + n;
				int c1 = cal[k][1] - 1 + n;
				int r2 = cal[k][2] - 1 - n;
				int c2 = cal[k][3] - 1 - n;
				
//				System.out.printf("%d, %d, %d, %d\n", r1, c1, r2, c2);

				// 돌릴 부분의 겹치는 점 저장
				int tmp = copyArr[r1][c1];

				// 1. 왼쪽 부분
				for (int r = r1 + 1; r <= r2; r++) {
					copyArr[r - 1][c1] = copyArr[r][c1];
				}
				
//				System.out.println("왼쪽 부분");
//				for (int r = 0; r < N; r++) {
//					System.out.println(Arrays.toString(copyArr[r]));
//				}

				// 2. 아래 부분
				for (int c = c1 + 1; c <= c2; c++) {
					copyArr[r2][c - 1] = copyArr[r2][c];
				}
				
//				System.out.println("아래 부분");
//				for (int r = 0; r < N; r++) {
//					System.out.println(Arrays.toString(copyArr[r]));
//				}

				// 3. 오른쪽 부분
				for (int r = r2; r > r1; r--) {
					copyArr[r][c2] = copyArr[r - 1][c2];
				}
				
//				System.out.println("오른쪽 부분");
//				for (int r = 0; r < N; r++) {
//					System.out.println(Arrays.toString(copyArr[r]));
//				}

				// 4. 위쪽 부분
				for (int c = c2; c > c1; c--) {
					copyArr[r1][c] = copyArr[r1][c - 1];
				}
				
//				System.out.println("위쪽 부분");
//				for (int r = 0; r < N; r++) {
//					System.out.println(Arrays.toString(copyArr[r]));
//				}

				// 겹친 부분 넣어주기
				copyArr[r1][c1 + 1] = tmp;
				
//				System.out.println(k + "결과");
//				for (int r = 0; r < N; r++) {
//					System.out.println(Arrays.toString(copyArr[r]));
//				}
			}
			
		}
		
		// 각 행의 합 구해서 비교하여 행 최대 합 구해주기
		for (int r = 0; r < N; r++) {
			int sum = 0;
			for (int c = 0; c < M; c++) {
				sum += copyArr[r][c];
			}
//			System.out.printf("%d행의 합: %d, 여태 나온 최대 합: %d%n", r, sum, minRow);
			minRow = minRow < sum ? minRow : sum;
		}
	}

	/**
	 * 원본 배열 보존을 위한 배열 복사 함수
	 * 
	 * @return 복사된 배열
	 */
	private static int[][] copyArr() {
		int[][] copyArr = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copyArr[r][c] = arr[r][c];
			}
		}

		return copyArr;
	}
}