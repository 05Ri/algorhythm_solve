package baekjoon.BOJ_2941_크로아티아_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final String[] CroatiaAlphabet = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        chars = br.readLine().toCharArray();

        int cnt = 0;
        for (int i = 0; i < chars.length; i++) {
            i += checkCroatia(i);
            cnt++;
        }

        System.out.println(cnt);
    }

    static int checkCroatia(int idx) {
        int csLen = 0;  // 크로아티아 알파벳의 길이
        boolean check;  // 문자열의 끝까지 갔는지 안갔는지 확인
        for (String CroatiaStr : CroatiaAlphabet) {
            csLen = CroatiaStr.length();
            check = false;
            for (int i = 0; i < csLen; i++) {
                if (idx + i >= chars.length || chars[idx + i] != CroatiaStr.charAt(i)) break;

                if (i == csLen - 1) {
                    check = true;
                }
            }

            if (check) return csLen - 1;
        }

        return 0;
    }
}
