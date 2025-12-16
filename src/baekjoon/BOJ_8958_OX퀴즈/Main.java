package baekjoon.BOJ_8958_OX퀴즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String result = br.readLine();
            
            int score = 0;
            int cnt = 0;

            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == 'O') {
                    cnt++;
                    score += cnt;
                }
                else {
                    cnt = 0;
                }
            }

            System.out.println(score);
        }
        
    }
}
