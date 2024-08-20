package swexpartacademy;

import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_3289_서로소_집합_보충수업 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(sc.nextLine());

		for (int tc = 1; tc < testCase; tc++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());

			int N, M;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// 부모를 저장할 parent 배열
			int[] parent = new int[N + 1];

			// 초기값 저장
			for (int i = 0; i <= N; i++) {
				parent[i] = i;
			}

			// tc 케이스를 일단 먼저 저장하고 시작
			sb.append(String.format("#%d ", tc));

			// 내가 0인지 1인지 확인 (action)
			// a, b를 입력받자
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(sc.nextLine());
				// 0인지 1인지
				int action = Integer.parseInt(st.nextToken());

				// a
				int a = Integer.parseInt(st.nextToken());

				// b
				int b = Integer.parseInt(st.nextToken());
				
				// 여기서 작업을 시작하면 된다.
				if (action == 0) {
					// 합집합
					union(parent, a, b);
				} else {
					// 연결 여부만 확인하면 된다.
					// sameGroup 활용
					sb.append(sameGroup(parent, a, b));
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
		
		sc.close();
	}

	// 각 원소가 초기에 자기자신만의 집합을 가지고 있다.
	// 들어오는 입력에 따라서 합집합과 같은 집합인지 확인하는 계산을 수행

	// 0 > 합집합을 수행
	// 1 > 같은 집합인지 확인

	// 각 집합의 부모가 누구인지 정해줘야한다.
	// 1 > 같은 집합인지 확일할 때는 부모가 같으면 같은 집합이다.
	// 0 > 부모가 같은지 먼저 확인을 하고 부모가 다르면 합집합 연산을 수행한다.

	// 같은 그룹인지 아닌지 찾아야한다.
	private static int sameGroup(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);

		// 같은 그룹이면 1, 다른 그룹이면 0
		if (a == b)
			return 1;
		else
			return 0;
	}

	// 합집합을 만들어줘야한다.
	private static void union(int[] parent, int a, int b) {
		// a랑 b 부모들을 받아와서 하나의 부모로 합쳐주기
		a = find(parent, a);
		b = find(parent, b);

		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	// 부모가 다를 때 부모를 찾아줘야한다.
	private static int find(int[] parent, int n) {
		if (parent[n] == n) {
			return n;
		} else {
			return parent[n] = find(parent, parent[n]);
		}
	}

	// 합집합을 만들어줘야한다.

	// 부모가 다를때 부모를 찾아줘야한다.
}
