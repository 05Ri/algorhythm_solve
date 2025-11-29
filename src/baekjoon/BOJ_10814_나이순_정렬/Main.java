package baekjoon.BOJ_10814_나이순_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class User {
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        User[] users = new User[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            users[i] = new User(age, name);
        }

        Arrays.sort(users, (a, b) -> {
            return Integer.compare(a.age, b.age);
        });

        for (int i = 0; i < N; i++) {
            sb.append(users[i].age).append(" ").append(users[i].name).append("\n");
        }

        System.out.println(sb.toString());
    }
}
