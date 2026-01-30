package baekjoon.BOJ_1620_나는야_포켓몬_마스터_이다솜;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());   // 포켓몬의 개수
        int M = Integer.parseInt(st.nextToken());   // 맞춰야하는 문제 개수

        String[] poketmonName = new String[N + 1];
        Map<String, Integer> poketmonNum = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            poketmonName[i] = name;
            poketmonNum.put(name, i);
        }

        while (M-- > 0) {
            String str = br.readLine();

            if (Character.isDigit(str.charAt(0))) {
                int number = Integer.parseInt(str);
                sb.append(poketmonName[number]);
            }
            else {
                sb.append(poketmonNum.get(str));
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}
