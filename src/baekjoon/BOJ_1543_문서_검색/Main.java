package baekjoon.BOJ_1543_문서_검색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String document = br.readLine();
        String sentence = br.readLine();

        int cnt = 0;
        int i = 0;

        while (i <= document.length() - sentence.length()) {
            boolean matched = true;

            for (int j = 0; j < sentence.length(); j++) {
                if (document.charAt(i + j) != sentence.charAt(j)) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                cnt++;
                i += sentence.length();
            }
            else {
                i++;
            }
        }

        System.out.println(cnt);
    }
}
