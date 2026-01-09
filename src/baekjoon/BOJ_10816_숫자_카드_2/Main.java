package baekjoon.BOJ_10816_숫자_카드_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        Map<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int k = Integer.parseInt(st.nextToken());
            if (!map.containsKey(k)) {
                map.put(k, 1);
                continue;
            }

            map.put(k, map.get(k) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int k = Integer.parseInt(st.nextToken());
            sb.append(map.containsKey(k) ? map.get(k) : 0).append(' ');
        }

        System.out.println(sb.toString());
    }
}