package baekjoon.BOJ_11723_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        set = new boolean[21];

        while (M --> 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x = 0;
            if (st.hasMoreTokens()) {
                x = Integer.parseInt(st.nextToken());
            }

            switch (op) {
                case "add":
                    add(x);
                    break;
                case "remove":
                    remove(x);
                    break;
                case "check":
                    sb.append(check(x) ? 1 : 0).append('\n');
                    break;
                case "toggle":
                    toggle(x);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    static void add(int x) {
        set[x] = true;
    }

    static void remove(int x) {
        set[x] = false;
    }

    static boolean check(int x) {
        return set[x];
    }

    static void toggle(int x) {
        set[x] = !set[x];
    }

    static void all() {
        for (int i = 1; i < set.length; i++) {
            set[i] = true;
        }
    }

    static void empty() {
        set = new boolean[21];
    }
}
