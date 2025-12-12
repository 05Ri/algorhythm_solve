package baekjoon.BOJ_10250_ACM_호텔;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());    // 테스트케이스 개수

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int H = Integer.parseInt(st.nextToken());   // 호텔의 층 수
            int W = Integer.parseInt(st.nextToken());   // 한 층의 방 수
            int N = Integer.parseInt(st.nextToken());   // 몇 번째 손님인지
    
            // 층 구하기
            int floor = N % H == 0 ? H : N % H;

            // 호 구하기
            int room = N % H == 0 ? N / H : N / H + 1;
    
            System.out.println(floor * 100 + room);
        }
    }
}
