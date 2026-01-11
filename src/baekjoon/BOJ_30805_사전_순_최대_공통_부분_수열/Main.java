package baekjoon.BOJ_30805_사전_순_최대_공통_부분_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // A 수열 입력받기
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < N; k++) {
            A[k] = Integer.parseInt(st.nextToken());
        }
        
        // B 수열 입력받기
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < M; k++) {
            B[k] = Integer.parseInt(st.nextToken());
        }

        // 수열의 순서 기억하기
        List<Integer>[] posA = new ArrayList[101];
        List<Integer>[] posB = new ArrayList[101];

        for (int i = 1; i <= 100; i++) {
            posA[i] = new ArrayList<>();
            posB[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            posA[A[i]].add(i);
        }
        for (int i = 0; i < M; i++) {
            posB[B[i]].add(i);
        }

        int len = 0;
        int idxA = 0;
        int idxB = 0;
        while (true) {
            boolean found = false;

            for (int num = 100; num > 0; num--) {
                int na = findNext(posA[num], idxA);
                int nb = findNext(posB[num], idxB);

                if (na != -1 && nb != -1) {
                    sb.append(num).append(" ");
                    len++;
                    idxA = na + 1;
                    idxB = nb + 1;
                    found = true;
                    break;
                }
            }

            if (!found) break;
        }

        System.out.println(len);
        if (len != 0) {
            System.out.println(sb.toString());
        }
    }

    private static int findNext(List<Integer> list, int idx) {
        int l = 0;
        int r = list.size() - 1;
        int res = -1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= idx) {
                res = list.get(mid);
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return res;
    }
}
