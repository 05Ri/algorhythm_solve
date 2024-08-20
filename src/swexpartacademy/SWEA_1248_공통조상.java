package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1248_공통조상 {
	public static List<Integer> roots = new ArrayList<>();
	public static int[][] node;
	public static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 정보 줄 읽어들이기
			st = new StringTokenizer(br.readLine());
			// 정점의 개수 V
			int V = Integer.parseInt(st.nextToken());
			// 간선의 개수 E
			int E = Integer.parseInt(st.nextToken());
			// 공통 조상을 찾을 두 개의 정점 번호
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 각 정점의 상태를 나타낼 배열
			node = new int[V + 1][3];

			// 간선을 잇는 라인 입력받기
			st = new StringTokenizer(br.readLine());
			// 간선 잇기
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());

				// 양방향이므로 넣어준다.
				if (node[parent][0] == 0)
					node[parent][0] = child;
				else
					node[parent][2] = child;

				node[child][1] = parent;
			}

//			System.out.println(Arrays.deepToString(node));

			findAllRoots(A);
			int[] arrA = new int[roots.size()];
			for (int i = 0; i < roots.size(); i++) {
				arrA[i] = roots.get(i);
			}
			roots.clear();

			findAllRoots(B);
			int[] arrB = new int[roots.size()];
			for (int i = 0; i < roots.size(); i++) {
				arrB[i] = roots.get(i);
			}
			roots.clear();

//			System.out.println(Arrays.toString(arrA));
//			System.out.println(Arrays.toString(arrB));

			cnt = 0;
			int ancestor = 0;
			out: for (int i = 0; i < arrA.length; i++) {
				for (int j = 0; j < arrB.length; j++) {
					if(arrA[i] == arrB[j]) {
						ancestor = arrA[i];
						countSubTree(arrA[i]);
						break out;
					}
				}
			}
			System.out.printf("#%d %d %d\n", tc, ancestor, cnt);
		}
	}

	public static void findAllRoots(int n) {
		if (node[n][1] != 0) {
			roots.add(node[n][1]);
			findAllRoots(node[n][1]);
		}
		return;
	}

	public static void countSubTree(int n) {
		for (int i = 0; i <= 2; i += 2) {
			// 자식이 있으면
			if (node[n][i] != 0) {				
				countSubTree(node[n][i]);
			}
		}
		cnt++;
		return;
	}
}
