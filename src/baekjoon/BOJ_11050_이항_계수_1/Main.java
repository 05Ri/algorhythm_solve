package baekjoon.BOJ_11050_이항_계수_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] factoArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        factoArr = new int[N + 1];
        factoArr[0] = 1;
        factorial(N);

        int result = factoArr[N] / (factoArr[K] * factoArr[N - K]);

        System.out.println(result);
    }

    static void factorial(int n) {
        for (int i = 1; i <= n; i++) {
            factoArr[i] = factoArr[i - 1] * i;
        }
    }
}
