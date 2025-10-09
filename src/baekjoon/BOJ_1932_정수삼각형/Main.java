package baekjoon.BOJ_1932_정수삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 삼각형의 크기 n
        int n = Integer.parseInt(br.readLine());

        // 입력받을 배열 및 계산 값을 저장 시킬 배열
        int[] inputArr = new int[n + 1];
        int[] calculateArr = new int[n + 1];

        // 삼각형이라 요소가 하나씩 추가되는 점을 이용
        for (int element = 1; element <= n; element++) {
            // 한 줄 입력받기
            st = new StringTokenizer(br.readLine());
            // 입력 받을 때 계산값이 저장된 배열에서 누가 더 큰지 확인 후 계산하여 저장
            for (int i = 1; i <= element; i++) {
                inputArr[i] = Integer.parseInt(st.nextToken());
                inputArr[i] += calculateArr[i - 1] > calculateArr[i] ? calculateArr[i - 1] : calculateArr[i];
            }

            // 입력 받을 배열을 저장된 배열로 저장(깊은 복사)
            calculateArr = Arrays.copyOf(inputArr, inputArr.length);
        }


        // 최종적으로 가장 큰 값 찾기
        int answer = -1;
        for (int i = 1; i <= n; i++) {
            answer = answer > calculateArr[i] ? answer : calculateArr[i];
        }

        // 답 출력
        System.out.println(answer);
    }
}
