package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649_N과M_1 {
	static int N, M;
	static int[] select;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫째 줄 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1 ~ N 자연수
		N = Integer.parseInt(st.nextToken());
		// 중복없이 M개 고르기
		M = Integer.parseInt(st.nextToken());

		// 방문처리할 배열
		visited = new boolean[N + 1];
		// 출력할 숫자 배열
		select = new int[M];


		permutation(0);
	}

	public static void permutation(int count) {
		// 기저 조건
		if (count >= M) {
			for (int i : select)
				System.out.print(i + " ");
			System.out.println();
			return;
		}

		// 재귀 호출
		for (int i = 1; i <= N; i++) {
			if (visited[i] == true)
				continue;
			select[count] = i;
			visited[i] = true;
			permutation(count + 1);
			visited[i] = false;
		}		
	}
}
