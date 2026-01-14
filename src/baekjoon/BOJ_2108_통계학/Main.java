package baekjoon.BOJ_2108_통계학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N];
        double sum = 0;
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            sum += numbers[i];

            maxValue = Math.max(maxValue, numbers[i]);
            minValue = Math.min(minValue, numbers[i]);
        }

        double avg = sum / N;
        boolean isMinus = false;
        if (avg < 0) {
            isMinus = true;
            avg *= -1;
        }
        int mean = (int) Math.round(avg);
        sb.append(isMinus ? -mean : mean).append('\n');

        Arrays.sort(numbers);
        sb.append(numbers[N / 2]).append('\n');

        int cnt = 0;
        int check = numbers[0];
        int maxCnt = 0;
        List<Integer> maxCntValue = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (check == numbers[i]) {
                cnt += 1;
                continue;
            }
            
            if (cnt > maxCnt) {
                maxCntValue.clear();
                maxCnt = cnt;
                maxCntValue.add(check);
            } else if (cnt == maxCnt) {
                maxCntValue.add(check);
            }
            
            check = numbers[i];
            cnt = 1;
        }

        if (cnt > maxCnt) {
            maxCntValue.clear();
            maxCnt = cnt;
            maxCntValue.add(check);
        } else if (cnt == maxCnt) {
            maxCntValue.add(check);
        }

        sb.append(maxCntValue.get(maxCntValue.size() >= 2 ? 1 : 0)).append('\n');

        sb.append(N == 1 ? 0 : maxValue - minValue);

        System.out.println(sb.toString());
    }
}