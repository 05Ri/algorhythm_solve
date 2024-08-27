package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16953_A_to_B {
	static int startNum, targetNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		startNum = Integer.parseInt(st.nextToken());
		
		targetNum = Integer.parseInt(st.nextToken());
		
		System.out.println(queue());
	}

	private static int queue() {
		Queue<Long> save = new LinkedList<>();
		
		save.offer((long) startNum);
		
		int count = 0;
		
		while(!save.isEmpty()) {
			count++;
			int size = save.size();
			
			for (int i = 0; i < size; i++) {
				long num = save.poll();
				
				// 뽑아낸 숫자가 목표치와 같다면 카운트 증가 후 리턴
				if (num == targetNum) return count;
				
				// 뽑아낸 숫자가 목표치보다 크다면 의미가 없는 수
				if (num > targetNum) continue;
				
				// 뽑아낸 수에서 * 2
				save.offer(num * 2);
				// 뽑아낸 수에서 뒤에 1 붙이기
				save.offer(10 * num + 1);
			}
		}
		
		// 만들 수 없는 경우
		return -1;
	}
}
