package baekjoon.BOJ_1002_터렛;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            
            judgement(x1, y1, r1, x2, y2, r2);
        }

    }

    static void judgement(int x1, int y1, int r1, int x2, int y2, int r2) {
        // 일치하는 경우
        if (x1 == x2 && y1 == y2 && r1 == r2) {
            System.out.println("-1");
            return;
        }

        // 바깥에서 만나지 않는 경우
        if (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) > Math.pow(r1 + r2, 2)) {
            System.out.println("0");
            return;
        }

        // 내부에서 만나지 않는 경우
        if (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) < Math.pow(r1 - r2, 2)) {
            System.out.println("0");
            return;
        }

        // 외접하는 경우
        if (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) == Math.pow(r1 + r2, 2)) {
            System.out.println("1");
            return;
        }

        // 내접하는 경우
        if (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) == Math.pow(r1 - r2, 2)) {
            System.out.println("1");
            return;
        }
        
        System.out.println("2");
    }
}
