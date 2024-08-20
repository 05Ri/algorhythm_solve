package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751_수_정렬하기_2_머지소트 {
	static int N;
	static int[] arr, sortArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		sortArr = new int[arr.length];
		
		mergeSort(0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	private static void mergeSort(int left, int right) {
		if (left >= right)
			return;
		
		int mid = (left + right) / 2;
		
		mergeSort(left, mid);
		mergeSort(mid + 1, right);
		merge(left, mid, right);
	}

	private static void merge(int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;
		
		while (l <= mid && r <= right) {
			if (arr[l] <= arr[r])
				sortArr[idx++] = arr[l++];
			else
				sortArr[idx++] = arr[r++];
		}
		
		while (l <= mid) sortArr[idx++] = arr[l++];
		while (r <= right) sortArr[idx++] = arr[r++];
		
		for (int i = left; i <= right; i++) {
			arr[i] = sortArr[i];
		}
	}
}
