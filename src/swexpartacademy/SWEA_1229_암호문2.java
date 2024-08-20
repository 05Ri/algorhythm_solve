package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1229_암호문2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Integer> password = new ArrayList<>();
		
		for (int tc = 1; tc <= 10; tc++) {
			// 원본 암호문의 길이 N
			int N = Integer.parseInt(br.readLine());
			
			// 원본 암호문
			st = new StringTokenizer(br.readLine());
			// 리스트에 넣기
			while (st.hasMoreTokens())
				password.add(Integer.parseInt(st.nextToken()));
			
			// 명령어의 개수
			int comCnt = Integer.parseInt(br.readLine());
			// 명령어
			st = new StringTokenizer(br.readLine());
			
//			// 출력할 최소값 찾기
//			int fixMin = Integer.MAX_VALUE;
			
			// 명령어 분기
			while (st.hasMoreTokens()) {
				switch (st.nextToken()) {
				case "I":
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					// x번째부터 y개 추가해야하므로
					for (int i = x; i < x + y; i++)
						password.add(i, Integer.parseInt(st.nextToken()));
//					// 수정된 암호문의 출력값 갱신
//					if (fixMin > x)
//						fixMin = x;
					break;
				case "D":
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					// x번째부터 y개 제거한다. x번째를 지우면 알아서 x번째로 인덱스가 땡겨진다.
					for (int i = x; i < x + y; i++)
						password.remove(x);
//					// 수정된 암호문의 출력값 갱신
//					if (fixMin > x)
//						fixMin = x;
//					break;
				}
			}
			
			System.out.printf("#%d ", tc);
			// 10개 출력
			for (int i = 0; i < 10; i++)
				System.out.print(password.get(i) + " ");
			System.out.println();
			password.clear();
		}
	}
}
