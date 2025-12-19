package baekjoon.BOJ_9012_괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            stack.clear();
            boolean isRight = true;
            String str = br.readLine();
    
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
    
                if (c == '(') {
                    stack.push(c);
                    continue;
                }
    
                if (c == ')' && stack.isEmpty()) {
                    isRight = false;
                    break;
                }

                stack.pop();
            }

            if (!stack.isEmpty() || !isRight) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
