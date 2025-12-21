package baekjoon.BOJ_10828_스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] stack;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 주어지는 명령의 개수
        stack = new int[N];
        idx = 0;

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
                    System.out.println(pop());
                    break;
                case "size":
                    System.out.println(size());
                    break;
                case "empty":
                    System.out.println(isEmpty() ? 1 : 0);
                    break;
                case "top":
                    System.out.println(top());
                    break;
                }
            }
        }
            
    static void push(int num) {
        stack[idx++] = num;
    }
    
    static boolean isEmpty() {
        if (idx == 0) {
            return true;
        }

        return false;
    }

    static int size() {
        return idx;
    }

    static int pop() {
        if (isEmpty()) {
            return -1;
        }
        
        return stack[--idx];
    }

    static int top() {
        if (isEmpty()) {
            return -1;
        }

        return stack[idx - 1];
    }
}
