package baekjoon.BOJ_1920_수_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());    // A에 들어갈 정수 개수
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            sb.append(findNum(n) ? 1 : 0).append('\n');
        }

        System.out.println(sb.toString());
    }

    static boolean findNum(int n) {
        int l = 0;
        int r = N - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (A[mid] == n) {
                return true;
            }
            else if (A[mid] < n) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return false;
    }
}
