package baekjoon.BOJ_2606_바이러스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] environment;

    static int find(int x) {
        if (environment[x] == x) {
            return x;
        }

        return environment[x] = find(environment[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            environment[fb] = fa;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int computerCnt = Integer.parseInt(br.readLine());
        int connection = Integer.parseInt(br.readLine());

        environment = new int[computerCnt + 1];
        for (int i = 1; i <= computerCnt; i++) {
            environment[i] = i;
        }

        for (int i = 0; i < connection; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }
        
        int root = find(1);
        int cnt = 0;

        for (int i = 2; i <= computerCnt; i++) {
            if (find(i) == root) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
