package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1966_숫자를_정렬하자_선택정렬 {
	// Solution_SelectionSort
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력받을 숫자열 배열 만들기
		List<Integer> arr = new ArrayList<>();

		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			// 개수 값 입력받기
			String N = br.readLine();

			// 배열값 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 배열값을 배열에 넣어주기
			while (st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}

			SelectionSort(arr);

			// 출력
			System.out.print("#" + t + " ");
			for (int i : arr)
				System.out.print(i + " ");
			System.out.println();
			
			arr.clear();
		}
	}

	public static void SelectionSort(List<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {	// i가 최소값이 될 것이므로
			int min = Integer.MAX_VALUE;
			int indexOfMin = 0;
			for (int j = i; j < arr.size(); j++) { // i번째부터 탐색
				if (min > arr.get(j)) { // 최소값과 그 위치의 인덱스 구하기
					min = arr.get(j);
					indexOfMin = j;
				}
			}
			arr.set(indexOfMin, arr.get(i)); // 최소값의 인덱스에 맨 앞의 값 넣기
			arr.set(i, min); // 맨 앞의 인덱스에 최소값 넣기
		}
	}
}
