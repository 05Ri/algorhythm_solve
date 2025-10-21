package swexpartacademy.SWEA_8931_제로;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt();

		for (int t = 1; t <= testCase; t++) {
			int K = sc.nextInt();

			Stack<Integer> stack = new Stack<>();
			int sum = 0;

			for (int $ = 0; $ < K; $++) {
				int num = sc.nextInt();

				if (num == 0) {
					stack.pop();
				} else {
					stack.add(num);					
				}
			}
			
			if (!stack.isEmpty()) {
				for (int i : stack)
					sum += i;
			}
			
			System.out.printf("#%d %d\n", t, sum);
		}
		sc.close();
	}
}
