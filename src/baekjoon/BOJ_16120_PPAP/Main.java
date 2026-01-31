package baekjoon.BOJ_16120_PPAP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> stack = new ArrayDeque<>();

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            stack.addLast(str.charAt(i));

            if (stack.size() >= 4) {
                char p4 = stack.pollLast();
                char p3 = stack.pollLast();
                char p2 = stack.pollLast();
                char p1 = stack.pollLast();

                if (p1 == 'P' && p2 == 'P' && p3 == 'A' && p4 == 'P') {
                    stack.addLast('P');
                } else {
                    stack.addLast(p1);
                    stack.addLast(p2);
                    stack.addLast(p3);
                    stack.addLast(p4);
                }
            }
        }

        if (stack.size() == 1 && stack.peekLast() == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
