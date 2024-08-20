package swexpartacademy;

import java.util.Scanner;

public class SWEA_2072_홀수만_더하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		
		for(int i = 1; i <= t; i++) {
			int temp, total = 0;
			
			for(int j = 0; j < 10; j++) {
				temp = sc.nextInt();
				
				if(temp % 2 == 1) {
					total += temp;
				}
			}
			
			System.out.println("#" + i + " " + total);
		}
	}
}
