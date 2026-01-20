package baekjoon.BOJ_1018_체스판_다시_칠하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] chessBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        chessBoard = new char[N][M];
        int cnt = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                chessBoard[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                cnt = Math.min(cnt, toMakePerfectChessBoard(i, j, 'W'));
                cnt = Math.min(cnt, toMakePerfectChessBoard(i, j, 'B'));
            }
        }

        System.out.println(cnt);
    }

    static int toMakePerfectChessBoard(int ci, int cj, char startColor) {
        int cnt = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int di = i + ci;
                int dj = j + cj;

                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        if (chessBoard[di][dj] != startColor) {
                            cnt++;
                        }
                    }
                    else {
                        if (chessBoard[di][dj] == startColor) {
                            cnt++;
                        }
                    }
                }
                else {
                    if (j % 2 == 0) {
                        if (chessBoard[di][dj] == startColor) {
                            cnt++;
                        }
                    }
                    else {
                        if (chessBoard[di][dj] != startColor) {
                            cnt++;
                        }
                    }
                }
            }
        }

        return cnt;
    }
}
