package baekjoon.BOJ_9251_LCS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 두 문자 입력받기
        String firstString = br.readLine();
        String secondString = br.readLine();

        // 각 문자열 길이 저장
        int firstLength = firstString.length();
        int secondLength = secondString.length();

        // 값을 저장해놓을 배열 생성
        int[][] stagingArr = new int[secondLength + 1][firstLength + 1];

        for (int i = 1; i <= secondLength; i++) {
            for (int j = 1; j <= firstLength; j++) {
                // i번째 글자와 j번째 글자가 같으면 대각선에 있는 LCS값을 확인 후 하나 추가
                if (secondString.charAt(i - 1) == firstString.charAt(j - 1)) {
                    stagingArr[i][j] += stagingArr[i - 1][j - 1] + 1;
                }
                // 같지 않다면 왼쪽과 위쪽 값 중 하나를 가져오기
                else {
                    stagingArr[i][j] = stagingArr[i - 1][j] > stagingArr[i][j - 1] ? stagingArr[i - 1][j] : stagingArr[i][j - 1];
                }
            }
        }

        // 답 출력
        System.out.println(stagingArr[secondLength][firstLength]);
    }
}
