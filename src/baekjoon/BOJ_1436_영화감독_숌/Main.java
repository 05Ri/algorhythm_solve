package baekjoon.BOJ_1436_영화감독_숌;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int number = 666;
        int cnt = 0;

        while (true) {
            if (String.valueOf(number).contains("666")) {
                cnt++;
                if (cnt == N) {
                    System.out.println(number);
                    break;
                }
            }
            number++;
        }
    }
}
