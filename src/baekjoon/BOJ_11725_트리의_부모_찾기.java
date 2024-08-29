package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11725_트리의_부모_찾기 {
	private static int N;
	private static int[] parentNode;
	private static ArrayList<Integer>[] tree;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		// ArrayList를 이용하여 노드 연결
		tree = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		// 들어온 숫자대로 서로의 노드 연결
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			
			tree[nodeA].add(nodeB);
			tree[nodeB].add(nodeA);
		}
		
		parentNode = new int[N + 1];
		visited = new boolean[N + 1];
		
		// 부모노드 찾아주기
		findParentNode(1);
		
//		System.out.println(Arrays.toString(tree));
//		System.out.println(Arrays.toString(parentNode));
		for (int i = 2; i < parentNode.length; i++) {			
			System.out.println(parentNode[i]);
		}
	}

	private static void findParentNode(int num) {
		visited[num] = true;
//		System.out.println(num);
		
		for (int i = 0; i < tree[num].size(); i++) {
			int parentCandidate = tree[num].get(i);
			
			if (!visited[parentCandidate]) {
				findParentNode(parentCandidate);
			} else {
				parentNode[num] = parentCandidate;
			}
		}
	}
}
