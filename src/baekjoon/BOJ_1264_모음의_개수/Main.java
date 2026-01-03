package baekjoon.BOJ_1264_모음의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

        while (true) {
            int cnt = 0;
            String str = br.readLine();

            if (str.equals("#")) break;

            for (char c : str.toCharArray()) {
                for (char v : vowels) {
                    if (c == v) {
                        cnt += 1;
                        break;
                    }
                }
            }

            sb.append(cnt).append('\n');
        }

        System.out.println(sb.toString());
    }
}
