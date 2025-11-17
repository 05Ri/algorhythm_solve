package baekjoon.BOJ_4659_비밀번호_발음하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final Character[] vowels = {'a', 'e', 'i', 'o', 'u'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String password = br.readLine().toLowerCase();    // 검사할 비밀번호 입력받기
            char[] passCh = password.toCharArray();
            boolean isAcceptable = true;    // 적합한지

            // end가 오면 끝내기
            if (password.equals("end")) break;

            // 1. 모음 중 하나를 반드시 포함
            isAcceptable = checkContainsVowel(passCh);
            
            // 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안된다.
            if (isAcceptable) {
                isAcceptable = checkTripleConsonantVowel(passCh);
            }
            
            // 3. 같은 글자가 연속으로 두 번 오면 안되지만, ee와 oo는 허용
            if (isAcceptable) {
                isAcceptable = checkDoubleChar(passCh);
            }
            
            if (isAcceptable) {
                System.out.printf("<%s> is acceptable.\n", password);
            } else {
                System.out.printf("<%s> is not acceptable.\n", password);
            }
        }
    }

    static boolean checkContainsVowel(char[] passCh) {
        for (char c : passCh) {
            if(isVowel(c)) return true;
        }

        return false;
    }

    static boolean isVowel(char ch) {
        for (char v : vowels) {
            if (ch == v) return true;
        }

        return false;
    }

    static boolean checkTripleConsonantVowel(char[] passCh) {
        int cnt = 1;
        boolean prev = isVowel(passCh[0]);

        for (int i = 1; i < passCh.length; i++) {
            boolean curr = isVowel(passCh[i]);

            // 전과 비교하여 자음과 모음이
            if (curr == prev) {
                // 같으면 횟수 추가
                cnt += 1;
            } else {
                // 다르면 횟수 초기화 및 상태 변경
                cnt = 1;
                prev = curr;
            }

            if (cnt >= 3) return false;
        }

        return true;
    }

    static boolean checkDoubleChar(char[] passCh) {
        for (int i = 1; i < passCh.length; i++) {
            char prev = passCh[i - 1];
            char curr = passCh[i];

            if (prev == curr) {
                if (prev == 'e' || prev == 'o') continue;
                return false;
            }
        }

        return true;
    }
}
