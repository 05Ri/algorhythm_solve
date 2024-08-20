package baekjoon;

import java.util.Scanner;

public class BOJ_1316_그룹_단어_체커 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int wordNum = sc.nextInt(); // 단어 개수 입력 받기
		String word = new String();
		// 알파벳 소문자 길이만큼 크기 할당, 알파벳 중복 검사 배열
		char[] alphaCheck = new char[26];
		int cnt = wordNum; // 일단 전부 그룹단어라고 가정해준다.

		for (int w = 0; w < wordNum; w++) {
			word = sc.next();

			// 검사 배열 초기화
			for (int i = 0; i < alphaCheck.length; i++) {
				alphaCheck[i] = '0';
			}

			char[] wordChar = word.toCharArray();

			int chkCnt = 0; // 검사 배열의 인덱스
			// 현 글자와 전 글자가 같지 않으면 검사 배열에 전 글자를 추가해준다.
			for (int i = 1; i < wordChar.length; i++) {
				if (wordChar[i] != wordChar[i - 1]) {
					alphaCheck[chkCnt++] = wordChar[i - 1];
				}
			}

			if (wordChar[wordChar.length - 1] != wordChar[wordChar.length - 2])
				alphaCheck[chkCnt] = wordChar[wordChar.length - 1]; // 마지막 글자도 비교하여 추가해준다.

			for (int i = 0; i < alphaCheck.length; i++) {
				System.out.print(alphaCheck[i] + " ");
			}
			System.out.println();

			for (int i = 1; i < alphaCheck.length && alphaCheck[i] == '0'; i++) {
				if (alphaCheck[i] == alphaCheck[i - 1]) {
					cnt--;
					break;
				}
			}
		}

		System.out.println(cnt);
	}
}
