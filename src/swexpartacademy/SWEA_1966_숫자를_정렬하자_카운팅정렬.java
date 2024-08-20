package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1966_숫자를_정렬하자_카운팅정렬 {
	// Solution_CountingSort
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력받을 숫자열 배열 만들기
		List<Integer> arr = new ArrayList<>();

		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			// 개수 값 입력받기
			int N = Integer.parseInt(br.readLine());

			// 배열값 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 배열값을 배열에 넣어주기
			while (st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			// 출력을 저장할 배열 생성
			arr = CountingSort(arr);

			// 출력
			System.out.print("#" + t + " ");
			for (int i : arr)
				System.out.print(i + " ");
			System.out.println();
			
			arr.clear();
		}
	}

	public static List<Integer> CountingSort(List<Integer> arr) {
		// 최대값 찾을 변수 설정
		int max = Integer.MIN_VALUE;
		for (int i : arr) {
			if (i > max)
				max = i;
		}
		
		// 카운트 배열 생성
		int[] count = new int[max + 1];
		
		// 카운트 배열에 카운트한다
		for (int i = 0; i < arr.size(); i++)
			count[arr.get(i)]++;
		
		// 카운트 배열을 prifix로 조정한다
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1]; 
		}
		
		int[] sorted = new int[arr.size() + 1];
		
		// 역방향 순회하며 prifix의 해당 인덱스의 값을 감소해주며 정렬해준다...!
		for (int i = arr.size() - 1; i >= 0 ; i--) {
			sorted[count[arr.get(i)]--] = arr.get(i);
		}
		
		// 받은 arr를 초기화해주고 
		arr.clear();
		
		// 정렬된 배열의 인덱스 1부터 다시 넣어준다
		for (int i = 1; i < sorted.length; i++)
			arr.add(sorted[i]);
		
		return arr;
	}
}
