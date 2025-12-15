package baekjoon.BOJ_2577_숫자의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] numCnt = new int[10];

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        int result = A * B * C;

        while (result > 0) {
            numCnt[result % 10]++;
            result /= 10;
        }

        for (int cnt : numCnt) {
            System.out.println(cnt);
        }
    }
}
