package baekjoon.BOJ_2798_블랙잭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] cards;
    static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 카드의 개수
        M = Integer.parseInt(st.nextToken());   // 기준 M

        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        maxSum = 0;

        doSum(0, 0, 0);

        System.out.println(maxSum);
    }

    static void doSum(int n, int idx, int sum) {
        if (n == 3) {
            if (sum <= M && maxSum < sum) {
                maxSum = sum;
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            doSum(n + 1, i + 1, sum + cards[i]);
        }
    }
}
