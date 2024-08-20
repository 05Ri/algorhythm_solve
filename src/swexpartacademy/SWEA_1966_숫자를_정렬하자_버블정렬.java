package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1966_숫자를_정렬하자_버블정렬 {
	// Solution_BubbleSort
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력받을 숫자열 배열 만들기
		List<Integer> arr = new ArrayList<>();

		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			String N = br.readLine();

			// 배열값 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 배열값을 배열에 넣어주기
			while (st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}

			BubbleSort(arr);

			// 출력
			System.out.print("#" + t + " ");
			for (int i : arr)
				System.out.print(i + " ");
			System.out.println();
			
			arr.clear();
		}
	}

	public static void BubbleSort(List<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 1; j < arr.size() - i; j++) {
				if (arr.get(j) < arr.get(j - 1)) {
					int temp = arr.get(j);
					arr.set(j, arr.get(j - 1));
					arr.set(j - 1, temp);
				}
			}
		}
	}
}
