package other.Programmers.줄_서는_방법;

import java.util.Arrays;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        boolean[] visited = new boolean[n + 1];

        // 팩토리얼 계산하여 미리 담아둠
        long[] facto = new long[n];
        facto[0] = 1;
        facto[1] = 1;
        for (int i = 2; i < n; i++) {
            facto[i] = facto[i - 1] * i;
        }

        // k번째 수열 만들기
        long sequence = k - 1;
        for (int i = 0; i < n; i++) {
            // 다음 숫자의 인덱스를 구하고 배열에 담음
            int index = (int)(sequence / facto[(n - 1) - i]);

            // 안 쓴 숫자들 중 index번째 숫자 선택
            int count = -1;
            for (int num = 1; num <= n; num++) {
                if (visited[num]) continue;
                count++;
                if (count == index) {
                    answer[i] = num;
                    visited[num] = true;
                    break;
                }
            }

            // 다음 순서를 구하기 위함
            sequence %= facto[(n - 1) - i];
        }
        return answer;
    }
}

class Main {
    public static void main (String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(4, 12)));
        System.out.println(Arrays.toString(solution.solution(3, 5)));
        System.out.println(Arrays.toString(solution.solution(5, 2)));
    } 
}