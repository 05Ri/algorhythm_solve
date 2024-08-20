package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4836_색칠하기 {
	public static final int N = 10;
	public static int[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 테스트케이스 개수
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			// 격자 크기는 10X10으로 문제에서 주어졌다.
			grid = new int[N][N];
			// 칠할 영역의 개수 입력받기(number of colored)
			int noc = Integer.parseInt(br.readLine().trim());

			// 영역의 개수만큼 반복
			for (int $ = 0; $ < noc; $++) {
				// 영역의 정보들의 줄 입력받기
				st = new StringTokenizer(br.readLine());

				// 좌표 입력받을 배열 생성(coordinate)
				int[] cdn = new int[4];
				
				// 좌표 입력하기
				// 0 <= 왼쪽 위의 r1, c1 / 오른쪽 아래의 r2, c2 <= 9
				for (int i = 0; i < 4; i++) {
					cdn[i] = Integer.parseInt(st.nextToken());
				}
					
				// 칠해주기
				// 자신의 색깔과 좌표를 인자로 넘긴다
				coloring(Integer.parseInt(st.nextToken()), cdn);
			}
//			// 테스트
//			for (int i = 0; i < N; i++)
//				System.out.println(Arrays.toString(grid[i]));
//			System.out.println();

			// 보라색 영역 개수 세기
			int purple = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (grid[r][c] == 3)
						purple++;
				}
			}

			System.out.printf("#%d %d\n", t, purple);
		}
	}

	/**
	 * 입력받은 색깔과 좌표로 영역을 칠하는 함수
	 * 
	 * @param color 색깔의 숫자
	 * @param cdn 입력받는 좌표 배열
	 */
	public static void coloring(int color, int[] cdn) {
		// 좌표 크기 비교
		// 행
		int rMax = (cdn[0] >= cdn[2]) ? cdn[0] : cdn[2];
		int rMin = (cdn[0] <= cdn[2]) ? cdn[0] : cdn[2];
		// 열
		int cMax = (cdn[1] >= cdn[3]) ? cdn[1] : cdn[3];
		int cMin = (cdn[1] <= cdn[3]) ? cdn[1] : cdn[3];
		
		for (int r = rMin; r <= rMax; r++) {
			for (int c = cMin; c <= cMax; c++) {
				// 칠해지지 않았을 때
				// (좌표값이 0일 때)
				if (grid[r][c] == 0) {
					// 본인 색으로 칠해준다
					grid[r][c] = color;
				}

				// 빨간색이고 color가 파란색이거나
				// 파란색이고 color가 빨간색이면
				// (좌표값에 자신의 색을 더하면 3일 때)
				if (grid[r][c] + color == 3) {
					// 보라색으로 칠해준다
					grid[r][c] = 3;
				}
				
				// 좌표값이 3일때는 이미 보라색이므로 냅둔다.
			}
		}
	}
}
