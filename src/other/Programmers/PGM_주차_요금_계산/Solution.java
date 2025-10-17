package other.Programmers.PGM_주차_요금_계산;

import java.util.*;

class Solution {
    /*
        fees[ 기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원) ]
        1 ≤ fees[0] ≤ 1,439
        0 ≤ fees[1] ≤ 100,000
        1 ≤ fees[2] ≤ 1,439
        1 ≤ fees[3] ≤ 10,000

        records ->  "시각, 차량번호, 내역" 의 String 배열
    */

    public int[] solution(int[] fees, String[] records) {
        // 주차중
        Map<String, Integer> parking = new HashMap<>();
        // 출차한 차량의 시간
        Map<String, Integer> carsMap = new HashMap<>();
        // 결과
        List<Integer> result = new ArrayList<>();

        // 기록 분석하기
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");

            // 시간
            String timeStamp = record[0];
            // 차량 번호
            String carNumber = record[1];
            // 입차인지 출차인지
            String enterType = record[2];

            // 입차하면 주차 리스트에 넣어주기
            if (enterType.equals("IN")) {
                parking.put(carNumber, toMinutes(timeStamp));
            }
            // 출차하면 시간 확인 후 주차 리스트에서 빼주기
            else {
                // 있는 시간 계산용
                int otherTime = 0;
                if (carsMap.containsKey(carNumber)) {
                    otherTime = carsMap.get(carNumber);
                }
                // 출차 시간 확인 및 주차 되었던 시간 확인, 주차 리스트에서 제외
                int parkingTime = parking.remove(carNumber);
                carsMap.put(carNumber, toMinutes(timeStamp) - parkingTime + otherTime);
            }
        }

        // 출차 안한 차 확인
        if (!parking.isEmpty()) {
            for (String car : parking.keySet()) {
                int parkingTime = parking.get(car);
                int prevTime = 0;
                // 이전 기록이 있는지 확인
                if (carsMap.containsKey(car)) {
                    prevTime = carsMap.get(car);
                }
                carsMap.put(car, toMinutes("23:59") - parkingTime + prevTime);
            }
        }

        // 맵 추출해서 값 정리
        List<String> cars = new ArrayList<String>(carsMap.keySet());
        Collections.sort(cars);
        
        // 요금 계산
        for (String carNum : cars) {
            int parkingTime = carsMap.get(carNum);
            // 기본 시간 뺐을 때 음수라면 기본 요금만
            parkingTime -= fees[0];
            if (parkingTime <= 0) {
                result.add(fees[1]);
                continue;
            }

            int cost = fees[1] + ((parkingTime + fees[2] - 1) / fees[2]) * fees[3];
            result.add(cost);
        }

        int[] answer = new int[result.size()];

        for (int i = 0 ; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private int toMinutes(String timeStamp) {
        String[] time = timeStamp.split(":");
        int hour = Integer.valueOf(time[0]);
        int min = Integer.valueOf(time[1]);

        return hour * 60 + min;
    }
}

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // [14600, 34400, 5000]
        System.out.println(Arrays.toString(sol.solution(
            new int[] { 180, 5000, 10, 600 }, 
            new String[] { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" }
        )));

        // [0, 591]
        System.out.println(Arrays.toString(sol.solution(
            new int[] { 120, 0, 60, 591 }, 
            new String[] { "16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN" }
        )));

        // [14841]
        System.out.println(Arrays.toString(sol.solution(
            new int[] { 1, 461, 1, 10 }, 
            new String[] { "00:00 1234 IN" }
        )));
    }
}