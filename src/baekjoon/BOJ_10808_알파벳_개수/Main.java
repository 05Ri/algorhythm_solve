package baekjoon.BOJ_10808_알파벳_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 소문자의 개수를 세서 담아줄 배열
        int[] cntAlpha = new int[26];
        
        String str = br.readLine();

        for (char c : str.toCharArray()) {
            int n = c - 'a';
            cntAlpha[n]++;
        }

        for (int n : cntAlpha) {
            sb.append(n).append(" ");
        }
        
        // 끝부분 띄어쓰기 없애기
        sb.delete(sb.length() - 1, sb.length());

        System.out.println(sb.toString());
    }
}