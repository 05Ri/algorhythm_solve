package baekjoon.BOJ_10830_행렬_제곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_많은_참고 {
    // 나눌 수
    static final int MOD = 1000;
    // 행렬 크기
    static int N;
    // 제곱할 횟수
    static long B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        // 행렬 입력받기
        long[][] matrix = new long[N][N];
        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 행렬 거듭 제곱
        long[][] ansArr = maxtrixPow(matrix);

        // 정답 조합 및 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int change = (int) ansArr[i][j] % 1000;
                sb.append(change).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    /**
     * 행렬 거듭제곱 분할정복
     * @param matrix 원본 행렬
     * @return 정답 행렬
     */
    static long[][] maxtrixPow(long[][] matrix) {
        long[][] result = new long[N][N];
        // 항등 행렬 만들어주기
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        // 거듭제곱할 행렬 생성
        long[][] cur = new long[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(matrix[i], 0, cur[i], 0, N);
        }

        while (B > 0) {
            // 홀수 판별 후 홀수면 곱해주기
            if ((B & 1) == 1) {
                result = maxtrixMultiply(result, cur);
            }
            // 짝수면 제곱
            cur = maxtrixMultiply(cur, cur);
            B >>= 1;
        }
        
        return result;
    }

    /**
     * 행렬 곱하기
     * @param maxtrix1 행렬1
     * @param maxtrix2 행렬2
     * @return long[][] 결과
     */
    static long[][] maxtrixMultiply(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < N; j++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
            for (int j = 0; j < N; j++) {
                result[i][j] %= MOD;
            }
        }
        
        return result;
    }
}
