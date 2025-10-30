package baekjoon.BOJ_11444_피보나치_수_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_공식참고 {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // n번째 피보나치 수
        long n = Long.parseLong(br.readLine());
        
        // 정답 출력
        System.out.println(matrixPow(n)[1][0]);
    }
    
    // 행렬 거듭제곱
    static long[][] matrixPow(long n) {
        long[][] result = new long[2][2];
        // 항등행렬 만들어주기
        for (int i = 0; i < 2; i++) {
            result[i][i] = 1;
        }

        // 거듭제곱할 행렬 생성
        long[][] A = {{1, 1}, {1, 0}};

        while (n > 0) {
            if ((n & 1) == 1) {
                result = matrixMultiply(result, A);
            }
            A = matrixMultiply(A, A);
            n >>= 1;
        }

        return result;
    }

    // 행렬 곱한 뒤 % 해주는 함수
    static long[][] matrixMultiply(long[][] maxtrix1, long[][] maxtrix2) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 2; j++) {
                    result[i][j] += maxtrix1[i][k] * maxtrix2[k][j];
                }
            }
            for (int j = 0; j < 2; j++) {
                result[i][j] %= MOD;
            }
        }

        return result;
    }
}
