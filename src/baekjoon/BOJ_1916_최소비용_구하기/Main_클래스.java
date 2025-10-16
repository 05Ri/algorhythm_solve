package baekjoon.BOJ_1916_최소비용_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class City implements Comparable<City> {
    int nextCity;
    int fee;

    public City(int nextCity, int fee) {
        this.nextCity = nextCity;
        this.fee = fee;
    }

    @Override
    public int compareTo(City o) {
        return this.fee - o.fee;
    }
}

public class Main_클래스 {
    // 버스가 가는 도시와 요금 정보를 저장할 배열
    public static List<City>[] fareTable;
    // 가장 싼 값을 저장할 배열
    public static int[] cheapestFee;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 개수
        int N = Integer.parseInt(br.readLine());
        // 버스의 개수
        int M = Integer.parseInt(br.readLine());

        // 버스요금표 및 가장 싼 값 배열 초기화
        fareTable = new ArrayList[N + 1];
        cheapestFee = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            fareTable[i] = new ArrayList<City>();
            cheapestFee[i] = Integer.MAX_VALUE;
        }

        // 도시 간 비용 입력받기
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            // 출발 도시
            int departure = Integer.parseInt(st.nextToken());
            // 도착 도시
            int arrival = Integer.parseInt(st.nextToken());
            // 버스 비용
            int price = Integer.parseInt(st.nextToken());

            fareTable[departure].add(new City(arrival, price));
        }

        // 출발지와 도착지
        st = new StringTokenizer(br.readLine());
        int starting = Integer.parseInt(st.nextToken());
        int ending = Integer.parseInt(st.nextToken());

        // 다익스트라
        dijkstra(starting);

        // 답 출력
        System.out.println(cheapestFee[ending]);
    }

    private static void dijkstra(int starting) {
        PriorityQueue<City> queue = new PriorityQueue<City>();
        cheapestFee[starting] = 0;
        queue.offer(new City(starting, 0));

        while (!queue.isEmpty()) {
            City currCity = queue.poll();

            if (cheapestFee[currCity.nextCity] < currCity.fee) continue;
            
            for (City city : fareTable[currCity.nextCity]) {
                int cost = cheapestFee[currCity.nextCity] + city.fee;
                if (cheapestFee[city.nextCity] > cost) {
                    cheapestFee[city.nextCity] = cost;
                    queue.offer(new City(city.nextCity, cost));
                }
            }
        }
    }
}
