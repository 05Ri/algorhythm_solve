package baekjoon.BOJ_2164_카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        int last = 0;
        while (true) {
            last = queue.poll();
            if (queue.isEmpty()) break;
            last = queue.poll();
            queue.offer(last);
        }

        System.out.println(last);
    }
}
