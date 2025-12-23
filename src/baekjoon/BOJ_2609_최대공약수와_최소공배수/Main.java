package baekjoon.BOJ_2609_최대공약수와_최소공배수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int minNum = Math.min(a, b);
        int maxNum = Math.max(a, b);

        int greatestCommonDivisor = 0;
        int leastCommonMultiple = 0;

        for (int i = minNum; i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                greatestCommonDivisor = i;
                break;
            }
        }

        for (int i = 1; i <= minNum; i++) {
            int multi = maxNum * i;
            if (multi % a == 0 && multi % b == 0) {
                leastCommonMultiple = multi;
                break;
            }
        }

        System.out.printf("%d %d", greatestCommonDivisor, leastCommonMultiple);
    }
}
