package baekjoon.BOJ_2839_설탕_배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int fiveGramCnt = N / 5;
        int remain = N % 5;

        if (remain == 0) {
            System.out.println(fiveGramCnt);
        }
        else if (remain == 1 && fiveGramCnt >= 1) {
            System.out.println(fiveGramCnt + 1);
        }
        else if (remain == 2 && fiveGramCnt >= 2) {
            System.out.println(fiveGramCnt + 2);
        }
        else if (remain == 3) {
            System.out.println(fiveGramCnt + 1);
        }
        else if (remain == 4 && fiveGramCnt >= 1) {
            System.out.println(fiveGramCnt + 2);
        }
        else {
            System.out.println(-1);
        }
    }
}
