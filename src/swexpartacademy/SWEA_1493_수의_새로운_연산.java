package swexpartacademy;

import java.util.Scanner;

public class SWEA_1493_수의_새로운_연산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// p와 q가 10000 이므로 숫자를 출력하여 확인한 결과 196 X 196이면 됐다. 그러나 좌표가 더 커야할듯...
		int[][] plane = new int[1001][1001];

		int n = 1; // 좌표를 제한할 변수
		int cnt = 1; // 좌표평면에 넣어줄 숫자

		// 숫자 좌표평면 만들기
		// x는 1부터 n까지
		// y는 n부터 1까지 동시에 변화하며 값이 들어간다.
		while (true) {
			for (int x = n, y = 1; x > 0; x--, y++) {
				plane[x][y] = cnt;
				cnt++;
			}
			n++;
			if (n > 300) break; // 범위를 넘어서면 while 탈출
		}
			
//			// 입력이 잘 되었는지 test 출력
//			for (int x = n - 1; x > 0; x--) {
//				for (int y = 1; y < n; y++) {
//					System.out.printf("%-6d", plane[x][y]);
//				}
//				System.out.println();
//			}

//			while (true) {
//				for (int x = 1, y = n; y > 0; x++, y--) {
//					plane[x][y] = cnt;
//					cnt++;
//				}
//				n++;
//				if (cnt > 10000)
//					break;
//			}

		// 입력받기
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int result = 0;
			int px = 0, py = 0, qx = 0, qy = 0; // p와 q 각각이 위치한 좌표를 넣어줄 값

			for (int x = 1; x < plane.length; x++) {
				for (int y = 1; y < plane[x].length; y++) {
					if (plane[x][y] == p) {
						System.out.printf("(%d, %d)에서 %d 발견\n", x, y, p);
						px = x; py = y;
					}
					if (plane[x][y] == q) {
						System.out.printf("(%d, %d)에서 %d 발견\n", x, y, q);
						qx = x; qy = y;
					}
				}
				// p와 q의 좌표를 모두 구했다면 탈출
				if (px != 0 && py != 0 && qx != 0 && qy != 0) break;
			}

			int x = px + qx;
			int y = py + qy;

			System.out.printf("#%d %d\n", t, plane[x][y]);
		}
	}
}
