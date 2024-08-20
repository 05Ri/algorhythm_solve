package swexpartacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7102_준홍이의_카드놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Queue<Integer> cardPack1 = new LinkedList<>();
		Queue<Integer> cardPack2 = new LinkedList<>();

		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 합한 숫자 카운팅해줄 배열 생성
			int[] cnt = new int[N + M + 1];

			// 카드팩 1, 2에 카드 주입
			for (int i = 1; i <= N; i++)
				cardPack1.add(i);
			for (int i = 1; i <= M; i++)
				cardPack2.add(i);
			
			for (int i = 0; i < N; i++) {
				int a = cardPack1.poll();
				for (int j = 0; j < M; j++) {
					int b = cardPack2.poll();
					cnt[a + b]++;
					cardPack2.offer(b);
				}
			}

			int cntMax = 0;
			for (int i = 0; i < N + M; i++) {
				if (cnt[i] > cntMax)
					cntMax = cnt[i];
			}

			System.out.printf("#%d ", t);
			for (int i = 0; i < N + M; i++) {
				if (cntMax == cnt[i])
					System.out.print(i + " ");
			}
			System.out.println();
			
			cardPack1.clear();
			cardPack2.clear();
		}
	}
}
