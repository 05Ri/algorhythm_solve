package baekjoon.BOJ_2741_N찍기;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int target = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= target; i++) {
            System.out.println(i);
        }
    }
}
