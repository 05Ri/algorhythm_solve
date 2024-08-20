package swexpartacademy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1210_Ladder1 {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/swea_1210_Ladder1/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		int N = 100; // 배열 크기 고정
		int[][] ladder = new int[N][N]; // 사다리 공간

		for (int $ = 1; $ <= 10; $++) {
			int t = sc.nextInt(); // 테스트 케이스 번호 입력받기

			int xPoint = 0; // 탐색할 x

			// 사다리 공간 입력받기
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					ladder[x][y] = sc.nextInt();
				}
			}

			// 사다리 공간에서 맨 밑에 2인 곳이 도착지점이므로 좌표찾기
			for (int x = 0; x < N; x++) {
				if (ladder[x][N - 1] == 2) {
					xPoint = x;
				}
			}

			int[] dx = { -1, 1 }; // x의 델타

			// 도착지점에서 출발지점 찾기 ladder[xArv][99] -> ladder[xDpt][0]

			// 좌우를 살펴서 1이 있다면 그 방향으로 0이 나올때까지 간다(범위 내에서).
			// 그 외에는 y - 1씩 해준다(위로 올라간다).
			for (int y = N - 1; y >= 0; y--) {
//				System.out.printf("%d, %d\n", xPoint, y);
				for (int d = 0; d < dx.length; d++) {
					int px = xPoint;
					// px와 델타의 합이 ladder 안의 범위이고
					if (px + dx[d] >= 0 && px + dx[d] < N) {
						// px의 양 옆이 1이고
						if (ladder[px + dx[d]][y] == 1) {
							// ladder 가로로 질러가는 위쪽의 y값이 1일 때까지
							while (ladder[px + dx[d]][y - 1] != 1) {
//								System.out.printf("(%d, %d) px = %d\n", xPoint, y, px);
//								System.out.println(dx[d] + " 방향");
								px += dx[d]; // 해당 방향으로 계속 간다.
//								System.out.printf("px위치: %d\n", px);
							}
							// 위쪽의 y값이 1이 아닐 때 멈췄으므로
							// 멈춘 x좌표에서 델타 하나 더 간 값을 넣어줘야한다.
							xPoint = px + dx[d];

							// 만약 dx[0]을 했다면 d[1]로 다시 연산하여
							// 왔던 길을 다시 가야하므로 탈출해준다.
							// 왼쪽 방향이 우선이었으므로 오른쪽으로 다시 가게 된다.
							break;
						}
					}
				}
			}
			// y가 0이 되었으니 x의 출발 지점을 알 수 있다.
			System.out.printf("#%d %d\n", t, xPoint);
		}
	}
}
