package baekjoon.BOJ_11720_숫자의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Stream {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int ans = br.readLine().chars()
              .map(ch -> ch - '0')
              .sum();

        System.out.println(ans);
    }
}
