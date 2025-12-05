package baekjoon.BOJ_5648_역원정렬;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        long[] numbers = new long[N];

        for (int i = 0; i < N; i++) {
            long number = sc.nextLong();
            numbers[i] = reverseNum(number);
        }

        Arrays.sort(numbers);

        for (long num : numbers) {
            System.out.println(num);
        }
    }

    static long reverseNum(long num) {
        Queue<Integer> q = new LinkedList<>();
        long result = 0;

        while (num > 0) {
            q.add((int) (num % 10));
            num /= 10;
        }

        while (!q.isEmpty()) {
            result *= 10;
            result += q.poll();
        }

        return result;
    }

}
