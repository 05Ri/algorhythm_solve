package baekjoon.BOJ_10845_큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] queue;
    static int front;
    static int rear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        queue = new int[N];
        front = 0;
        rear = 0;

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            switch (operation) {
                case "push":
                    push(num);
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(front()).append("\n");
                    break;
                case "back":
                    sb.append(back()).append("\n");
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    static void push(int num) {
        // if (rear < queue.length) {
            queue[rear++] = num;
        // }
    }

    static int pop() {
        return isEmpty() ? -1 : queue[front++];
    }

    static int size() {
        return rear - front;
    }

    static boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    static int front() {
        return isEmpty() ? -1 : queue[front];
    }

    static int back() {
        return isEmpty() ? -1 : queue[rear - 1];
    }
}
