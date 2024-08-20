package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int data;
	Node link;
	
	public Node() {}
	
	public Node(int data) {
		this.data = data;
	}
}

class LinkedList {
	Node head;
	int size;
	
	LinkedList() {
		head = new Node();
	}
	
	// 인덱스가 유효한지 검사
	boolean isValid(int idx) {
		if (idx < 0 || size < idx) {
			System.out.println("오류: 인덱스가 범위를 벗어났습니다.");
			return false;
		}
		return true;
	}
	
	// data를 추가한다.
	void add(int data) {
		Node curr = head;
		
		while (curr.link != null) {
			curr = curr.link;
		}
		
		Node newNode = new Node(data);
		newNode.link = curr.link;
		curr.link = newNode;
		
		size++;
	}
	
	// idx 위치에 data를 삽입한다.
	void add(int idx, int data) {
		if (!isValid(idx))
			return;
		
		Node curr = head;
		
		for (int i = 0; i < idx; i++) {
			curr = curr.link;
		}
		
		Node newNode = new Node(data);
		newNode.link = curr.link;
		curr.link = newNode;
		
		size++;
	}
	
	// idx 위치에서 Linked List를 10개만 출력한다.
	void printList10Cnt(int idx) {
		Node curr = head.link;
		
		for (int i = 0; i < idx; i++) {
			curr = curr.link;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.print(curr.data + " ");
			curr = curr.link;
		}
	}
}

public class SWEA_1228_암호문1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 테스트케이스 10개
		for (int t = 1; t <= 10; t++) {
			// 수정된 가장 작은 인덱스를 저장할 값
			int minX = Integer.MAX_VALUE;
			
			// 원본 암호문의 길이 N
			int N = Integer.parseInt(br.readLine());
			// 원본 암호문 줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 원본 암호문 Linked List에 넣기
			LinkedList password = new LinkedList();
			while (st.hasMoreTokens()) {
				password.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령어의 개수
			int cntN = Integer.parseInt(br.readLine());
			// 명령어 라인 입력받기
			st = new StringTokenizer(br.readLine());
			
			// 명령어에 따라 암호문 수정하기
			for (int $ = 0; $ < cntN; $++) {
				// 명령글자
				String command = st.nextToken();
				
				// x의 위치
				int x = Integer.parseInt(st.nextToken());
				
				// 수정된 결과의 처음 10개를 출력해야하므로
				// x의 위치가 가장 작은 곳을 저장해둔다.
				minX = (minX < x) ? minX : x;
				
				// 삽입할 y개의 숫자
				int y = Integer.parseInt(st.nextToken());
				
				// 조건에 맞게 암호문 수정
				for(int u = 0; u < y; u++) {
					password.add(x++, Integer.parseInt(st.nextToken()));
				}
			}
			
			// 답 출력
			System.out.printf("#%d ", t);
//			if (minX - 10 < 0)
				password.printList10Cnt(0);
//			else
//				password.printList10Cnt(minX);
			System.out.println();
		}
	}
}
