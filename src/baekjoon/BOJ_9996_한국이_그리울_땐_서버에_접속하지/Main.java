package baekjoon.BOJ_9996_한국이_그리울_땐_서버에_접속하지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());    // 파일 개수
        String[] patterns = br.readLine().split("\\*");    // 파일 이름 패턴

        // 파일 개수만큼 검사
        for (int n = 0; n < N; n++) {
            String fileName = br.readLine();    // 파일 이름
            int fnIdx = 0;  // 파일 인덱스
            boolean matched = true; // 패턴과 맞는지

            // 길이 체크
            if (fileName.length() < patterns[0].length() + patterns[1].length()) {
                System.out.println("NE");
                continue;
            }

            // 앞부분
            for (int i = 0; i < patterns[0].length(); i++) {
                if (fileName.charAt(fnIdx++) != patterns[0].charAt(i)) {
                    matched = false;
                    break;
                }
            }

            // 뒷부분
            if (matched) {
                fnIdx = fileName.length() - 1;
                for (int i = patterns[1].length() - 1; i >= 0; i--) {
                    if (fileName.charAt(fnIdx--) != patterns[1].charAt(i)) {
                        matched = false;
                        break;
                    }
                }
            }
            
            System.out.println(matched ? "DA" : "NE");
        }
    }
}