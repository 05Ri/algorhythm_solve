package baekjoon.BOJ_1254_팰린드롬_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();   // 문자열 입력받기
        int len = S.length();   // 문자열의 길이
        
        int n = palindromeNum(S, len);

        System.out.println(2 * len - n);
    }
    
    /**
     * 팰린드롬이 시작되는 위치부터의 개수
     * @param s 문자열
     * @return 팰린드롬이 되는 문자의 개수
     */
    static int palindromeNum(String s, int len) {
        // 문자열의 처음부터 시작
        for (int i = 0; i < len; i++) {
            int start = i;
            int end = len - 1;

            boolean check = true;

            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    check = false;
                    break;
                }

                start++;
                end--;
            }

            if (check) {
                return len - i;
            }
        }

        return 1;
    }
}
