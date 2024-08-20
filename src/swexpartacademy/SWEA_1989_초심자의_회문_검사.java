package swexpartacademy;

import java.util.Scanner;

public class SWEA_1989_초심자의_회문_검사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();

		out: for (int t = 1; t <= tc; t++) {
			String st = sc.next();

			char[] ch = new char[st.length()];

			for (int i = 0; i < st.length(); i++) {
				ch[i] = st.charAt(i);
			}

			int L = ch.length;
			for (int i = 0; i < L / 2; i++) {
				// 같지 않으면
				if (ch[i] != ch[L - 1 - i]) {
					System.out.printf("#%d 0\n", t);
					continue out;
				}
			}
			// 전부 일치하면
			System.out.printf("#%d 1\n", t);
		}
	}
}
