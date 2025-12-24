package baekjoon.BOJ_2231_분해합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());    // 생성자를 구해내야하는 수

        int generator = 0;

        for (int i = 1; i < N; i++) {
            int[] numArr = makeNumArr(i);

            int sum = i;
            for (int j : numArr) {
                sum += j;
            }

            if (sum == N) {
                generator = i;
                break;
            }
        }

        System.out.println(generator);
    }

    static int[] makeNumArr(int n) {
        String str = Integer.toString(n);
        int len = str.length();
        int[] numArr = new int[len];

        for (int i = 0; i < len; i++) {
            numArr[i] = str.charAt(i) - '0';
        }
        
        return numArr;
    }
}
