package baekjoon.BOJ_1271_엄청난_부자2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

       BigInteger A = new BigInteger(st.nextToken());
       BigInteger B = new BigInteger(st.nextToken());

       System.out.println(A.divide(B));
       System.out.println(A.remainder(B));
    }
}
