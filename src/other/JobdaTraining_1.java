package other;

public class JobdaTraining_1 {
    public static void main(String[] args) {
        Solution1 problem = new Solution1();
    
        int answer = problem.solution(7);
    
        // 105
        System.out.println(answer);
    }
}

class Solution1 {
    public int solution(int X) {
        int answer = 0;

        int day = X - 1;

        for (int i = 0; i < 365; i++) {
            if ((day + i) % 7 == 5 || (day + i) % 7 == 6) {
                answer++;
            }
        }

        return answer;
    }
}
