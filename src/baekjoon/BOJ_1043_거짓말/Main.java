package baekjoon.BOJ_1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 사람의 수
        int M = Integer.parseInt(st.nextToken());   // 파티의 수

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 둘째 줄 처리
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());   // 진실을 아는 사람의 수
        
        int knowTruthReader = 0;
        // 진실을 아는 사람이 있다면 첫번째 사람이 리더
        if (T > 0) {
            knowTruthReader = Integer.parseInt(st.nextToken());
        }
        // 이후 사람들 묶기
        for (int i = 1; i < T; i++) {
            int knowTruthPerson = Integer.parseInt(st.nextToken());
            union(knowTruthReader, knowTruthPerson);
        }
    
        // 셋째 줄 이후 처리(파티 저장)
        int[][] parties = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());   // 파티 사람들 수
            
            int[] party = new int[P];
            int prev = Integer.parseInt(st.nextToken());
            party[0] = prev;
            for (int j = 1; j < P; j++) {
                int person = Integer.parseInt(st.nextToken());
                party[j] = person;
                union(prev, person);
                prev = person;
            }

            parties[i] = party;
        }

        // 추가 연합을 처리
        if (T > 0) {
            knowTruthReader = find(knowTruthReader);
        }

        int answer = 0;

        for (int[] party : parties) {
            boolean canLie = true;

            for (int p : party) {
                if (find(p) == knowTruthReader) {
                    canLie = false;
                    break;
                }
            }

            if (canLie) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
