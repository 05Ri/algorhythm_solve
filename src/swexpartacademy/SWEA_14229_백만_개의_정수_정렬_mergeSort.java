package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_14229_백만_개의_정수_정렬_mergeSort {
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
		mergeSort(0, MILION - 1);

		System.out.println(A[MILION / 2]);
	}

	/**
	 * 병합 정렬
	 * 
	 * @param left  배열의 왼쪽 끝 인덱스
	 * @param right 배열의 오른쪽 끝 인덱스
	 */
	public static void mergeSort(int left, int right) {
		// 왼쪽 끝과 오른쪽 끝이 유효하다면
		if (left < right) {
			// 가운데 구하기
			int mid = (left + right) / 2;
			// 다음 왼쪽 부분 배열의 처음과 끝
			mergeSort(left, mid);
			// 오른쪽 부분 배열의 처음과 끝
			mergeSort(mid + 1, right);
			// 병합으로 넘긴다
			merge(left, mid, right);
		}
	}

	/**
	 * 병합
	 * 
	 * @param left  배열의 왼편의 맨 왼쪽
	 * @param mid   배열의 가운데
	 * @param right 배열의 오른편의 맨 왼쪽
	 */
	public static void merge(int left, int mid, int right) {
		// 배열 왼편의 처음
		int L = left;
		// 배열 오른편의 처음
		int R = mid + 1;
		// 정렬된 배열에 위치할 인덱스
		int idx = left;

		// 원본 배열에서의 비교
		// 왼편과 오른편이 각자의 오른쪽 끝 범위를 벗어날 때까지
		while (L <= mid && R <= right) {
			// 부분 배열의 왼편과 오른편의 맨 왼쪽부터 차례로 비교하여 큰 쪽이 먼저 배치되고
			// 부분배열과 정렬된 배열의 인덱스도 늘려준다.
			if (A[L] <= A[R])
				sortedArr[idx++] = A[L++];
			else
				sortedArr[idx++] = A[R++];
		}
		// 왼편 또는 오른편의 원소가 남아있을 때 없어질 때까지 전부 뱉어낸다
		while (L <= mid) {
			sortedArr[idx++] = A[L++];
		}
		while (R <= right) {
			sortedArr[idx++] = A[R++];
		}
		
		// 원본에 반영하기
		for (int i = left; i <= right; i++) {
			A[i] = sortedArr[i];
		}
	}
}
