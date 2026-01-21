package baekjoon.BOJ_1966_프린터_큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            Deque<int[]> dq = new ArrayDeque<>();
            int[] importanceCnt = new int[10];

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                dq.offer(new int[] {n, i == M ? 1 : 0});
                importanceCnt[n]++;
            }

            int cnt = 0;
            boolean isFind = false;

            while (true) {
                int importance = 0;
                for (int i = 9; i > 0; i--) {
                    if (importanceCnt[i] > 0) {
                        importance = i;
                        break;
                    }
                }

                while (importanceCnt[importance] > 0) {
                    int[] arr = dq.poll();
                    int n = arr[0];
                    int findNum = arr[1];

                    if (n == importance) {
                        importanceCnt[importance]--;
                        cnt++;

                        if (findNum == 1) {
                            isFind = true;
                            break;
                        }
                    } else {
                        dq.offer(arr);
                    }

                }

                if (isFind) {
                    break;
                }
            }

            System.out.println(cnt);
        }

    }
}
