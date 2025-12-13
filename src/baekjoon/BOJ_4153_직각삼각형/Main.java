package baekjoon.BOJ_4153_직각삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long[] triangle = new long[3];
            for (int i = 0; i < 3; i++) {
                long side = Long.parseLong(st.nextToken());
                triangle[i] = side * side;
            }

            if (triangle[0] == 0 && triangle[1] == 0 && triangle[2] == 0) break;

            Arrays.sort(triangle);

            if (triangle[0] + triangle[1] == triangle[2]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}
