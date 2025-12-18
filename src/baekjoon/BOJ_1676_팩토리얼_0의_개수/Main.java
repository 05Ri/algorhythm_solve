package baekjoon.BOJ_1676_팩토리얼_0의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int twoCnt = 0;
        int fiveCnt = 0;

        for (int i = 2; i <= N; i++) {
            if (i % 2 == 0) {
                twoCnt += count(i, 2);
            }
            
            if (i % 5 == 0) {
                fiveCnt += count(i, 5);
            }
        }

        System.out.println(Math.min(twoCnt, fiveCnt));
    }

    static int count(int num, int div) {
        int cnt = 0;
        while (true) {
            if (num % div != 0) {
                return cnt;
            }

            num /= div;
            cnt++;
        }
    }
}
