package baekjoon.BOJ_1766_문제집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] problems;
    static int[] degree;   // 각 문제의 차수를 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());   // 문제의 수
        int M = Integer.parseInt(st.nextToken());   // 먼저 풀면 좋을 문제의 수
        
        // 문제들의 관계를 담을 배열 초기화
        problems = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            problems[i] = new ArrayList<Integer>();
        }

        // 차수를 담을 배열 초기화
        degree = new int[N + 1];

        // A → B인 그래프 그리기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            problems[A].add(B);
            degree[B]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 차수가 0인 노드들 먼저 넣기
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");

            for (int next : problems[curr]) {
                degree[next]--;
                if (degree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        
        System.out.println(sb.toString());
    }
}
