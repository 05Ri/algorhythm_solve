package baekjoon.BOJ_2920_음계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] sounds = new int[8];
        boolean asc = true;
        boolean desc = true;

        for (int i = 0; i < 8; i++) {
            sounds[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < 8; i++) {
            if (sounds[i - 1] < sounds[i]) {
                desc = false;
            }

            if (sounds[i - 1] > sounds[i]) {
                asc = false;
            }
        }

        if (asc) {
            System.out.println("ascending");
        }
        else if (desc) {
            System.out.println("descending");
        }
        else {
            System.out.println("mixed");
        }
    }
}
