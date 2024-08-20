package swexpartacademy;

import java.util.Scanner;

public class SWEA_1206_view {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int cnt = 0; // 조망권을 확보한 세대 수
			int N = sc.nextInt(); // 건물 개수 입력받기
			int[] aptH = new int[N]; // 건물 높이(apt height 줄여서) 입력받을 개수 생성

			// 건물의 높이 입력받기
			for (int i = 0; i < aptH.length; i++) {
				aptH[i] = sc.nextInt();
			}

			// 앞의 2번째까지 0, 뒤에서 2번째부터 0이므로 범위를 고려해준다
			for (int i = 2; i < aptH.length - 2; i++) {
				for (int h = aptH[i]; h >= 0; h--) {
					if (h - aptH[i - 2] > 0 && h - aptH[i - 1] > 0 && h - aptH[i + 1] > 0 && h - aptH[i + 2] > 0) {
						cnt++;
					} else {
						break;
					}
				}
			}
			System.out.printf("#%d %d\n", t, cnt);
		}
	}
}
