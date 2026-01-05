package baekjoon.BOJ_2083_럭비_클럽;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            if (name.equals("#")) break;
            int age = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            sb.append(name).append(' ');

            if (age > 17 || weight >= 80) {
                sb.append("Senior");
            } else {
                sb.append("Junior");
            }

            sb.append('\n');
        }

        System.out.println(sb.toString());
    }
}
