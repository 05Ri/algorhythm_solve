package other.Programmers.PGM_삼각_달팽이;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(4)));
        System.out.println(Arrays.toString(sol.solution(5)));
        System.out.println(Arrays.toString(sol.solution(6)));
        System.out.println(Arrays.toString(sol.solution(7)));
        System.out.println(Arrays.toString(sol.solution(8)));
    }
}

class Solution {
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        // 채우는 숫자
        int fillNum = 1;
        // 몇 번 이동할지 판단해주는 숫자
        int movement = n;
        // height, bottom 좌표
        int h = -1;
        int b = 0;
        
        while (movement > 0) {
            // 아래로 이동
            for (int i = 0; i < movement; i++) {
                triangle[++h][b] = fillNum++;
            }
            movement--;
            
            // 오른쪽으로 이동
            for (int i = 0; i < movement; i++) {
                triangle[h][++b] = fillNum++;
            }
            movement--;
            
            // 좌상향 대각으로 이동
            for (int i = 0; i < movement; i++) {
                triangle[--h][--b] = fillNum++;
            }
            movement--;
        }
        
        // // 출력 테스트
        // for (int[] arr : triangle) {
        //     System.out.println(Arrays.toString(arr));
        // }
        
        int[] answer = new int[fillNum - 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (triangle[i][j] == 0) continue;
                answer[index++] = triangle[i][j];
            }
        }
        
        return answer;
    }
}