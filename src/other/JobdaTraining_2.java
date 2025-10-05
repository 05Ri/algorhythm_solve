package other;

public class JobdaTraining_2 {
    public static void main(String[] args) {
        Solution2 problem = new Solution2();
    
        int[][] restDays = {
            {1, 1}, {1, 21}, {1, 22}, {1, 23},
            {3, 1},
            {5, 5}, {5, 27},
            {6, 6},
            {8, 15}, 
            {9, 28}, {9,29}, {9, 30},
            {10, 3}, {10, 9},
            {12, 25}
        };

        int answer = problem.solution(7, restDays);
    
        // 115
        System.out.println(answer);
    }
}

class Solution2 {
    // 12개월의 날짜를 담을 배열
    public static boolean[][] restDayCalender = new boolean[13][];
    // 각 달 별 날짜의 개수
    public static int[] cntDates = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int solution(int X, int[][] H) {
        // 정답: 휴일의 개수
        int answer = 0;

        // H의 인덱스
        int indexOfH = 0;

        // 휴일인지 아닌지 체크해주는 달력 초기화
        for (int i = 1; i <= 12; i++) {
            restDayCalender[i] = new boolean[cntDates[i] + 1];
        }

        // 활용하기 편하도록 0을 월요일 ... 6은 일요일로 변환
        int day = X - 1;

        // 월, 일 확인하여 휴일 달력에 기입
        for (int month = 1; month <= 12; month++) {
            for (int date = 1; date <= cntDates[month]; date++) {
                // 주말 우선 기입
                if (isRestDay(day)) {
                    restDayCalender[month][date] = true;
                }

                // 휴일 확인 후 기입
                if (indexOfH < H.length && H[indexOfH][0] == month && H[indexOfH][1] == date) {   
                    restDayCalender[month][date] = true;
                    indexOfH++;
                }

                day++;
            }
        }

        // 휴일 달력에 기재된 휴일 개수 세기
        for (int month = 1; month <= 12; month++) {
            for (int date = 1; date <= cntDates[month]; date++) {
                if (restDayCalender[month][date] == true) {
                    answer++;
                }
            }
        }

        // System.out.println(Arrays.deepToString(restDayCalender));

        return answer;
    }

    /**
     * 쉬는 날이 맞는지 체크해주는 함수
     * @param int day 요일
     * @return boolean
    */
    public boolean isRestDay(int day) {
        if (day % 7 == 5 || day % 7 == 6) {
            return true;
        }

        return false;
    }
}