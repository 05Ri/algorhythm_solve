package baekjoon.BOJ_15686_치킨_배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;   // 도시 크기
    static int M;   // 남길 치킨집 개수
    static List<int[]> chikenPlaceList;    // 치킨집들의 좌표
    static List<int[]> homeList;   // 집들의 좌표
    static int minChikenDistance;   // 최소 치킨 거리
    static boolean[] visited;   // 치킨집 방문배열
    static List<int[]> choicePlace;    // 고른 치킨집들의 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chikenPlaceList = new ArrayList<>();
        homeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    homeList.add(new int[] {i, j});
                }
                else if (n == 2) {
                    chikenPlaceList.add(new int[] {i, j});
                }
            }
        }

        minChikenDistance = Integer.MAX_VALUE;
        choicePlace = new ArrayList<>();
        visited = new boolean[chikenPlaceList.size()];
        choiceChikenPlace(0, 0);

        System.out.println(minChikenDistance);
    }

    /**
     * 치킨집 고르기
     * @param start 다음 시작할 인덱스
     * @param count 치킨집을 고른 개수
     */
    static void choiceChikenPlace(int start, int count) {
        if (count == M) {
            minChikenDistance = Math.min(minChikenDistance, getChikenDistance());
            return;
        }

        for (int i = start; i < chikenPlaceList.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            choicePlace.add(chikenPlaceList.get(i));
            choiceChikenPlace(i + 1, count + 1);
            choicePlace.remove(choicePlace.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 치킨 거리 구하기
     * @return 고른 치킨집과의 최소 거리
     */
    static int getChikenDistance() {
        int result = 0;
        for (int[] home : homeList) {
            int minDis = Integer.MAX_VALUE;
            for (int[] place : choicePlace) {
                minDis = Math.min(minDis, Math.abs(home[0] - place[0]) + Math.abs(home[1] - place[1]));
            }
            result += minDis;
        }
        return result;
    }

    static void printList(List<int[]> list) {
        for (int[] arr : list) {
            System.out.print(Arrays.toString(arr));
        }
        System.out.println();
    }
}
