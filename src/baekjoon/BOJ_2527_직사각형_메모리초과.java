package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_직사각형_메모리초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 탐색은 오른쪽과 위쪽만 탐색하면 된다.
		int[] dx = { 1, 0 };
		int[] dy = { 0, 1 };

		// 4줄 주어진다고 했으므로
		nextCase: for (int $ = 0; $ < 4; $++) {
			// 줄 입력받기
			st = new StringTokenizer(br.readLine());

			// 직사각형1 입력받기
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());

			// 직사각형2 입력받기
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());
			
			// 왼쪽 아래 꼭짓점 좌표를 이용하여
			// 사각형들의 좌표를 원점에 가깝게 끌어온다.
			int minX = Math.min(x1, x2);
			int minY = Math.min(y1, y2);
			
			x1 -= minX; y1 -= minY;
			p1 -= minX; q1 -= minY;
			x2 -= minX; y2 -= minY;
			p2 -= minX; q2 -= minY;
			
			// 오른쪽 위 꼭짓점 좌표를 비교하여 평면의 최대 크기를 구해준다.
			int planeX = Math.max(p1, p2);
			int planeY = Math.max(q1, q2);

			// 평면 생성
			int[][] plane = new int[planeX + 1][planeY + 1];

			// 직사각형1 범위에 1 더해주기
			for (int y = q1; y >= y1; y--) {
				for (int x = p1; x >= x1; x--) {
					plane[x][y] += 1;
				}
			}

			// 직사각형2 범위에 1 더해주기
			for (int y = q2; y >= y2; y--) {
				for (int x = p2; x >= x2; x--) {
					plane[x][y] += 1;
				}
			}

			// 둘이 겹쳤으면 2가 탐색될테니 그걸 이용해준다.
			for (int y = 0; y < planeY; y++) {
				for (int x = 0; x < planeX; x++) {

					// 2가 탐색되었다면 점인지 선인지 면적인지 알기 위해서 탐색해준다.
					if (plane[x][y] == 2) {

						// 어느 방향에서 탐색되었는지 카운트 해준다.
						// 0이면 점, 1이면 선, 2이면 직사각형으로 만난 것이다.
						int direction = 0;

						// 범위는 좌표 평면 안에 있을 것이므로 신경 쓸 필요 없다.
						for (int d = 0; d < 2; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];

							// 탐색 했을 때 카운트해준다.
							if (plane[nx][ny] == 2)
								direction++;
						}

						if (direction == 0) {
							// 본인 말고는 2가 탐색되지 않았으므로 점이다.
							System.out.println('c');
							continue nextCase;
						} else if (direction == 1) {
							// 한쪽 방향으로만 탐색되었으므로 선이다.
							System.out.println('b');
							continue nextCase;
						} else if (direction == 2) {
							// 양쪽 방향으로 탐색되었으므로 면이다.
							System.out.println('a');
							continue nextCase;
						}
					}
				}
			}
			// 어느곳에도 2가 탐색되지 않았다면 d 케이스이다.
			System.out.println('d');
		}
	}
}
