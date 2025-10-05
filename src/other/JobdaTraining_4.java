package other;

import java.util.Arrays;

public class JobdaTraining_4 {
    public static void main(String[] args) {
        Solution4 problem = new Solution4();

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

        int payday = 5;

        int[] answer = problem.solution(startDayNum, restDates, payday);
    
        // [5, 6, 6, 5, 4, 5, 5, 4, 5, 5, 6, 5]
        System.out.println(Arrays.toString(answer));
    }
}

class Solution4 {
    // 12개월의 날짜를 담을 배열
    public static boolean[][] restDayCalender = new boolean[13][];
    // 각 달 별 날짜의 개수
    public static int[] cntDates = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    // 방향 정하기
    public enum Direction {
        PREV, NEXT
    }

    public int[] solution(int X, int[][] H, int Y) {
        // 정답: 월급을 받는 날짜들
        int[] answer = new int[12];

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

        // 월급날 확인
        for (int month = 1; month <= 12; month++) {
            // 월급날이 휴일이 아니면
            if (!restDayCalender[month][Y]) {
                // 답에 추가
                answer[month - 1] = Y;
                continue;
            }

            // 탐색 결과 저장용
            final int SENTINEL = Integer.MAX_VALUE / 2;
            int prevCnt = SENTINEL;
            int nextCnt = SENTINEL;
            int[] prevDate = null;
            int[] nextDate = null;

            int[] cur = findDate(month, Y, Direction.PREV);
            int steps = 1;

            // 평일 찾아 삼만리
            while (cur != null && cur[0] == month) {
                if (!restDayCalender[cur[0]][cur[1]]) {
                    prevDate = cur;
                    prevCnt = steps;
                    break;
                }
                cur = findDate(cur[0], cur[1], Direction.PREV);
                steps++;
            }

            cur = findDate(month, Y, Direction.NEXT);
            steps = 1;
            while (cur != null && cur[0] == month) {
                // 달이 넘어갔다면 null로
                if (!restDayCalender[cur[0]][cur[1]]) {
                    nextDate = cur;
                    nextCnt = steps;
                    break;
                }
                cur = findDate(cur[0], cur[1], Direction.NEXT);
                steps++;
            }

            // 월급날을 찾을 수 없다면 평상시 월급날로
            if (prevDate == null && nextDate == null) {
                answer[month - 1] = Y;
            }
            // 하나만 존재하면 그 날로 선택
            else if (prevDate == null) {
                answer[month - 1] = nextDate[1];
            }
            else if (nextDate == null) {
                answer[month - 1] = prevDate[1];
            }
            // 이전으로 간 횟수가 더 적거나 탐색 횟수가 같다면
            else if (prevCnt <= nextCnt) {
                answer[month - 1] = prevDate[1];
            }
            // 다음으로 갔을 때가 더 적다면
            else {
                answer[month - 1] = nextDate[1];
            }
        }

        // 휴일 달력에 기재된 휴일 개수 세기
        // for (int month = 1; month <= 12; month++) {
        //     for (int date = 1; date <= cntDates[month]; date++) {
        //         if (restDayCalender[month][date]) {
        //             // answer++;
        //         }
        //     }
        // }

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