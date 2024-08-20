package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_5658_보물상자_비밀번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());			
			// 문자열의 길이
			int N = Integer.parseInt(st.nextToken());
			// 찾아야하는 K번째 큰 수
			int K = Integer.parseInt(st.nextToken());
			// 문자열 길이 / 4
			int len = N / 4;
			// 숫자 중복 피하면서 넣기
			Set<Integer> numSet = new HashSet<>();
			
			// 16진수 숫자 줄 입력받기
			String str = br.readLine();
			
			// 받은 String을 char 배열로 만들기
			char[] tmpCh = new char[N];
			for (int n = 0; n < N; n++) {
				tmpCh[n] = str.charAt(n);
//				System.out.print(tmpCh[n] + " ");
			}
//			System.out.println();
			
			// len만큼 옮겨주기
			// 그 이상 옮겨주어도 len만큼 끊어서 같은 수가 나오기 때문에 의미가 없다.
			for (int k = 0; k < len; k++) {
				// char 배열 끝부분을 저장 후
				char tmpChar = tmpCh[N - 1];
				
				// 한칸씩 오른쪽으로 옮겨주고
				for (int idx = N - 1; idx >= 1; idx--) {
					tmpCh[idx] = tmpCh[idx - 1];
				}
				
				// char 배열 처음부분에 저장한 값 넣어주기
				tmpCh[0] = tmpChar;
				
//				System.out.println(Arrays.toString(tmpCh));
				
				// 옮긴 char 배열을 활용
				// 16진수 > 10진수로 바꾸고 set에 넣어주기
				for (int i = 0; i < N; i += len) {
					int num = 0;
					for (int j = i; j < i + len; j++) {
						char ch = tmpCh[j];
						int tmp = 0;
						
						if ('0' <= ch && ch <= '9') 
							tmp = ch - '0';
						else
							tmp = ch - 55;
						
//						System.out.println("tmp:" + tmp);
						
						// 16진수의 몇번째 자리수인지
						int cond = (len - 1) - (j % len);
						
						// 자리수만큼 16을 곱해주고 변환한 값을 곱해서 더해준다.
						num += Math.pow(16, cond) * tmp;
					}
//					System.out.println(num);
					// 변환된 10진수를 set에 넣어준다.
					numSet.add(num);
//					System.out.println(numSet);
				}
			}
			
			// set에 들어있는 값들을 배열로 전환하고 오름차순으로 정렬하고 답 출력
			Integer[] numArr = new Integer[numSet.size()];
			numArr = numSet.toArray(new Integer[0]);
			
			Arrays.sort(numArr);
//			System.out.println(Arrays.toString(numArr));
			
			int ans = numArr[(numArr.length - 1) - (K - 1)];
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
