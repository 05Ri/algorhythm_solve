package baekjoon.BOJ_2744_대소문자_바꾸기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        for (char ch : str.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                sb.append((char) (ch - 'a' + 'A'));
            } else if (ch >= 'A' && ch <= 'Z') {
                sb.append((char) (ch - 'A' + 'a'));
            }
        }

        System.out.println(sb.toString());
    }
}
