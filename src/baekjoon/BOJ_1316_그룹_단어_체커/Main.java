package baekjoon.BOJ_1316_그룹_단어_체커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] alpha;	// 알파뱃 방문체크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 단어 개수
		int cnt = 0;	// 카운팅

		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			alpha = new boolean[26];

			if(checking(str.toCharArray())) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	static boolean checking(char[] charArray) {
		for (int i = 1; i < charArray.length; i++) {
			// 현재와 전이 같다면 다음 순서로 건너뛰기
			if (charArray[i - 1] == charArray[i]) continue;
			
			// 알파벳이 쓰였는지 확인 후 쓰였다면 false 리턴
			if (alpha[charArray[i] - 'a']) return false;

			// 전에 쓰인 알파벳을 썼다고 체크해주기
			alpha[charArray[i - 1] - 'a'] = true;
		}
		
		return true;
	}
}
