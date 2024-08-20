package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1218_괄호_짝짓기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();

		for (int t = 1; t <= 10; t++) {
			// 테스트케이스 길이 입력받기
			int L = Integer.parseInt(br.readLine());
			// 테스트케이스 괄호 입력받기
			String testCase = br.readLine();
			
			
			// 만약 괄호의 총 개수가 홀수라면 유효하지 않은 값이다!
			if (L % 2 == 1) {
				System.out.printf("#%d 0\n", t);
				continue;
			}
			
			// 기본값은 가능한 것으로 한다.
			int isAble = 1;

			// 입력받은 테스트케이스 길이만큼 괄호 넣어주기
			for (int i = 0; i < L; i++) {
				char c = testCase.charAt(i);
				// 괄호의 머리가 들어온다면 스택에 푸쉬해주고
				// 꼬리가 들어온다면 같은 종류인지 확인 후 pop해준다.
				// 종류가 같지 않다면 유효하지 않으므로 isAble을 0으로 만들어주고 반복문을 종료해준다.
				if (c == '(' || c == '[' || c == '{' || c == '<') {
					stack.push(c);
				} else if (c == ')' && stack.peek() == '(') {
					stack.pop();
				} else if (c == ']' && stack.peek() == '[') {
					stack.pop();
				} else if (c == '}' && stack.peek() == '{') {
					stack.pop();
				} else if (c == '>' && stack.peek() == '<') {
					stack.pop();
				} else {
					isAble = 0;
					break;
				}
			}
			
			System.out.println(stack);

			// 전부 검사를 해서 통과했어도 스택에 남아있는 녀석들이 있을 수 있기 때문에 체크해준다.
			// 스택이 비지 않았다면 유효하지 않다.
			if (!stack.isEmpty()) {
				isAble = 0;
			}
			
			System.out.printf("#%d %d\n", t, isAble);
			stack.clear();
		}
	}
}
