package baekjoon.BOJ_2775_부녀회장이_될테야;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[][] apartment = new int[15][15];

        for (int i = 1; i < 15; i++) {
            apartment[i][1] = 1;
        }
        for (int j = 1; j < 15; j++) {
            apartment[0][j] = j;
        }

        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                apartment[i][j] = apartment[i - 1][j] + apartment[i][j - 1];
            }
        }

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            System.out.println(apartment[k][n]);
        }
    }
}
