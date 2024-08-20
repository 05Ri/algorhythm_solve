package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 이닝 개수
		int N = Integer.parseInt(br.readLine());
		
		for (int $ = 1; $ <= N; $++) {
			// 선수 성적 입력받을 배열 생성
			int[] result = new int[10];
			
			result[0] = -1;
			// 타자 순서 넣을 배열 생성
			int[] batter = new int[10];
			
			// 선수의 결과 입력받기
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= 9; i++) {
				result[i] = Integer.parseInt(st.nextToken());
			}
			
			// 1번 선수가 4번 타자 고정이다.
			batter[4] = result[1];
		}
	}
}
