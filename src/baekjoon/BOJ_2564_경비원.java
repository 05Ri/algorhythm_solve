package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564_경비원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫째줄 가져오기
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 가로
		int blockHor = Integer.parseInt(st.nextToken());
		// 세로
		int blockVer = Integer.parseInt(st.nextToken());
		// 둘째줄: 상점의 개수
		int storeCnt = Integer.parseInt(br.readLine());

		for (int $ = 0; $ < storeCnt; $++) {
			st = new StringTokenizer(br.readLine());
			// 북남서동 - 1234
			int direction = Integer.parseInt(st.nextToken());
			// 경계로부터의 거리 > 12 왼쪽으로부터 / 34 위쪽으로부터
			int position = Integer.parseInt(st.nextToken());
		}

		// 마지막 줄 - 동근이의 위치
		st = new StringTokenizer(br.readLine());
		
	}
}
