package baekjoon.BOJ_1855_암호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int line = str.length() / K;

        
        char[][] charArray = makeCharArray(line, K, str);
        str = makeString(line, K, charArray);
        
        charArray = makeCharArray(line, K, str);

        for (int j = 0; j < K; j++) {
            for (int i = 0; i < line; i++) {
                sb.append(charArray[i][j]);
            }
        }

        System.out.println(sb.toString());

    }

    private static String makeString(int line, int K, char[][] charArray) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line; i++) {
            if ((i & 1) == 0) {
                for (int j = 0; j < K; j++) {
                    sb.append(charArray[i][j]);
                }
            }
            else {
                for (int j = K - 1; j >= 0; j--) {
                    sb.append(charArray[i][j]);
                }
            }
        }

        return sb.toString();
    }

    static char[][] makeCharArray(int line, int K, String str) {
        char[][] charArray = new char[line][K];

        int k = 0;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < K; j++) {
                charArray[i][j] = str.charAt(k++);
            }
        }

        return charArray;
    }
}
