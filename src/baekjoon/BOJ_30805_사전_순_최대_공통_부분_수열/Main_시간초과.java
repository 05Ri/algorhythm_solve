package baekjoon.BOJ_30805_사전_순_최대_공통_부분_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_시간초과 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < N; k++) {
            A[k] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < M; k++) {
            B[k] = Integer.parseInt(st.nextToken());
        }

        int number = 100;
        int len = 0;
        int idxA = 0;
        int idxB = 0;
        while (idxA < N && idxB < M) {
            for (int k = idxA; k < N; k++) {
                if (A[k] == number) {
                    for (int l = idxB; l < M; l++) {
                        if (B[l] == number) {
                            idxA = k + 1;
                            idxB = l + 1;
                            len++;
                            sb.append(number).append(" ");
                            number = 100;
                            break;
                        }
                    }
                }
            }
            number--;
        }

        System.out.println(len);
        if (len != 0) {
            System.out.println(sb.toString());
        }
    }
}
