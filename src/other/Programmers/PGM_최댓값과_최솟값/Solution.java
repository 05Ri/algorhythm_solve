package other.Programmers.PGM_최댓값과_최솟값;

import java.util.Arrays;

class Solution {
    public String solution(String s) {
        // 분리
        String[] numArr = s.split(" ");
        
        // 숫자로 치환
        int[] nums = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            nums[i] = Integer.parseInt(numArr[i]);
        }
        
        // 정렬
        Arrays.sort(nums);
        
        // 답 문자 만들기
        String answer = Integer.toString(nums[0]) + " " + Integer.toString(nums[nums.length - 1]);
        
        return answer;
    }
}

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution("1 2 3 4"));
        System.out.println(sol.solution("-1 -2 -3 -4"));
        System.out.println(sol.solution("-1 -1"));
    }
}