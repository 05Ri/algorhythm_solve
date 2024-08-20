package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1966_숫자를_정렬하자 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			// 숫자의 개수 입력받기
			int n = Integer.parseInt(br.readLine());
			// 숫자 줄 입력받기
			st = new StringTokenizer(br.readLine());
			
			// 숫자들 넣을 배열 생성
			int[] arr = new int[n];
			// 숫자들 넣기
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i < n; i++) {
				int key = arr[i];
				
				// 최소값 저장할 변수
				int min;
				
				// key값 전에를 비교한다.
				for (min = i - 1; min >= 0; min--) {
					// min에 있는 값이 key보다 작다면 탈출한다.
					if (arr[min] <= key)
						break;
					
					// 앞에꺼를 미리 땡겨온다.
					arr[min + 1] = arr[min];
				}
				// min이 -1 되어서 반복문을 탈출했기 때문에
				// 배열의 (min + 1)에 key값을 넣어준다.
				arr[min + 1] = key;
			}
			
			System.out.printf("#%d ", t);
			// 정렬된 수열 출력
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
