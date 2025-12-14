package baekjoon.BOJ_1259_팰린드롬수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = "";
        while (true) {
            num = br.readLine();
            if (num.equals("0")) break;

            int L = 0;
            int R = num.length() - 1;

            boolean check = true;
            while (L < R) {
                if (num.charAt(L) != num.charAt(R)) {
                    check = false;
                    break;
                }

                L++;
                R--;
            }

            if (check) {
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }
        }
    }
}
