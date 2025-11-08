package baekjoon.BOJ_2999_비밀_이메일;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String msg = br.readLine(); // 받은 메세지
        int N = msg.length();   // 메세지 글자 수

        int[] RC = rc(N);   // RC 구하기
        int R = RC[0];
        int C = RC[1];

        char[][] array = new char[R][C];

        int idx = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                array[i][j] = msg.charAt(idx++);
            }
        }

        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R; i++) {
                sb.append(array[i][j]);
            }
        }

        System.out.println(sb.toString());
    }

    static int[] rc(int n) {
        for (int i = (int) Math.sqrt(n); i > 0; i--) {
            if (n % i == 0) {
                return new int[]{n / i, i};
            }
        }
        return null;
    }
}