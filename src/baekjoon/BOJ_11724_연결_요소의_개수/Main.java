package baekjoon.BOJ_11724_연결_요소의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] graph;

    static int find(int x) {
        if (graph[x] == x) {
            return x;
        }

        return graph[x] = find(graph[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            graph[fb] = fa;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = i;
        }

        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }
}
