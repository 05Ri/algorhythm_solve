package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1208_Flatten {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			int dumpCnt = Integer.parseInt(br.readLine()); // 덤프 개수를 입력받는다.

			StringTokenizer st = new StringTokenizer(br.readLine()); // 박스의 높이들을 입력받는다.

			int[] boxH = new int[100]; // 박스 높이들을 저장할 배열 생성

			// 박스 높이들을 저장해준다.
			for (int i = 0; i < boxH.length; i++)
					boxH[i] = Integer.parseInt(st.nextToken());

			// 박스의 최대 높이는 100 이하이므로 카운팅할 배열의 크기를 101로 해준다.
			int[] cnt = new int[101];

			// 박스 높이 카운트해주기
			for (int i = 0; i < boxH.length; i++)
				cnt[boxH[i]]++;
			
			int right = cnt.length - 1; // 오른쪽 인덱스
			int left = 1; // 왼쪽 인덱스

			// 왼쪽과 오른쪽이 교차하기 전이거나 덤프 횟수가 남아있으면 진행
			while (left <= right && dumpCnt >= 0) {
				// 박스가 쌓일 왼쪽 인덱스 찾기
				for (int i = left; i < right; i++) {
					if (cnt[i] != 0) { // 값이 0이 아니면 멈춤
						left = i;
						break;
					}
					left++;
				}

				// 박스를 옮길 오른쪽 인덱스 찾기
				for (int i = right; i >= left; i--) {
					if (cnt[i] != 0) { // 값이 있으면 멈춤
						right = i;
						break;
					}
					right--;
				}
				
				// 박스 높이의 개수가 더 낮거나 같은 인덱스가 왼쪽일 경우
				if (cnt[left] <= cnt[right]) {
					dumpCnt -= cnt[left]; // 덤프 카운트의 수가 그만큼 줄어들고
					cnt[right - 1] += cnt[left]; // right 전의 박스 높이 개수가 left의 개수만큼 더해지고
					cnt[left + 1] += cnt[left]; // left 후의 박스 높이 개수도 마찬가지로 더해진다.
					cnt[right] -= cnt[left]; // right에 해당하는 박스는 빼진다.
					cnt[left] = 0; // 자신의 역할을 다 했으니 해당 높이는 존재하지 않는다.
				} else { // 박스 높이의 개수가 더 낮은 인덱스가 오른쪽일 경우
					dumpCnt -= cnt[right]; // 덤프 카운트의 수가 그만큼 줄어들고
					cnt[right - 1] += cnt[right]; // right 전의 박스 높이 개수가 right의 개수만큼 더해지고
					cnt[left + 1] += cnt[right]; // left 후의 박스 높이 개수도 마찬가지로 더해진다.
					cnt[left] -= cnt[right]; // left에 해당하는 박스는 빼진다.s
					cnt[right] = 0; // 자신의 역할을 다 했으니 해당 높이는 존재하지 않는다.
				}
				
//				//테스트
//				System.out.println(left + ", " + right + ", " + dumpCnt);
//				for (int i : cnt)
//					System.out.print(i + " ");
//				System.out.println();
			}
			
			// 최종으로 쌓인 박스 개수 찾기
			int afterFlatten = right - left;
			
			System.out.println("#" + t + " " + afterFlatten);
		}
	}
}
