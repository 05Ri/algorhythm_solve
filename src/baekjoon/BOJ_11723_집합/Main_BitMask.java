package baekjoon.BOJ_11723_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BitMask {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());

        int set = 0;

        while (M --> 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x = 0;
            if (st.hasMoreTokens()) {
                x = Integer.parseInt(st.nextToken());
            }

            if (op.equals("add")) {
                set |= (1 << x);
            }
            else if (op.equals("remove")) {
                set &= ~(1 << x);
            }
            else if (op.equals("check")) {
                sb.append((set & (1 << x)) != 0 ? 1 : 0).append('\n');
            }
            else if (op.equals("toggle")) {
                set ^= (1 << x);
            }
            else if (op.equals("all")) {
                set = (1 << 21) - 2;
            }
            else if (op.equals("empty")) {
                set = 0;
            }
        }

        System.out.println(sb.toString());
    }
}
