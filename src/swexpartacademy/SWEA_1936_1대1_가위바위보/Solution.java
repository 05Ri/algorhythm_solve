package swexpartacademy.SWEA_1936_1대1_가위바위보;

import java.util.Scanner;


public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a, b = 0;
		a = sc.nextInt();
		b = sc.nextInt();

		if(a > b || (a == 3 && b == 1)) {
			System.out.println('A');
		} else {
			System.out.println('B');
		}
		sc.close();
	}
}
