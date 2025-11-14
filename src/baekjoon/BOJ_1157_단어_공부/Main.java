package baekjoon.BOJ_1157_단어_공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int[] cntAlphabet = new int[26];

        for (char ch : str.toCharArray()) {
            cntAlphabet[Character.toUpperCase(ch) - 'A'] += 1;
        }

        int maxCnt = 0; // 카운트 개수 최대인거 저장
        int maxCntIdx = -1; // 카운트 개수 최대인 인덱스 저장
        boolean isEqualMaxCnt = false;  // 카운트 개수 최대인거와 같은게 있는지 확인 용도
        for (int i = 0; i < cntAlphabet.length; i++) {
            if (maxCnt < cntAlphabet[i]) {
                maxCnt = cntAlphabet[i];
                maxCntIdx = i;
                isEqualMaxCnt = false;
            }
            else if (maxCnt == cntAlphabet[i]) {
                isEqualMaxCnt = true;
            }
        }

        if (isEqualMaxCnt) {
            System.out.println("?");
        }
        else {
            System.out.println((char)(maxCntIdx + 'A'));
        }
    }
}
