package baekjoon.BOJ_14626_ISBN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String damagedISBN = br.readLine();
        
        int check = damagedISBN.charAt(12) - '0';
        int sum = 0;
        int damaged = 0;
        boolean isMul = false;

        for (int i = 0; i < 12; i++) {
            char c = damagedISBN.charAt(i);
            if (c == '*') {
                if (i % 2 == 1) {
                    isMul = true;
                }
                continue;
            }

            int mul = i % 2 == 0 ? 1 : 3;
            sum += (c - '0') * mul;
        }

        sum += check;

        for (int i = 0; i < 10; i++) {
            int k = i;
            if (isMul) {
                k *= 3;
            }
            sum += k;

            if (sum % 10 == 0) {
                damaged = i;
                break;
            }

            sum -= k;
        }

        System.out.println(damaged);
    }
}
