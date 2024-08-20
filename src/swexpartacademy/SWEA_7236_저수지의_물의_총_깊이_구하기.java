package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7236_저수지의_물의_총_깊이_구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 0, -1, 0, -1, 1, 1, -1 };

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int N = Integer.parseInt(br.readLine());

			char[][] reservoir = new char[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					reservoir[r][c] = st.nextToken().charAt(0);
				}
			}
			
			int deepest = 1;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (reservoir[r][c] == 'G')
						continue;

					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (nr >= 0 && nr < N && nc >= 0 && nc < N && reservoir[nr][nc] == 'W')
							cnt++;
					}
					
					deepest = deepest >= cnt ? deepest : cnt;
				}
			}
			
			System.out.printf("#%d %d\n", tc, deepest);
		}
	}
}
