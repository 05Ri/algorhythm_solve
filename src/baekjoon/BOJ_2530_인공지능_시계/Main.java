package baekjoon.BOJ_2530_인공지능_시계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        int cookingTime = Integer.parseInt(br.readLine());

        second += cookingTime;
        minute += second / 60;
        second %= 60;
        hour += minute / 60;
        minute %= 60;
        hour %= 24;
        
        System.out.printf("%d %d %d", hour, minute, second);
    }
}
