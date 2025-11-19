package baekjoon.BOJ_11779_최소비용_구하기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int cityCnt;
    static List<int[]>[] map;   // 각 도시와 요금을 저장할 배열
    static int[] minFee;    // 도시마다의 가장 싼 값을 저장할 배열
    static int[] prev;  // 최소 비용이 되는 전의 경로를 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cityCnt = Integer.parseInt(br.readLine());  // 도시의 개수
        int busCnt = Integer.parseInt(br.readLine());   // 버스의 개수
        
        // 버스 정보 담기
        map = new ArrayList[cityCnt + 1];
        for (int i = 1; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }
        
        
        for (int i = 0; i < busCnt; i++) {
            st = new StringTokenizer(br.readLine());

            int dep = Integer.parseInt(st.nextToken());
            int arr = Integer.parseInt(st.nextToken());
            int fee = Integer.parseInt(st.nextToken());

            map[dep].add(new int[] {arr, fee});
        }

        // 출발지 도착지 정보
        st = new StringTokenizer(br.readLine());
        int departureCity = Integer.parseInt(st.nextToken());
        int arrivalCity = Integer.parseInt(st.nextToken());

        // 탐색 전 세팅
        minFee = new int[cityCnt + 1];
        Arrays.fill(minFee, INF);
        minFee[departureCity] = 0;
        prev = new int[cityCnt + 1];

        // 다익스트라
        djikstra(departureCity, arrivalCity);
        
        // 경로 구하기
        List<Integer> route = new ArrayList<>();
        int point = arrivalCity;
        while (point != 0) {
            route.add(point);
            point = prev[point];
        }
        Collections.reverse(route);

        // 출력
        System.out.println(minFee[arrivalCity]);
        System.out.println(route.size());
        for (int n : route) {
            System.out.printf("%d ", n);
        }
        
    }

    static void djikstra(int departureCity, int arrivalCity) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {departureCity, 0});

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCity = curr[0];
            int accumulationFee = curr[1];

            if (accumulationFee > minFee[currCity]) continue;

            if (currCity == arrivalCity) return;

            for (int[] next : map[currCity]) {
                int nextCity = next[0];
                int nextFee = next[1];

                if (minFee[nextCity] > accumulationFee + nextFee) {
                    minFee[nextCity] = accumulationFee + nextFee;
                    prev[nextCity] = currCity;
                    pq.offer(new int[] {nextCity, minFee[nextCity]});
                }
            }
        }
    }
}
