package swexpartacademy.중위순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	String data;
	int left;
	int right;

	Node() {
	}

	Node(String data) {
		this.data = data;
	}
}

public class SWEA_1231_중위순회 {
	static int N;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());

			nodes = new Node[N + 1];

			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());

				int nodeNum = Integer.parseInt(st.nextToken());

				Node a = new Node();
				nodes[nodeNum] = a;

				a.data = st.nextToken();

				if (st.hasMoreTokens())
					a.left = Integer.parseInt(st.nextToken());
				if (st.hasMoreTokens())
					a.right = Integer.parseInt(st.nextToken());
			}
			
			System.out.printf("#%d ", tc);
			inorder(1);
			System.out.println();
		}
	}
	
	public static void inorder(int idx) {
		if (idx <= 0 || idx > N)
			return;
		
		inorder(nodes[idx].left);
		System.out.print(nodes[idx].data);
		inorder(nodes[idx].right);
	}
}
