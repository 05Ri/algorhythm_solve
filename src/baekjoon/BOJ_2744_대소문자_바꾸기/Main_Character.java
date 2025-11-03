package baekjoon.BOJ_2744_대소문자_바꾸기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Character {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        for (char ch : str.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                sb.append(Character.toUpperCase(ch));
            } else if (Character.isUpperCase(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }

        System.out.println(sb.toString());
    }
}
