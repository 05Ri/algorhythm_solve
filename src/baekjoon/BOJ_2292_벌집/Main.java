package baekjoon.BOJ_2292_벌집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int roomCnt = 1;
        int sum = 1;
        while (N > sum) {
            sum += roomCnt++ * 6;
        }

        System.out.println(roomCnt);
    }
}