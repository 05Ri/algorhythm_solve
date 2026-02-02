package baekjoon.BOJ_1764_듣보잡;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        Set<String> notListening = new HashSet<>();
        List<String> notListeningSeeing = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            notListening.add(br.readLine());
        }

        while (M-- > 0) {
            String notSeeing = br.readLine();
            if (notListening.contains(notSeeing)) {
                notListeningSeeing.add(notSeeing);
            }
        }

        notListeningSeeing.sort((a, b) -> a.compareTo(b));

        sb.append(notListeningSeeing.size()).append('\n');
        for (String person : notListeningSeeing) {
            sb.append(person).append('\n');
        }

        System.out.println(sb);
    }
}
