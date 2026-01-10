package baekjoon.BOJ_18110_solved_ac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N == 0) {
            System.out.println(0);
            return;
        }

        int cut = Math.round((float) (N * 0.15));
        int[] scores = new int[N];
        int[] avgScores = new int[N - 2 * cut];

        for (int i = 0; i < N; i++) {
            int score = Integer.parseInt(br.readLine());
            scores[i] = score;
        }

        Arrays.sort(scores);

        System.arraycopy(scores, cut, avgScores, 0, avgScores.length);

        float sum = 0;
        for (int score : avgScores) {
            sum += score;
        }

        System.out.println(Math.round(sum / avgScores.length));
    }
}