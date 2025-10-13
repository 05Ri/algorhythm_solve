package baekjoon.BOJ_1152_단어의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().trim();

        String[] strArr = str.split(" ");
        
        if (str.isEmpty()) {
            System.out.println(0);
            return;
        }

        System.out.println(strArr.length);
    }
}
