package baekjoon.BOJ_10825_국영수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Student {
    private String name;
    private int korScore;
    private int engScore;
    private int mathScore;

    public Student() {}

    public Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.korScore = kor;
        this.engScore = eng;
        this.mathScore = math;
    }

    public String getName() {
        return this.name;
    }
    public int getKor() {
        return this.korScore;
    }
    public int getEng() {
        return this.engScore;
    }
    public int getMath() {
        return this.mathScore;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());    // 학생 수
        Student[] students = new Student[N];    // 학생들 정보의 배열

        // 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            students[i] = new Student(name, kor, eng, math);
        }

        // 정렬
        Arrays.sort(students, (a, b) -> {
            if (a.getKor() == b.getKor()) {
                if (a.getEng() == b.getEng()) {
                    if (a.getMath() == b.getMath()) {
                        return a.getName().compareTo(b.getName());
                    }
                    return Integer.compare(b.getMath(), a.getMath());
                }
                return Integer.compare(a.getEng(), b.getEng());
            }
            return Integer.compare(b.getKor(), a.getKor());
        });

        // 출력
        for (Student student : students) {
            sb.append(student.getName()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
