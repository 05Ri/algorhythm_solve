package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_직사각형_생각더해보자 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 4줄 주어진다고 했으므로
		for (int $ = 0; $ < 4; $++) {
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
			
			
		}
	}
}
