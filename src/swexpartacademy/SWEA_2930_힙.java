package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2930_힙 {
	static int[] heap;
	static int heapSize;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 수행해야하는 연산의 수 N
			int N = Integer.parseInt(br.readLine());
			
			// N번만큼 추가만 한다면 최대 배열의 크기는 N + 1이 된다.
			heap = new int[N + 1];
			heapSize = 0;
			
			// 테스트 케이스 출력
			System.out.printf("#%d ", tc);
			
			for (int $ = 0; $ < N; $++) {
				// 순서대로 수행해야하는 연산에 대한 정보
				st = new StringTokenizer(br.readLine().trim());
				// 무슨 연산을 해야하는지??
				String operator = st.nextToken();
				
				// 연산 번호에 따른 분기점
				switch (operator) {
				case "1":
					// 추가 연산
					heapPush(Integer.parseInt(st.nextToken()));
					break;
				case "2":
					// 삭제 연산은 루트노드에 있던 숫자 출력
					System.out.print(heapPop() + " ");
					break;
				}
			}
			// 다음 테스트 케이스를 위해 엔터
			System.out.println();
		}
	}
	
	/**
	 * 1번 연산을 수행
	 * @param data 추가할 값
	 */
	static void heapPush(int data) {
		// 순차로 데이터 추가하고
		heap[++heapSize] = data;
		
		// child와 parent는 힙의 인덱스 "현재 데이터"는 "자식"이다!
		int child = heapSize;
		int parent = child / 2;
		
		// 부모인덱스가 0 초과이거나 자식데이터가 부모데이터보다 클 동안
		while (parent > 0 && heap[parent] < heap[child]) {
			// 교체
			int temp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = temp;
			
			// 비교할 인덱스도 교체
			child = parent;
			parent /= 2;
		}
	}
	
	/**
	 * 2번 연산을 수행
	 * @return 힙에서 제거한 수
	 */
	static int heapPop() {
		// 힙에 아무것도 없어서 못꺼낼 경우 -1 반환
		if (heapSize == 0)
			return -1;
		
		// 제거할 맨 위의 데이터
		int rmData = heap[1];
		
		// 끝의 노드를 루트로 가져오고 끝부분 삭제
		heap[1] = heap[heapSize--];
		
		// 현재는 "내"가 "부모"이다!!
		// 부모와 그 자식들간의 크기 비교를 하여 더 큰 자식이랑 교체한다.
		int parent = 1;
		int child = parent * 2;
		
		// 우선 비교를 해주고
		if (child + 1 <= heapSize && heap[child] < heap[child + 1])
			child += 1;
		
		// 자식이 힙 크기보다 작고 부모의 데이터가 자식의 데이터보다 작을 동안
		while(heapSize >= child && heap[parent] < heap[child]) {
			
			// 교체
			int temp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = temp;
			
			// 인덱스도 교체
			parent = child;
			child *= 2;
			
			// 더 큰 자식을 먼저 찾아서 인덱스에 넣어주고
			if (child + 1 <= heapSize && heap[child] < heap[child + 1])
				child += 1;
		}
		
		return rmData;
	}
}
