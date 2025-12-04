package baekjoon.BOJ_1431_시리얼_번호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 기타의 개수

        String[] serialNumber = new String[N];
        
        // 시리얼번호 입력받기
        for (int n = 0; n < N; n++) {
            serialNumber[n] = br.readLine();
        }

        Arrays.sort(serialNumber, (a, b) -> {
            if (a.length() == b.length()) {
                int sa = getNumSum(a);
                int sb = getNumSum(b);
                if (sa == sb) {
                    return a.compareTo(b);
                }
                return Integer.compare(sa, sb);
            }
            return Integer.compare(a.length(), b.length());
        });

        for (String serial : serialNumber) {
            System.out.println(serial);
        }
    }

    static int getNumSum(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                sum += ch - '0';
            }
        }

        return sum;
    }
}
