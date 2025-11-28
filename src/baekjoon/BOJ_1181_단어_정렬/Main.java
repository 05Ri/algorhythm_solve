package baekjoon.BOJ_1181_단어_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] sentence = new String[N];
        for (int i = 0; i < N; i++) {
            sentence[i] = br.readLine();
        }

        // Comparator 이용하기
        Arrays.sort(sentence, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });

        // 출력
        System.out.println(sentence[0]);
        for (int i = 1; i < N; i++) {
            if (!sentence[i - 1].equals(sentence[i])) {
                System.out.println(sentence[i]);
            }
        }
    }
 }
