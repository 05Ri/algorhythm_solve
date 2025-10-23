package baekjoon.BOJ_1978_소수_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 수의 개수
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int count = 0;
        // 주어진 수 입력받아서 판단하기
        for (int i = 0 ; i < N; i++) {
            count += isPrimeNum(Integer.parseInt(st.nextToken()));
        }

        System.out.println(count);
    }

    private static int isPrimeNum(int num) {
        if (num == 1) return 0;

        for (int n = 2; n <= Math.sqrt(num); n++) {
            if (num % n == 0) {
                return 0;
            }
        }
        
        return 1;
    }
}
