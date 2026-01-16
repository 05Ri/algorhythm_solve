package baekjoon.BOJ_15829_Hashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int VERY_LONG_PRIME = 1234567891;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();
        long[] thirtyOneArr = thirtyOneMul(L);

        long sum = 0;
        for (int i = 0; i < L; i++) {
            int num = str.charAt(i) - 'a' + 1;
            sum += num * thirtyOneArr[i];
            sum %= VERY_LONG_PRIME;
        }

        System.out.println(sum);
    }

    static long[] thirtyOneMul(int L) {
        long[] array = new long[L];
        array[0] = 1;

        for (int i = 1; i < L; i++) {
            array[i] = array[i - 1] * 31 % VERY_LONG_PRIME;
        }

        return array;
    }
}
