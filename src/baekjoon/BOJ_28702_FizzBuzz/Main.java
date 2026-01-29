package baekjoon.BOJ_28702_FizzBuzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int result = 0;
        for (int i = 3; i > 0; i--) {
            String str = br.readLine();
            if (Character.isDigit(str.charAt(0))) {
                int n = Integer.parseInt(str);
                result = n + i;
            }
        }

        if (result % 3 == 0) {
            sb.append("Fizz");
        }
        if (result % 5 == 0) {
            sb.append("Buzz");
        }

        if (sb.length() == 0) {
            sb.append(result);
        }

        System.out.println(sb);
    }
}
