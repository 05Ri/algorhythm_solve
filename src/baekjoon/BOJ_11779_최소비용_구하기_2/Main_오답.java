package baekjoon.BOJ_11779_최소비용_구하기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_오답 {
    static final int INF = Integer.MAX_VALUE;
    static int cityCnt;
    static List<int[]>[] map;   // 각 도시와 요금을 저장할 배열
    static int[] route; // 경로를 저장할 배열
    static int[] minFee;    // 도시마다의 가장 싼 값을 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cityCnt = Integer.parseInt(br.readLine());  // 도시의 개수
        int busCnt = Integer.parseInt(br.readLine());   // 버스의 개수
        
        // 버스 정보 담기
        map = new ArrayList[cityCnt + 1];
        for (int i = 0; i < busCnt; i++) {
            st = new StringTokenizer(br.readLine());

            int dep = Integer.parseInt(st.nextToken());
            int arr = Integer.parseInt(st.nextToken());
            int fee = Integer.parseInt(st.nextToken());

            if (map[dep] == null) {
                map[dep] = new ArrayList<>();
            }

            // 후에 안헷갈리기 위해 요금, 도착지 순서로 넣기
            map[dep].add(new int[] {fee, arr});
        }

        // 출발지 도착지 정보
        st = new StringTokenizer(br.readLine());
        int departureCity = Integer.parseInt(st.nextToken());
        int arrivalCity = Integer.parseInt(st.nextToken());

        // 탐색 전 세팅
        Arrays.fill(minFee, INF);
        minFee[departureCity] = 0;

        // 다익스트라
        djikstra(departureCity, arrivalCity);

        // 출력
        System.out.println(minFee[arrivalCity]);
        System.out.println(route.length);
        for (int n : route) {
            System.out.printf("%d ", n);
        }
    }

    /**
     * 다익스트라
     * @param departureCity 출발 도시
     * @param arrivalCity 도착 도시
     */
    static void djikstra(int departureCity, int arrivalCity) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[] visited = new boolean[cityCnt + 1];
        // 요금, 거쳐간 도시들 누적
        pq.offer(new int[] {0, departureCity});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currFee = curr[0];
            int currCity = curr[curr.length - 1];
            int[] routeCity = new int[curr.length - 2];
            System.arraycopy(curr, 1, routeCity, 0, routeCity.length);
            
            visited[currCity] = true;
            
            if (currCity == arrivalCity) {
                route = new int[routeCity.length];
                System.arraycopy(routeCity, 0, route, 0, routeCity.length);
                return;
            }

            for (int[] info : map[currCity]) {
                int nextFee = info[0];
                int nextCity = info[1];

                // 다음 도시의 요금이 현재 도시의 가장 싼 값과 다음 비용을 더한 값보다 더 싸다면 건너뛰기
                if (minFee[nextCity] > currFee + nextFee) continue;

                minFee[nextCity] = nextFee + minFee[currCity];
                
                // 큐에 넣을 배열 만들기
                int[] offerArr = new int[curr.length + 1];
                offerArr[0] = minFee[nextCity];
                System.arraycopy(routeCity, 0, offerArr, 1, routeCity.length);
                offerArr[offerArr.length - 1] = nextCity;

                pq.offer(offerArr);
            }
        }
    }
}
