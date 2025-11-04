package baekjoon.BOJ_2902_KMP는_왜_KMP일까;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main_Stream {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String ans = Arrays.stream(br.readLine().split("-"))
                .map(s -> String.valueOf(s.charAt(0)))
                .collect(Collectors.joining());

        System.out.println(ans);
    } 
}
