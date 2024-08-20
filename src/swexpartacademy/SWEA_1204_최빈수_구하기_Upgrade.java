package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_1204_최빈수_구하기_Upgrade {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());

		for (int $ = 1; $ <= testCase; $++) {
			int[] stu = new int[1000]; // 학생수
			int[] score = new int[101]; // 점수 카운트 배열

			// 테스트 케이스 번호 입력받기
			int t = Integer.parseInt(br.readLine());

			// 1000명의 점수 받기
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 1000명의 점수 학생 배열에 넣기
			for (int i = 0; i < stu.length; i++)
				stu[i] = Integer.parseInt(st.nextToken());

			// 1000명의 점수 카운트하기
			for (int i = 0; i < stu.length; i++)
				score[stu[i]]++;

			int max = 0; // 빈도 많은 가장 큰 숫자
			int maxIdx = 0; // max의 인덱스
			
			// 카운트 정렬에서 가장 빈도가 많은 숫자 찾기
			for (int i = 1; i < score.length; i++) {
				if (max <= score[i]) { // 빈도가 max와 같거나 크면
					max = score[i]; // 그 큰 숫자 대입
					maxIdx = i; // 인덱스가 점수니까 정답이다
				}
			}
			System.out.printf("#%d %d\n", t, maxIdx);
		}
	}
}
