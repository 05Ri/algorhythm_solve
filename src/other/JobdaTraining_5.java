package other;

import java.util.Arrays;

public class JobdaTraining_5 {
    public static void main(String[] args) {
        Solution5 problem = new Solution5();

        int startDayNum = 7;
    
        int[][] restDates = {
            {1, 1}, {1, 21}, {1, 22}, {1, 23},
            {3, 1},
            {5, 5}, {5, 27},
            {6, 6},
            {8, 15}, 
            {9, 28}, {9,29}, {9, 30},
            {10, 3}, {10, 9},
            {12, 25}
        };

        int vacations = 3;

        int[] answer = problem.solution(startDayNum, restDates, vacations);
    
        // 연속적으로 쉴 수 있는 기간 / 휴가 시작 - 월, 일 / 휴가 끝 - 월, 일
        // [11, 9, 23, 10, 3]
        System.out.println(Arrays.toString(answer));
    }
}

class Solution5 {
    // 12개월의 날짜를 담을 배열
    public static boolean[][] restDayCalender = new boolean[13][];
    // 각 달 별 날짜의 개수
    public static int[] cntDates = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    // 방향 정하기
    public enum Direction {
        PREV, NEXT
    }

    public int[] solution(int X, int[][] H, int N) {
        // 정답: { 연속적으로 쉴 수 있는 기간 / 휴가 시작 - 월, 일 / 휴가 끝 - 월, 일 }
        int[] answer = new int[5];

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
                if (day == 5 || day == 6) {
                    restDayCalender[month][date] = true;
                }

                // 휴일 확인 후 기입
                if (indexOfH < H.length && H[indexOfH][0] == month && H[indexOfH][1] == date) {
                    // 휴일 확인 했을 때 이미 휴일이라면 대체 휴일 찾기
                    if (restDayCalender[month][date]) {
                        findRestDay(month, date, findDirection(month, date, day));
                    }
                    // 휴일이 아니면 휴일로 기재
                    else {
                        restDayCalender[month][date] = true;
                    }
                    indexOfH++;
                }
                day++;
                day %= 7;
            }
        }

        // 연휴 구하기
        for (int month = 1; month <= 12; month++) {
            for (int date = 1; date <= cntDates[month]; date++) {
                int[] bestVacation = findBestVacation(month, date, N);
                if (answer[0] < bestVacation[0]) {
                    answer = bestVacation;
                }
            }
        }

        // debugPrintCalender();

        return answer;
    }

    /**
     * 대체공휴일을 지정할 방향을 찾아주는 함수
     * @param month 달
     * @param date 일
     * @param day 요일
     * @return Direction 방향
     */
    public Direction findDirection(int month, int date, int day) {
        // 토요일이면 이전을 탐색
        if (day == 5) {
            return Direction.PREV;
        }
        // 일요일이면 다음을 탐색
        else if (day == 6) {
            return Direction.NEXT;
        } 
        // 다른날이면 가까이에 토요일인지 일요일인지 확인 후 전날인지 훗날인지 구분
        else {
            if (day < 3) {
                return Direction.NEXT;
            }
            else {
                return Direction.PREV;
            }
        }
    }

    /**
     * 다른 쉬는 날을 찾아서 체크해주는 함수
     * @param month 월
     * @param date 일
     * @param dir 대체 공휴일을 찾을 방향
     */
    public void findRestDay(int month, int date, Direction dir) {
        // 찾을 방향의 날 불러오기
        int[] targetDate = findDate(month, date, dir);

        // 날이 조회되지 않으면 리턴
        if (targetDate == null) {
            return;
        }

        // 휴일이 아니면 휴일로 만들어준다
        if (!restDayCalender[targetDate[0]][targetDate[1]]) {
            restDayCalender[targetDate[0]][targetDate[1]] = true;
            return;
        }

        // 휴일이면 전 휴일 찾기
        findRestDay(targetDate[0], targetDate[1], dir);
    }

    /**
     * 이전날 또는 다음날을 찾아주는 함수
     * @param month 월
     * @param date 일
     * @param dir 찾을 날의 방향
     * @return int[] {월, 일}
     */
    public int[] findDate(int month, int date, Direction dir) {
        // 전날 탐색
        if (dir == Direction.PREV) {
            // 만약 1일이면
            if (date == 1) {
                // 전 달 확인
                int prevMonth = month - 1;

                // 작년으로 넘어가면 패스
                if (prevMonth == 0) {
                    return null;
                }
                
                return new int[] {prevMonth, cntDates[prevMonth]};
            }

            return new int[] {month, date - 1};
        }
        // 다음날 탐색
        else {
            // 만약 다음날이 다음달이면
            if (date + 1 > cntDates[month]) {
                // 다음 달 확인
                int nextMonth = month + 1;

                // 내년이면 패스
                if (nextMonth == 13) {
                    return null;
                }

                return new int[] {nextMonth, 1};
            }

            return new int[] {month, date + 1};
        }
    }

    /**
     * 받은 휴가를 이용해서 제일 길게 쉴 수 있는 휴일이 얼마나 되는지 구해주는 함수
     * @param month 월
     * @param date 일
     * @param vacationAmount 휴가 개수
     * @return int[] { 연속적으로 쉴 수 있는 기간 / 휴가 시작 - 월, 일 / 휴가 끝 - 월, 일 }
     */
    public int[] findBestVacation(int month, int date, int vacationAmount) {
        int[] targetDate = {month, date};
        int vacationCnt = 0;
        
        while (true) {
            // 오늘이 유효하지 않으면 리턴
            if (targetDate == null) {
                return new int[5];
            }

            // 휴가 개수가 없을 때
            if (vacationAmount <= 0) {
                // 현재 날이 쉬는날이 아니면 리턴
                if (!restDayCalender[targetDate[0]][targetDate[1]]) {
                    targetDate = findDate(targetDate[0], targetDate[1], Direction.PREV);
                    return new int[] {vacationCnt, month, date, targetDate[0], targetDate[1]};
                }
            }
            // 휴가 개수가 있으면
            else {
                // 현재 날이 쉬는날이 아니면
                if (!restDayCalender[targetDate[0]][targetDate[1]]) {
                    // 휴가 하나 줄이고
                    vacationAmount--;
                }
            }
            // 휴일 개수 카운팅
            vacationCnt++;
            // 다음날 탐색
            targetDate = findDate(targetDate[0], targetDate[1], Direction.NEXT);
        }
    }

    /**
     * 휴일 달력을 표시해준다.
     */
    public void debugPrintCalender() {
        int cnt = 0;
        for (int month = 1; month < restDayCalender.length; month++) {
            cnt = 0;
            System.out.println(month + "월 =======================================================================================================");
            for (int date = 1; date < restDayCalender[month].length; date++) {
                cnt++;
                System.out.printf("%s<%s> ", date, restDayCalender[month][date]);
                if (cnt > 6) {
                    System.out.println();
                    cnt = 0;
                }
            }
            System.out.println();
            System.out.println("==========================================================================================================");
            System.out.println();
        }
    }
}