package baekjoon.BOJ_11720_숫자의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int ans = 0;
        for (char ch : br.readLine().toCharArray()) {
            if (Character.isDigit(ch)) {
                ans += ch - '0';
            }
        }

        System.out.println(ans);
    }
}
