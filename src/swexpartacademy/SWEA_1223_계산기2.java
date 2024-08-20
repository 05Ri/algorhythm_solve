package swexpartacademy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SWEA_1223_계산기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 스택 안에서 우선순위를 비교하기 위해서
		Map<Character, Integer> priority = new HashMap<>();

		priority.put('+', 1);
		priority.put('*', 2);

		for (int t = 1; t <= 10; t++) {
			// 기호를 담을 스택
			Stack<Character> op = new Stack<>();
			// 후위 표기식으로 변경할 문자열
			String postfix = "";

			// 식 길이 입력받기
			int ExLength = sc.nextInt();
			// 식 입력받기
			String expression = sc.next();

			// 표현식의 길이만큼
			for (int i = 0; i < ExLength; i++) {
				char c = expression.charAt(i);

				if ('0' <= c && c <= '9') {
					// 숫자를 만나면 postfix에 추가
					postfix += c;
				} else if (op.isEmpty()) {
					// 기호를 만났는데 스택이 비어있다면
					op.push(c);
				} else {
					// 기호를 만났는데 스택이 비어있지 않다면
					// 우선순위가 낮은 기호가 마지막에 위치할 때까지 pop
					while (!op.isEmpty() && priority.get(c) < priority.get(op.peek()))
						postfix += op.pop();

					op.push(c);
				}
			}
			// 스택이 아직 차있다면 꺼내기
			while (!op.isEmpty()) {
				postfix += op.pop();
			}
			// 테스트
//			System.out.println(postfix);

			// 후위 표기식으로 계산하기
			// 숫자를 담을 스택
			Stack<Integer> num = new Stack<>();

			for (int i = 0; i < postfix.length(); i++) {
				char c = postfix.charAt(i);

				if ('0' <= c && c <= '9') {
					num.push(c - '0');
				} else {
					switch (c) {
					case '+':
						int b = num.pop();
						int a = num.pop();
						num.push(a + b);
						break;
					case '*':
						b = num.pop();
						a = num.pop();
						num.push(a * b);
						break;
					}
				}
				// 테스트
//				System.out.println(num);
			}
			System.out.printf("#%d %d\n", t, num.pop());
		}
		sc.close();
	}
}
