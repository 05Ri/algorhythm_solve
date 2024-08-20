package swexpartacademy.사칙연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
class Node {
	String data;
	int left;
	int right;
	
	Node() {}
	
	Node(String data) {
		this.data = data;
	}
}

public class SWEA_1232_사칙연산 {
	static int N;
	static Node[] nodes;
	static Stack<Integer> cal = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			// 정점의 개수 N
			N = Integer.parseInt(br.readLine());
			
			nodes = new Node[N + 1];
			
			// 각 정점 입력받기
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				// 노드 번호 입력받기
				int nodeNum = Integer.parseInt(st.nextToken());
				Node a = new Node();
				nodes[nodeNum] = a;
				
				// 노드 데이터 입력받기
				a.data = st.nextToken();
				switch (a.data) {
				case "+":
				case "*":
				case "-":
				case "/":
					a.left = Integer.parseInt(st.nextToken());
					a.right = Integer.parseInt(st.nextToken());
					break;
				}
			}
			
			inorder(1);
			
			
			
			System.out.printf("#%d ", tc);
			System.out.println(cal.pop());
		}
	}
	
	public static void inorder(int idx) {
		if (idx > N || idx == 0)
			return;
		
		String d = nodes[idx].data;
		
		inorder(nodes[idx].left);
		inorder(nodes[idx].right);
		if (d.equals("+") || d.equals("*") || d.equals("-") || d.equals("/")) {
			int b = cal.pop();
			int a = cal.pop();
			switch(d) {
			case "+":
				cal.push(a + b);
				break;
			case "*":
				cal.push(a * b);
				break;
			case "-":
				cal.push(a - b);
				break;
			case "/":
				cal.push(a / b);
				break;
			}
		} else {
			cal.push(Integer.parseInt(d));
		}
//		System.out.print(nodes[idx].data + " ");
	}
}
