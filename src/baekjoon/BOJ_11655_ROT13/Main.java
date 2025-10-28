package baekjoon.BOJ_11655_ROT13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strArr = br.readLine().split(" ");

        // ROT13
        String buildString = "";
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            char[] chars = str.toCharArray();
            buildString = "";

            for (int j = 0; j < chars.length; j++) {
                int n = chars[j];
                
                // 소문자일 경우
                if (n >= 97) {
                    n = n - 'a' + 13;
                    n %= 26;
                    n += 'a';
                }
                // 대문자일 경우
                else if (n >= 65) {
                    n = n - 'A' + 13;
                    n %= 26;
                    n += 'A';
                }

                buildString += (char) n;
            }

            strArr[i] = buildString;
        }

        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
