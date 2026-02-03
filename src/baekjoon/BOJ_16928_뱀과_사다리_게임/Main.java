package baekjoon.BOJ_16928_뱀과_사다리_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = N + M;
        
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            mapping.put(key, value);
        }

        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        int throwsCnt = 0;

        dq.offer(1);
        visited[1] = true;

        while (!dq.isEmpty()) {
            int size = dq.size();
            throwsCnt++;

            for (int i = 0; i < size; i++) {
                int curr = dq.poll();

                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (mapping.containsKey(next)) {
                        next = mapping.get(next);
                    }

                    if (next == 100) {
                        System.out.println(throwsCnt);
                        return;
                    }

                    if (next > 100 || visited[next]) {
                        continue;
                    }

                    visited[next] = true;
                    dq.offer(next);
                }
            }
        }
    }
}
