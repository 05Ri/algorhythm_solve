package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339_단어_수학 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 단어 개수
		int N = Integer.parseInt(br.readLine());
		
		// 문자의 최대 길이 인덱스를 저장할 변수
		int maxLen = 0;
		
		// 영어 대문자 개수는 26 및 수의 최대 길이는 8
		int[][] savedArr = new int[26][8];
		
		// 문자열을 받으면 해제하여 확인 및 배열에 담는다.
		for (int $ = 0; $ < N; $++) {
			String str = br.readLine();
			
			// 받은 문자열 길이 최대 인덱스 저장
			int strLen = str.length() - 1;
			
			// 받은 문자열의 인덱스 길이가 최대 인덱스 길이보다 크면 저장
			if (strLen > maxLen)
				maxLen = strLen;
			
			// 문자열 길이만큼 역으로 해제 작업하여 배열에 저장
			for (int i = strLen; i >= 0 ; i--) {
				// 문자를 숫자로 치환
				int chI = str.charAt(i) - 'A';
				// 자리수에 맞게 넣기 위해 다시 뒤집어주었다.
				savedArr[chI][strLen - i]++;
			}
		}
		
//		for (int i = 0; i < 26; i++)
//			System.out.println(Arrays.toString(savedArr[i]));
//		System.out.println(maxLen);
//		System.out.println();
		
		// 각 알파벳에서 1을 대입하고 계산한 값을 도출하여 배열에 저장한다.
		int[] numArr = new int[26];
		for (int i = 0; i < 26; i++) {
			int num = 0;
			for (int j = maxLen; j >= 0; j--) {
				num += Math.pow(10, j) * savedArr[i][j];
			}
			numArr[i] = num;
		}
		
		// 정렬 후 역으로 9 ~ 0 곱해주고 더해준 값이 답이다.
		Arrays.sort(numArr);
		
//		System.out.println(Arrays.toString(numArr));
		
		int ans = 0;
		int mul = 9;
		for (int i = 25; i >= 0; i--) {
			// 0을 만나면 출현한 적 없는 수니까 탈출
			if (numArr[i] == 0)
				break;
			
			// 곱해지는 수가 0 미만이면 탈출
			if (mul < 0)
				break;
			
			ans += numArr[i] * mul;
			mul--;
		}
		
		System.out.println(ans);
	}
}
