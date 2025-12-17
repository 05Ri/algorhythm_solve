package baekjoon.BOJ_30802_웰컴_키트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] size = new int[6];

        int N = Integer.parseInt(br.readLine());    // 참가자 수
        
        // 티셔츠 사이즈 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }
        
        
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());    // 티쳐스 묶음 수
        int P = Integer.parseInt(st.nextToken());    // 펜 묶음 수

        int tCnt = 0;   // 총 티셔츠 묶음 개수
        for (int cnt : size) {
            tCnt += (cnt + T - 1) / T;  // 묶음 올림
        }

        System.out.println(tCnt);
        System.out.printf("%d %d", N / P, N % P);
    }
}
