package baekjoon.BOJ_3460_이진수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        int n = 0;
        int cnt = 0;
        int length = 0;
        
        for (int tc = 0; tc < testCase; tc++) {
            n = Integer.parseInt(br.readLine());
            cnt = 0;

            while (n > 0) {
                if ((n & 1) == 1) {
                    sb.append(cnt).append(" ");
                }
                cnt++;
                n = n >> 1;
            }
            length = sb.length();
            sb.delete(length - 1, length);
            sb.append("\n");
        }
        length = sb.length();
        sb.delete(length - 1, length);

        System.out.println(sb.toString());
    }
}
