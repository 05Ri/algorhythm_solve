package baekjoon.BOJ_1904_막대기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int cnt = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                cnt++;
            }
            n = n >> 1;
        }

        System.out.println(cnt);
    }
}
