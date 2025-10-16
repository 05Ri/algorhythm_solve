package other.Programmers.줄_서는_방법;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_효율안좋음 {
    // 숫자를 담을 배열
    public static List<Integer> number;

    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        int idx = 0;

        number = new ArrayList<>();
        for (int num = 1; num <= n; num++) {
            number.add(num);
        }

        long sequenceNum = (k - 1);
        long nextNum;
        for (int i = 1; i <= n; i++) {
            // 팩토리얼 계산
            long factoResult = facto(n - i);

            // 다음 숫자의 인덱스를 구하고 배열에 담음
            nextNum = sequenceNum / factoResult;
            answer[idx++] = number.remove(Long.valueOf(nextNum).intValue());

            // 다음 순서를 구하기 위함
            sequenceNum %= factoResult;
        }

        return answer;
    }

    /**
     * 팩토리얼 계산
     * @param n n!을 할 숫자
     * @return long의 결과 숫자
     */
    private long facto(int n) {
        long result = 1;
        if (n == 0 || n == 1) return result;

        for (int m = 2; m <= n; m++) {
            result *= m;
        }

        return result;
    }
}

class Main1 {
    public static void main (String[] args) {
        Solution_효율안좋음 solution = new Solution_효율안좋음();
        System.out.println(Arrays.toString(solution.solution(4, 12)));
        System.out.println(Arrays.toString(solution.solution(3, 5)));
        System.out.println(Arrays.toString(solution.solution(5, 2)));
    } 
}