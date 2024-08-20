package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11004_K번째_수_퀵정렬 {
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		K = k - 1;
		
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		quicksort(0, N - 1);
		System.out.println(arr[K]);
	}

	private static void quicksort(int left, int right) {
		if (left >= right)
			return;

		int pivotIdx = partition(left, right);

		if (pivotIdx == K)
			return;
		else if (pivotIdx > K)
			quicksort(left, pivotIdx - 1);
		else
			quicksort(pivotIdx + 1, right);
	}

	private static int partition(int left, int right) {
		int pivot = arr[left];
		int l = left + 1;
		int r = right;

		while (l <= r) {
			while (l <= r && arr[l] <= pivot)
				l++;
			while (l <= r && arr[r] > pivot)
				r--;

			if (l < r) {
				int tmp = arr[l];
				arr[l] = arr[r];
				arr[r] = tmp;
			}
		}

		arr[left] = arr[r];
		arr[r] = pivot;
		return r;
	}
}
