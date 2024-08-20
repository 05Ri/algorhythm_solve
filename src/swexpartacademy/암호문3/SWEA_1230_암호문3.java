package swexpartacademy.암호문3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int data;
	Node next;
	Node prev;

	Node() {
	}

	Node(int data) {
		this.data = data;
	}
}

class LinkedList {
	Node head;
	Node tail;
	int size;

	public LinkedList() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * 인덱스가 유효한지 검사
	 */
	boolean isVaild(int idx) {
		if(idx < 0 || size < idx) {
			System.out.println("인덱스가 범위를 벗어났습니다...!");
			return false;
		}
		return true;
	}

	/**
	 * 뒤에 데이터를 추가한다
	 * 
	 * @param data 추가할 데이터
	 */
	void add(int data) {
		Node newNode = new Node(data);
		
		newNode.next = tail;
		newNode.prev = tail.prev;
		
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		
		size++;
	}

	/**
	 * 인덱스부터 데이터를 추가한다
	 * 
	 * @param idx  추가할 인덱스
	 * @param data 추가할 데이터
	 */
	void add(int idx, int data) {
		if (!isVaild(idx))
			return;
		
		Node newNode = new Node(data);
		Node curr = head;
		
		for (int i = 0; i < idx; i++) {
			curr = curr.next;
		}
		
		// newNode가 먼저 앞뒤로 연결
		newNode.next = curr.next;
		newNode.prev = curr;
		
		// newNode에 앞과 뒤가 연결
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		
		size++;
	}

	/**
	 * 해당 인덱스의 데이터를 삭제한다
	 * 
	 * @param idx 삭제할 데이터의 인덱스
	 */
	void remove(int idx) {
		if (!isVaild(idx))
			return;
		
		Node rmNode = head.next;
		
		for (int i = 0; i < idx; i++) {
			rmNode = rmNode.next;
		}
		
		rmNode.prev.next = rmNode.next;
		rmNode.next.prev = rmNode.prev;
		
		size--;
	}

	/**
	 * 해당 인덱스부터 데이터 10개를 출력한다. 만약 출력할 값이 10개가 안된다면 처음부터 출력한다.
	 * 
	 * @param idx 출력하고 싶은 인덱스 값
	 */
	void printList10Cnt(int idx) {
		Node curr = head.next;

		if (idx - 10 < 0) {
			for (int i = 0; i < 10; i++) {
				System.out.print(curr.data + " ");
				curr = curr.next;
			}
		} else {
			for (int i = 0; i < idx; i++) {
				curr = curr.next;
			}
			
			for (int i = 0; i < 10; i++) {
				System.out.print(curr.data + " ");
				curr = curr.next;
			}
		}
	}
}

public class SWEA_1230_암호문3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 10개의 테스트 케이스
		for (int t = 1; t <= 10; t++) {
			// 수정된 가장 작은 인덱스를 저장할 값
			int minX = Integer.MAX_VALUE;
			
			// 원본 암호문 뭉치 속 암호문의 개수 N
			int N = Integer.parseInt(br.readLine());

			// 원본 암호문 뭉치 입력받기
			st = new StringTokenizer(br.readLine());

			// 원본 암호문 뭉치 Linked List에 넣기
			LinkedList password = new LinkedList();
			while (st.hasMoreTokens()) {
				password.add(Integer.parseInt(st.nextToken()));
			}

			// 명령어의 개수 M
			int M = Integer.parseInt(br.readLine());

			// 명령어 줄 입력받기
			st = new StringTokenizer(br.readLine());

			// 명령어에 따라 암호문 수정하기
			for (int $ = 0; $ < M; $++) {
				int x, y;
				// 명령어 입력받기
				String command = st.nextToken();
				switch (command) {
				case "I": // 삽입의 경우
					x = Integer.parseInt(st.nextToken());
					minX = (minX < x) ? minX : x;
					y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						password.add(x++, Integer.parseInt(st.nextToken()));
					}
					break;
				case "D": // 삭제의 경우
					x = Integer.parseInt(st.nextToken());
					minX = (minX < x) ? minX : x;
					y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						password.remove(x);
					}
					break;
				case "A": // 추가의 경우
					y = Integer.parseInt(st.nextToken());
					for(int i = 0; i < y; i++) {
						password.add(Integer.parseInt(st.nextToken()));
					}
					break;
				}
			}

			// 정답 출력하기
			System.out.printf("#%d ", t);
			password.printList10Cnt(minX);
			System.out.println();
		}
	}
}
