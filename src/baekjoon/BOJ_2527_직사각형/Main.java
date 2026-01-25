package baekjoon.BOJ_2527_직사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

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

			// 1. 아예 분리된 경우
			if (p1 < x2 || q1 < y2 || q2 < y1 || p2 < x1) {
				sb.append('d');
			}
			// 2. 한 점에서 만나는 경우
			else if (
				(p1 == x2 && q1 == y2) ||
				(q2 == y1 && p2 == x1) ||
				(p1 == x2 && y1 == q2) ||
				(x1 == p2 && q1 == y2)
			) {
				sb.append('c');
			}
			// 3. 선으로 만나는 경우
			else if (
				(p1 == x2 && q1 > y2) ||
				(p1 > x2 && q1 == y2) ||
				(q2 == y1 && p2 > x1) ||
				(q2 > y1 && p2 == x1)
			) {
				sb.append('b');
			}
			// 그 외
			else {
				sb.append('a');
			}

			sb.append('\n');
		}

		System.out.println(sb);
	}
}
