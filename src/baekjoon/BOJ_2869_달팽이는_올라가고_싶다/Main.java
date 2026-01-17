package baekjoon.BOJ_2869_달팽이는_올라가고_싶다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        int day = 1;
        int move = A - B;
        int point = V - A;

        if (point > 0) {
            day += Math.ceil((double) point / move);
        }

        System.out.println(day);
    }
}
