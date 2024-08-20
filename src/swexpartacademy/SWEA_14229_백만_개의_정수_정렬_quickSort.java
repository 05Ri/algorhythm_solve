package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_14229_백만_개의_정수_정렬_quickSort {
	// 원본 배열, 정렬된 배열
	public static int[] A, sortedArr;
	// 백만을 상수로
	public static final int MILION = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 백만개를 줄으로 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 받은 정수를 배열에 넣기
		A = new int[MILION];
		for (int i = 0; i < MILION; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬된 배열 초기화
		sortedArr = new int[MILION];
		// 병합배열 시작
		quickSort(0, MILION - 1);

		System.out.println(A[MILION / 2]);
	}

	/**
	 * 퀵 정렬
	 * 
	 * @param left  배열의 맨 왼쪽
	 * @param right 배열의 맨 오른쪽
	 */
	public static void quickSort(int left, int right) {
		// 왼쪽과 오른쪽이 유효할 때
		if (left < right) {
			// 피봇값은 파티션 기법을 이용해서 꺼내올 것이다.
			int pivot = partition(left, right);
			// 피봇 기준으로 분할된 왼쪽 부분 배열에서 다시 퀵소트
			quickSort(left, pivot - 1);
			// 피봇 기준으로 분할된 오른쪽 부분 배열에서 다시 퀵소트
			quickSort(pivot + 1, right);
		}
	}

	/**
	 * 파티션 기법
	 * 
	 * @param left  부분 배열의 왼쪽 끝
	 * @param right 부분 배열의 오른쪽 끝
	 * @return
	 */
	public static int partition(int left, int right) {
		// 피봇은 left값으로 할 것이다.
		int pivot = A[left];
		// 왼쪽은 피봇 한칸 옆부터 시작
		int L = left + 1;
		int R = right;
		
		// L과 R이 교차할 때까지
		while (L <= R) {
			// L은 피봇보다 큰 위치를 찾아서 이동
			while (A[L] <= pivot && L <= R)
				L++;
			// R은 피봇보다 작은 위치를 찾아서 이동
			while (A[R] > pivot)
				R--;
			
			// 만약 LR이 유효하다면
			if (L < R) {
				// 원본 배열의 L 인덱스 값과 R 인덱스 값을 교환
				int tmp = A[L];
				A[L] = A[R];
				A[R] = tmp;
			}
		}
		
		// L과 R이 만나거나 교차했다면 R이 pivot이 있어야하는 위치까지 온 것이므로 교체
		A[left] = A[R];
		A[R] = pivot;
		
		// 피봇값의 위치 = R
		return R;
	}
}
