package baekjoon.BOJ_17609_회문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String s = br.readLine();
            System.out.println(check(s));
        }
    }

    /**
     * 회문, 유사회문, 그 외를 판별해주는 함수
     * @param s 검사할 문자열
     * @return 0 or 1 or 2
     */
    static int check(String s) {
        int L = 0;
        int R = s.length() - 1;

        while (L < R) {
            if (s.charAt(L) == s.charAt(R)) {
                L++;
                R--;
            } else {
                // 문자열을 하나 건너뛰고 회문이라면 유사 회문
                if (isPalindrome(s, L + 1, R) || isPalindrome(s, L, R - 1)) {
                    return 1;
                }
                // 아니라면 그 외
                return 2;
            }
        }
        // 모두 통과하면 회문
        return 0;
    }

    /**
     * 추가 회문 검사
     * @param s 문자열
     * @param L 왼쪽 시작 지점
     * @param R 오른쪽 시작 지점
     * @return boolean
     */
    static boolean isPalindrome(String s, int L, int R) {
        while (L < R) {
            if (s.charAt(L) != s.charAt(R)) {
                return false;
            }

            L++;
            R--;
        }

        return true;
    }
}
